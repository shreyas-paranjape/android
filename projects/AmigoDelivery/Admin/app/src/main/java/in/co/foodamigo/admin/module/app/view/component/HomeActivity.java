package in.co.foodamigo.admin.module.app.view.component;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.infra.socket.SocketConnectionManager;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProductListFragment;

public class HomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, SocketConnectionManager.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getDrawerFragmentId() {
        return R.id.navigation_drawer;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected Fragment getInitContent() {
        return new ProductListFragment();
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}


