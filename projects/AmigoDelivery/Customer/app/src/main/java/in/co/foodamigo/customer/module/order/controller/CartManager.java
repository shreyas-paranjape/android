package in.co.foodamigo.customer.module.order.controller;

import android.util.Log;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class CartManager {
    private static final String TAG = CartManager.class.getName();
    private final SlidingUpPanelLayout cartHolder;

    public CartManager(SlidingUpPanelLayout cartHolder) {
        this.cartHolder = cartHolder;
    }

    private void showCartPanel() {
        Log.d(TAG, "Showing cart");
        cartHolder.setPanelHeight(100);
    }

    public void collapseCartPanel() {
        if (isCartExpanded()) {
            cartHolder.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }
    }

    public void onEvent(CurrentOrderManager.CartModifiedEvent event) {
        if (event.getCartSize() == 0) {
            cartHolder.setPanelHeight(0);
            collapseCartPanel();
        } else {
            showCartPanel();
        }
    }

    public boolean isCartExpanded() {
        return SlidingUpPanelLayout.PanelState.EXPANDED == cartHolder.getPanelState();
    }
}
