package in.co.foodamigo.admin.module.catalogue.view.component.list;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import in.co.foodamigo.admin.R;

public abstract class AbstractListFragment<T> extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_list, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        ListView lvProdCat = (ListView) root.findViewById(R.id.lvGrid);
        ArrayAdapter<T> prodCatAdapter = getAdapter();
        lvProdCat.setAdapter(prodCatAdapter);
        Button btnAdd = (Button) root.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    protected abstract void add();

    protected abstract ArrayAdapter<T> getAdapter();


}
