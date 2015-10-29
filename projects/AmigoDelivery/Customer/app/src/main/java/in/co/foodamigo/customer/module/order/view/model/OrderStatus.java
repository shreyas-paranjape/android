package in.co.foodamigo.customer.module.order.view.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import in.co.foodamigo.customer.BR;

public class OrderStatus extends BaseObservable {

    private int progress;
    private String eta;
    private String orderStatus;
    private String placedAt;

    public OrderStatus() {

    }

    public OrderStatus(int progress, String eta, String orderStatus) {
        this.progress = progress;
        this.eta = eta;
        this.orderStatus = orderStatus;
    }

    @Bindable
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }

    @Bindable
    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
        notifyPropertyChanged(BR.eta);
    }

    @Bindable
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        notifyPropertyChanged(BR.orderStatus);
    }

    public String getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(String placedAt) {
        this.placedAt = placedAt;
    }
}
