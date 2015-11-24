package in.co.foodamigo.admin.module.catalogue.view.component.dialog;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.view.fragment.AbstractFilterFragment;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import model.catalogue.Product;

public class ProductFilterDialogue extends AbstractFilterFragment<Product> {

    protected void initView(View v) {
        ((TextView) v.findViewById(R.id.toolbar_title)).setText("Filter");
        setupToolbar((Toolbar) v.findViewById(R.id.dialogToolbar),
                android.R.drawable.ic_menu_close_clear_cancel, R.menu.menu_dialog,
                new Toolbar.OnMenuItemClickListener() {
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
                });
    }

    private void resetClicked() {
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

}
