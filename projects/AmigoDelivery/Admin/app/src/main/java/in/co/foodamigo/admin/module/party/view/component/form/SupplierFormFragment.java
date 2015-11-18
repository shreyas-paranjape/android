package in.co.foodamigo.admin.module.party.view.component.form;

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
import model.party.Party;

public class SupplierFormFragment extends Fragment {

    private Party party;
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

        etTitle.setText(party.getName());
        //etImageUrl.setText(party.getImageUrl());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                party.setName(etTitle.getText().toString());
                //party.setImageUrl(etImageUrl.getText().toString());
                SugarRecord.save(party);
            }
        });
    }

    private void initData() {
        party = (Party) getArguments().getSerializable(Constant.SUPPLIER);
        if (party == null) {
            party = new Party();
            party.setId(random.nextInt());
        }
    }
}
