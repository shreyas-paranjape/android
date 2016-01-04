package supplier.module.app.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import supplier.R;
import supplier.module.app.view.fragment.NewPickUpOrderFragment;
import supplier.module.party.view.activity.AuthActivity;

public class HomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isLoggedIn()) {
            requestAuth(AuthActivity.class);
        }
    }

    protected void onLoginSuccess(Intent data) {
    }

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