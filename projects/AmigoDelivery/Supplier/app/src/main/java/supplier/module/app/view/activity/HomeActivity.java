package supplier.module.app.view.activity;

import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import supplier.R;
import supplier.module.app.view.fragment.NewPickUpOrderFragment;

public class HomeActivity extends AbstractActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected int getDrawerFragmentId() {
        return R.id.drawer;
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment getInitContent() {
        return new NewPickUpOrderFragment();
    }
}
