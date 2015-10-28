package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goaamigo.traveller.R;

public class FeedbackFragment extends Fragment {
    Button maps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        initView(v);
        return v;
    }

    private void initView(View view) {
        final Intent intent = new Intent(getActivity(),MapsActivity.class);

        maps = (Button)view.findViewById(R.id.btnMaps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
