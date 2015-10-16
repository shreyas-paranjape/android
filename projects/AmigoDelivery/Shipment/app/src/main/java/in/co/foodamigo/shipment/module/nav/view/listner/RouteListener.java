package in.co.foodamigo.shipment.module.nav.view.listner;

import com.skobbler.ngx.navigation.SKNavigationManager;
import com.skobbler.ngx.navigation.SKNavigationSettings;
import com.skobbler.ngx.routing.SKRouteInfo;
import com.skobbler.ngx.routing.SKRouteJsonAnswer;
import com.skobbler.ngx.routing.SKRouteListener;

public class RouteListener implements SKRouteListener {

    @Override
    public void onRouteCalculationCompleted(SKRouteInfo skRouteInfo) {

    }

    @Override
    public void onRouteCalculationFailed(SKRoutingErrorCode skRoutingErrorCode) {

    }

    @Override
    public void onAllRoutesCompleted() {
        SKNavigationSettings navigationSettings = new SKNavigationSettings();
        navigationSettings.setNavigationType(SKNavigationSettings.SKNavigationType.REAL);

        SKNavigationManager navigationManager = SKNavigationManager.getInstance();
        //navigationManager.setMapView(mapView);
        navigationManager.setNavigationListener(new NavigationListener());
        navigationManager.startNavigation(navigationSettings);
    }

    @Override
    public void onServerLikeRouteCalculationCompleted(SKRouteJsonAnswer skRouteJsonAnswer) {

    }

    @Override
    public void onOnlineRouteComputationHanging(int i) {

    }
}
