package com.goaamigo.traveller.module.product.view.Contoller;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class CartManager {
    private static final String TAG = CartManager.class.getName();
    private final SlidingUpPanelLayout cartHolder;

    public CartManager(SlidingUpPanelLayout cartHolder) {
        this.cartHolder = cartHolder;
    }

    public void collapsePanel() {
        if (!isCartCollapsed()) {
            cartHolder.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }
    }

    public void hidePanel() {
        cartHolder.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
    }

    public void onEvent(CurrentOrderManager.CartModifiedEvent event) {
        if (event.getCartSize() == 0) {
            hidePanel();
        } else {
            collapsePanel();
        }
    }

    public boolean isCartExpanded() {
        return SlidingUpPanelLayout.PanelState.EXPANDED == cartHolder.getPanelState();
    }

    public boolean isCartCollapsed() {
        return SlidingUpPanelLayout.PanelState.COLLAPSED == cartHolder.getPanelState();
    }
}
