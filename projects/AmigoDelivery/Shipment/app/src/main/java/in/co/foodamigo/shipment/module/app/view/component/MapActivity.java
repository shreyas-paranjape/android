package in.co.foodamigo.shipment.module.app.view.component;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;


import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.util.MapViewerTemplate;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderTheme;

import java.io.File;

import in.co.foodamigo.shipment.R;

public class MapActivity extends MapViewerTemplate {

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getMapViewId() {
        return R.id.mapView;
    }

    protected String getMapFileName() {
        return "panaji.map";
    }

    protected XmlRenderTheme getRenderTheme() {
        return InternalRenderTheme.OSMARENDER;
    }

    protected void createLayers() {
        this.mapView.getLayerManager().getLayers().add(
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

    @Override
    protected MapPosition getDefaultInitialPosition() {
        return new MapPosition(new LatLong(15.4989, 73.8278), (byte) 12);
    }
}
