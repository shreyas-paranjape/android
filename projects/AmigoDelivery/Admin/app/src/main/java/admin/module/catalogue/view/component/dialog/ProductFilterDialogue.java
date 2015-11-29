package admin.module.catalogue.view.component.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.util.Constant;
import com.util.IPredicate;
import com.view.fragment.AbstractFilterFragment;
import com.view.model.Filter;

import admin.module.catalogue.view.adapter.list.ProdListAdapter;
import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.R;
import model.catalogue.Product;

public class ProductFilterDialogue extends AbstractFilterFragment<Product> {

    protected void initView(View v) {
        ((TextView) v.findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.filter));
        setupToolbar((Toolbar) v.findViewById(R.id.dialogToolbar),
                android.R.drawable.ic_menu_close_clear_cancel, R.menu.menu_dialog,
                getMenuItemClickListener());
        final Button btnApply = (Button) v.findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFilter(new Filter<>("xyz", new IPredicate<Product>() {
                    @Override
                    public boolean apply(Product product) {
                        return product.getName().contains("2");
                    }
                }, null));
                dismiss();
                EventBus.getDefault().post(new ProdListAdapter.FilterEvent());
            }
        });
    }

    private void addFilter(Filter<Product> filter) {
        filters.add(filter);
    }

    @Override
    protected Object getArgumentKey() {
        return Constant.PRODUCT;
    }

    protected int getLayoutId() {
        return R.layout.fragment_product_filter;
    }


    protected int getDialogAnimation() {
        return R.style.DialogAnimation;
    }

    @NonNull
    private Toolbar.OnMenuItemClickListener getMenuItemClickListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_reset:
                        resetClicked();
                        return true;
                }
                return false;
            }
        };
    }

    private void resetClicked() {
        filters.clear();
        dismiss();
        EventBus.getDefault().post(new ProdListAdapter.FilterEvent());
    }

}
