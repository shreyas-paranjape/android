package in.co.foodamigo.customer.module.order.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.view.fragment.AbstractFragment;

import in.co.foodamigo.customer.R;

public class PastOrderFragment extends AbstractFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_past_order, container, false);
    }

}
