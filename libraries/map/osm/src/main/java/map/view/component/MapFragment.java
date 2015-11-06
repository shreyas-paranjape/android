package map.view.component;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidPreferences;
import org.mapsforge.map.android.util.AndroidSupportUtil;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.model.MapViewPosition;
import org.mapsforge.map.model.common.PreferencesFacade;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class MapFragment extends Fragment {


    protected MapView mapView;
    protected PreferencesFacade preferencesFacade;
    protected XmlRenderThemeStyleMenu renderThemeStyleMenu;
    protected List<TileCache> tileCaches = new ArrayList<TileCache>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        mapView = (MapView) v.findViewById(getMapViewId());
        createSharedPreferences();
        createMapViews();
        createTileCaches();
        checkPermissionsAndCreateLayersAndControls();
        return v;
    }

    /*
     * Abstract methods that must be implemented.
	 */

    /**
     * @return the layout to be used,
     */
    protected abstract int getLayoutId();

    /**
     * @return the id of the mapview inside the layout.
     */
    protected abstract int getMapViewId();

    /**
     * Gets the name of the map file.
     * The directory for the file is supplied by getMapFileDirectory()
     *
     * @return the map file name to be used
     */
    protected abstract String getMapFileName();

    /**
     * Hook to create controls, such as scale bars.
     * You can add more controls.
     */
    protected void createControls() {
        initializePosition(mapView.getModel().mapViewPosition);
    }

    /**
     * The MaxTextWidthFactor determines how long a text may be before it is line broken. The
     * default setting should be good enough for most apps.
     *
     * @return the maximum text width factor for line breaking captions
     */
    protected float getMaxTextWidthFactor() {
        return 0.7f;
    }

    /**
     * @return the default starting zoom level if nothing is encoded in the map file.
     */
    protected byte getZoomLevelDefault() {
        return (byte) 12;
    }

    /**
     * @return the minimum zoom level of the map view.
     */
    protected byte getZoomLevelMin() {
        return (byte) 0;
    }

    /**
     * @return the maximum zoom level of the map view.
     */
    protected byte getZoomLevelMax() {
        return (byte) 24;
    }

    /**
     * Template method to create the map views.
     */
    protected void createMapViews() {
        mapView.getModel().init(this.preferencesFacade);
        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(true);
        mapView.setBuiltInZoomControls(hasZoomControls());
        mapView.getMapZoomControls().setAutoHide(isZoomControlsAutoHide());
        mapView.getMapZoomControls().setZoomLevelMin(getZoomLevelMin());
        mapView.getMapZoomControls().setZoomLevelMax(getZoomLevelMax());
    }

    /**
     * Creates the shared preferences that are being used to store map view data over
     * activity restarts.
     */
    protected void createSharedPreferences() {
        this.preferencesFacade =
                new AndroidPreferences(
                        getActivity().getSharedPreferences(getPersistableId(),
                                Activity.MODE_PRIVATE));
    }

    /**
     * Gets the default initial position of a map view if nothing is set in the map file. This
     * operation is used as a fallback only. Override this if you are not sure if your map file
     * will always have an initial position.
     *
     * @return the fallback initial position of the mapview.
     */
    protected MapPosition getDefaultInitialPosition() {
        return new MapPosition(new LatLong(0, 0), getZoomLevelDefault());
    }

    /**
     * Extracts the initial position from the map file, falling back onto the value supplied
     * by getDefaultInitialPosition if there is no initial position coded into the map file.
     * You will only need to override this method if you do not want the initial position extracted
     * from the map file.
     *
     * @return the initial position encoded in the map file or a fallback value.
     */
    protected MapPosition getInitialPosition() {
        MapDataStore mapFile = getMapFile();

        if (mapFile.startPosition() != null) {
            Byte startZoomLevel = mapFile.startZoomLevel();
            if (startZoomLevel == null) {
                // it is actually possible to have no start zoom level in the file
                startZoomLevel = new Byte((byte) 12);
            }
            return new MapPosition(mapFile.startPosition(), startZoomLevel);
        } else {
            return getDefaultInitialPosition();
        }
    }

    /**
     * Provides the directory of the map file, by default the Android external storage
     * directory (e.g. sdcard).
     *
     * @return
     */
    protected File getMapFileDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Combines map file directory and map file to a map file.
     * This method usually will not need to be changed.
     *
     * @return a map file interface
     */
    protected MapDataStore getMapFile() {
        return new MapFile(new File(getMapFileDirectory(), this.getMapFileName()));
    }

    /**
     * The persistable ID is used to store settings information, like the center of the last view
     * and the zoomlevel. By default the simple name of the class is used. The value is not user
     * visibile.
     *
     * @return the id that is used to save this mapview.
     */
    protected String getPersistableId() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the relative size of a map view in relation to the screen size of the device. This
     * is used for cache size calculations.
     * By default this returns 1.0, for a full size map view.
     *
     * @return the screen ratio of the mapview
     */
    protected float getScreenRatio() {
        return 1.0f;
    }

    /**
     * Configuration method to set if a map view activity will have zoom controls.
     *
     * @return true if the map has standard zoom controls.
     */
    protected boolean hasZoomControls() {
        return true;
    }

    /**
     * Configuration method to set if map view activity's zoom controls hide automatically.
     *
     * @return true if zoom controls hide automatically.
     */
    protected boolean isZoomControlsAutoHide() {
        return true;
    }

    /**
     * initializes the map view position.
     *
     * @param mvp the map view position to be set
     * @return the mapviewposition set
     */
    protected MapViewPosition initializePosition(MapViewPosition mvp) {
        LatLong center = mvp.getCenter();

        if (center.equals(new LatLong(0, 0))) {
            mvp.setMapPosition(this.getInitialPosition());
        }
        mvp.setZoomLevelMax(getZoomLevelMax());
        mvp.setZoomLevelMin(getZoomLevelMin());
        return mvp;
    }

    @Override
    public void onPause() {
        mapView.getModel().save(this.preferencesFacade);
        this.preferencesFacade.save();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.destroyAll();
        tileCaches.clear();
        super.onDestroy();
    }

    /**
     * Hook to purge tile caches.
     * By default we purge every tile cache that has been added to the tileCaches list.
     */
    protected void purgeTileCaches() {
        for (TileCache tileCache : tileCaches) {
            tileCache.purge();
        }
        tileCaches.clear();
    }

    protected void redrawLayers() {
        mapView.getLayerManager().redrawLayers();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidGraphicFactory.INSTANCE == null) {
            AndroidGraphicFactory.createInstance(getActivity().getApplication());
        }

    }

    protected XmlRenderTheme getRenderTheme() {
        return InternalRenderTheme.OSMARENDER;
    }

    protected void createLayers() {
        mapView.getLayerManager().getLayers().add(
                AndroidUtil.createTileRendererLayer(
                        this.tileCaches.get(0),
                        mapView.getModel().mapViewPosition,
                        getMapFile(),
                        getRenderTheme(),
                        false,
                        true));
    }

    private final byte PERMISSIONS_REQUEST_READ_STORAGE = 122;

    protected void createTileCaches() {
        this.tileCaches.add(AndroidUtil.createTileCache(
                getActivity(),
                getPersistableId(),
                this.mapView.getModel().displayModel.getTileSize(),
                this.getScreenRatio(),
                this.mapView.getModel().frameBufferModel.getOverdrawFactor(),
                true));
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_STORAGE: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showDialogWhenPermissionDenied();
                    return;
                }
                createLayers();
                createControls();
            }
        }
    }

    /**
     * Hook to check for Android Runtime Permissions.
     */
    protected void checkPermissionsAndCreateLayersAndControls() {
        if (AndroidSupportUtil.runtimePermissionRequiredForReadExternalStorage(
                getActivity(), getMapFileDirectory())) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_READ_STORAGE);
        } else {
            createLayers();
            createControls();
        }
    }

    /**
     * Sample dialog shown when permission to read storage denied.
     */
    protected void showDialogWhenPermissionDenied() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Warning");
        builder.setMessage("Without granting access to storage you will not see a map");
        builder.create().show();
    }
}
