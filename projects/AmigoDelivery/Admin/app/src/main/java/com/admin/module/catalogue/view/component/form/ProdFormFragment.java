package com.admin.module.catalogue.view.component.form;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.admin.module.catalogue.view.adapter.spinner.ProdCatSpinnerAdapter;
import com.admin.module.party.view.adapter.SupplierSpinnerAdapter;
import com.orm.SugarRecord;
import com.util.Constant;

import java.util.List;
import java.util.Random;

import com.admin.R;
import model.catalogue.Product;
import model.catalogue.ProductCategory;
import model.party.Party;

public class ProdFormFragment extends Fragment {

    private List<ProductCategory> productCategories;
    private List<Party> suppliers;

    private Product product;
    private Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_prod, container, false);
        initData();
        initView(root);
        return root;
    }


    private void initData() {
        productCategories = SugarRecord.listAll(ProductCategory.class);
        suppliers = SugarRecord.listAll(Party.class);
        product =
                (Product) getArguments().getSerializable(Constant.PRODUCT);
        if (product == null) {
            product = new Product();
            product.setId(random.nextInt());
        }
    }

    private void initView(View root) {
        final EditText etTitle = (EditText) root.findViewById(R.id.etTitle);
        final EditText etDescription = (EditText) root.findViewById(R.id.etDescription);
        final EditText etRate = (EditText) root.findViewById(R.id.etRate);
        final EditText etImageUrl = (EditText) root.findViewById(R.id.etImageUrl);
        final Spinner spParentCategory = getCategorySpinner(root);
        final Spinner spSupplier = getSupplierSpinner(root);
        final Button btnSave = (Button) root.findViewById(R.id.btnSave);

        etTitle.setText(product.getName());
        //etDescription.setText(product.getDescription());
        //etRate.setText(String.valueOf(product.getPrice()));
        //etImageUrl.setText(product.getImageUrl());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setName(etTitle.getText().toString());
                //product.setDescription(etDescription.getText().toString());
                //product.setPrice(Integer.valueOf(etRate.getText().toString()));
                //product.setImageUrl(etImageUrl.getText().toString());
                product.setProductCategory((ProductCategory) spParentCategory.getSelectedItem());
                //product.setProductSupplier((Supplier) spSupplier.getSelectedItem());
                SugarRecord.save(product);
            }
        });

    }

    @NonNull
    private Spinner getCategorySpinner(View root) {
        final Spinner spParentCategory = (Spinner) root.findViewById(R.id.spParentCategory);
        spParentCategory.setSelection(getIndexFor(product.getProductCategory()));
        ArrayAdapter<ProductCategory> prodCatAdapter = new ProdCatSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                productCategories);
        spParentCategory.setAdapter(prodCatAdapter);
        return spParentCategory;
    }

    @NonNull
    private Spinner getSupplierSpinner(View root) {
        final Spinner spSuppliers = (Spinner) root.findViewById(R.id.spSupplier);
        // spSuppliers.setSelection(getIndexFor(product.getProductSupplier()));
        ArrayAdapter<Party> supplierAdapter = new SupplierSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                suppliers);
        spSuppliers.setAdapter(supplierAdapter);
        return spSuppliers;
    }


    private int getIndexFor(ProductCategory productCategory) {
        if (productCategory != null) {
            for (ProductCategory category : productCategories) {
                if (category.getId() == (productCategory.getId())) {
                    return productCategories.indexOf(category);
                }
            }
        }
        return 0;
    }

    private int getIndexFor(Party productSupplier) {
        if (productSupplier != null) {
            for (Party supplier : suppliers) {
                if (supplier.getId() == (productSupplier.getId())) {
                    return suppliers.indexOf(supplier);
                }
            }
        }
        return 0;
    }


}
