package in.co.foodamigo.customer.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.profile.view.component.AddressFormFragment;

public class FormActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutId() {
        return R.layout.activity_form;
    }

    protected int getDrawerFragmentId() {
        return 0;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected Fragment getInitContent() {
        return new AddressFormFragment();
    }

    protected DrawerLayout getDrawerLayout() {
        return null;
    }


}
