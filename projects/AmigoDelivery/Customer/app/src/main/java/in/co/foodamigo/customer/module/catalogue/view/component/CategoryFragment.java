package in.co.foodamigo.customer.module.catalogue.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.catalogue.view.adapter.ProductAdapter;
import model.catalogue.Product;
import model.catalogue.ProductCategory;


public class CategoryFragment extends Fragment {

    private ProductCategory productCategory;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        productCategory = (ProductCategory) args.getSerializable(Constant.PRODUCT_CATEGORY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        RecyclerView productsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_category);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Product> products = new ArrayList<>(); // TODO find products for category
        productsRecyclerView.setAdapter(new ProductAdapter(getActivity(), products));
    }
}
