package admin.module.catalogue.view.component.form;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.orm.SugarRecord;
import com.util.Constant;

import java.util.List;
import java.util.Random;

import admin.module.catalogue.view.adapter.spinner.ProdCatSpinnerAdapter;
import in.co.foodamigo.admin.R;
import model.catalogue.ProductCategory;

public class ProdCatFormFragment extends Fragment {

    private List<ProductCategory> productCategories;
    private ArrayAdapter<ProductCategory> prodCatAdapter;

    private ProductCategory productCategory;
    private Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_prod_cat, container, false);
        initData();
        initView(root);
        return root;
    }


    private void initData() {
        productCategories = SugarRecord.listAll(ProductCategory.class);
        productCategory =
                (ProductCategory) getArguments().getSerializable(Constant.PRODUCT_CATEGORY);
        if (productCategory == null) {
            productCategory = new ProductCategory();
            productCategory.setId(random.nextInt());
        }
    }

    private void initView(View root) {
        final EditText etTitle = (EditText) root.findViewById(R.id.etTitle);
        etTitle.setText(productCategory.getName());
        final Spinner spParentCategory = (Spinner) root.findViewById(R.id.spParentCategory);
        int index = getIndexFor(productCategory);
        if (index != 0) {
            spParentCategory.setSelection(index);
        }
        prodCatAdapter = new ProdCatSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                productCategories);
        spParentCategory.setAdapter(prodCatAdapter);
        Button btnSave = (Button) root.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCategory parent = (ProductCategory) spParentCategory.getSelectedItem();
                productCategory.setParent(parent);
                productCategory.setName(etTitle.getText().toString());
                SugarRecord.save(productCategory);
                refreshSpinner();
            }
        });

    }

    private void refreshSpinner() {
        productCategories.clear();
        productCategories.addAll(SugarRecord.listAll(ProductCategory.class));
        prodCatAdapter.notifyDataSetChanged();
    }

    private int getIndexFor(ProductCategory productCategory) {
        for (ProductCategory category : productCategories) {
            if (category.getId() == (productCategory.getId())) {
                return productCategories.indexOf(category);
            }
        }
        return 0;
    }


}
