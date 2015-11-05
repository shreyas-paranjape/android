package map.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ResourceUtil;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.model.MapViewPosition;

import java.util.List;

public class Util {


    public static Bitmap viewToBitmap(Context c, View view) {
        view.measure(View.MeasureSpec.getSize(view.getMeasuredWidth()),
                View.MeasureSpec.getSize(view.getMeasuredHeight()));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Drawable drawable = new BitmapDrawable(c.getResources(),
                android.graphics.Bitmap.createBitmap(view.getDrawingCache()));
        view.setDrawingCacheEnabled(false);
        return AndroidGraphicFactory.convertToBitmap(drawable);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static Marker createMarker(Context c, int resourceIdentifier,
                               LatLong latLong) {
        Drawable drawable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? c.getDrawable(resourceIdentifier) : c.getResources().getDrawable(resourceIdentifier);
        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
        return new Marker(latLong, bitmap, 0, -bitmap.getHeight() / 2);
    }

    static Paint createPaint(int color, int strokeWidth, Style style) {
        Paint paint = AndroidGraphicFactory.INSTANCE.createPaint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        return paint;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static Marker createTappableMarker(final Context c, int resourceIdentifier,
                                       LatLong latLong) {
        Drawable drawable =
                Build.VERSION.SDK_INT >=
                        Build.VERSION_CODES.LOLLIPOP ?
                        c.getDrawable(resourceIdentifier) :
                        c.getResources().getDrawable(resourceIdentifier);
        if (drawable != null) {
            Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
            bitmap.incrementRefCount();
            return new Marker(latLong, bitmap, 0, -bitmap.getHeight() / 2) {
                @Override
                public boolean onTap(LatLong geoPoint, Point viewPosition,
                                     Point tapPoint) {
                    if (contains(viewPosition, tapPoint)) {
                        Toast.makeText(c,
                                "The Marker was tapped " + geoPoint.toString(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }
            };
        }
        return null;
    }


    public static Bitmap createBalloonMarker(Context context, String text, int balloonResId) {
        TextView bubbleView = new TextView(context);
        ResourceUtil.setBackground(context, bubbleView, balloonResId);
        bubbleView.setGravity(Gravity.CENTER);
        bubbleView.setMaxEms(20);
        bubbleView.setTextSize(15);
        bubbleView.setTextColor(Color.BLACK);
        bubbleView.setText(text);
        bubbleView.setPadding(50, 0, 50, 10);
        Bitmap bubble = Util.viewToBitmap(context, bubbleView);
        bubble.incrementRefCount();
        return bubble;
    }

    public static Polyline createPolyLine(List<LatLong> points) {
        Paint paintStroke =
                createPaint(
                        AndroidGraphicFactory.INSTANCE.createColor(
                                org.mapsforge.core.graphics.Color.GREEN), 1,
                        Style.STROKE);
        paintStroke.setDashPathEffect(new float[]{0, 0});
        paintStroke.setStrokeWidth(5);
        Polyline line = new Polyline(paintStroke, AndroidGraphicFactory.INSTANCE);
        line.getLatLongs().addAll(points);
        return line;
    }

    public static void addLayer(MapView mapView, Layer layer) {
        mapView.getLayerManager().getLayers().add(layer);
    }


    public static void addMarker(MapView mapView, Bitmap background, LatLong location) {
        addLayer(mapView, new Marker(location, background, 0, -background.getHeight() / 2) {
            @Override
            public boolean onTap(LatLong geoPoint, Point viewPosition,
                                 Point tapPoint) {
                if (contains(viewPosition, tapPoint)) {
                    Log.i("MARKER", "Marker tapped");
                    return true;
                }
                return false;
            }
        });
    }


    public static void addBalloonMarker(Context context, MapView mapView,
                                        LatLong location, String text, int balloonResId) {
        addMarker(mapView, createBalloonMarker(context, text, balloonResId), location);
    }

    public static void addLine(MapView mapView, List<LatLong> points) {
        addLayer(mapView, createPolyLine(points));
    }

    public static MapViewPosition getCurrentMapPosition(MapView mapView) {
        return mapView.getModel().mapViewPosition;
    }


    private Util() {
        throw new IllegalStateException();
    }
}
