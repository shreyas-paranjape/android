package in.co.foodamigo.supplier.module;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.supplier.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class loginfragment extends Fragment {


    public loginfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loginfragment, container, false);
    }


}