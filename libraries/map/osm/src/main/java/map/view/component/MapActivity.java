package map.view.component;

import android.os.Bundle;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.util.MapViewerTemplateRuntimePermissions;
import org.mapsforge.map.rendertheme.InternalRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderTheme;


public abstract class MapActivity extends MapViewerTemplateRuntimePermissions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidGraphicFactory.createInstance(getApplication());
        super.onCreate(savedInstanceState);
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

    protected void createTileCaches() {
        this.tileCaches.add(AndroidUtil.createTileCache(
                this,
                getPersistableId(),
                this.mapView.getModel().displayModel.getTileSize(),
                this.getScreenRatio(),
                this.mapView.getModel().frameBufferModel.getOverdrawFactor(),
                true));
    }
}
