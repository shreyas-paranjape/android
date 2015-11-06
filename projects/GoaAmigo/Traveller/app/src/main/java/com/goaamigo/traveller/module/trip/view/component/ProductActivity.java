package com.goaamigo.traveller.module.trip.view.component;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.goaamigo.traveller.R;
import com.view.activity.AbstractActivity;

public class ProductActivity extends AbstractActivity {
    Spinner activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        activity = (Spinner) findViewById(R.id.spinner_activity);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.Menu_Search_Content, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        activity.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_filter:
                break;
            case R.id.action_maps:
                break;
            case R.id.action_sort:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected int getDrawerFragmentId() {
        return 0;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }
}
