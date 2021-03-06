package restaurant.order.module.order.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.CurrentOrderManager;
import com.view.fragment.AbstractFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import restaurant.order.databinding.FragmentOrderStatusBinding;
import restaurant.order.module.order.view.model.OrderStatus;

public class OrderStatusFragment extends AbstractFragment {

    private CurrentOrderManager currentOrderManager;
    private OrderStatus orderStatus = new OrderStatus();
    private int i;
    private SimpleDateFormat formatter = new SimpleDateFormat("hh-mm", Locale.getDefault());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        currentOrderManager = getApp().getCurrentOrderManager();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Order status");
        if (currentOrderManager.getOrder().getPlacedAt() != null) {
            FragmentOrderStatusBinding rootBinding = FragmentOrderStatusBinding.inflate(inflater);
            orderStatus.setPlacedAt(
                    formatter.format(currentOrderManager.getOrder().getPlacedAt()));
            rootBinding.setOrderStatus(orderStatus);
            ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
            e.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    i++;
                    populateStatus(
                            new OrderStatus(
                                    calculateProgress(i, 30 - i),
                                    String.valueOf(30 - i),
                                    "Nearly there"));
                }
            }, 0, 1, TimeUnit.MINUTES);
            return rootBinding.getRoot();
        } else {
            //TODO No pending orders view
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    private void populateStatus(OrderStatus os) {
        orderStatus.setProgress(os.getProgress());
        orderStatus.setEta(os.getEta());
        orderStatus.setOrderStatus(os.getOrderStatus());
    }

    private int calculateProgress(int timeElapsed, int timeRemaining) {
        if (timeRemaining == 0) return 100;
        return (int) (((double) timeElapsed) / (timeElapsed + timeRemaining) * 100);
    }

}
