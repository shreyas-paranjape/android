package in.co.foodamigo.admin.module.catalogue.view.component.form;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.orm.SugarRecord;

import java.util.Random;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.model.Supplier;

public class SupplierFormFragment extends Fragment {

    private Supplier supplier;
    private Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_prod, container, false);
        initData();
        initView(root);
        return root;
    }

    private void initView(View root) {
        final EditText etTitle = (EditText) root.findViewById(R.id.etTitle);
        final EditText etImageUrl = (EditText) root.findViewById(R.id.etImageUrl);
        final Button btnSave = (Button) root.findViewById(R.id.btnSave);

        etTitle.setText(supplier.getName());
        etImageUrl.setText(supplier.getImageUrl());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplier.setName(etTitle.getText().toString());
                supplier.setImageUrl(etImageUrl.getText().toString());
                SugarRecord.save(supplier);
            }
        });
    }

    private void initData() {
        supplier =
                (Supplier) getArguments().getSerializable(Constant.SUPPLIER);
        if (supplier == null) {
            supplier = new Supplier();
            supplier.setId(random.nextInt());
        }
    }
}
