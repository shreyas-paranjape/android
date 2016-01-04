package in.co.foodamigo.customer.module.party.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.view.activity.AbstractAuthActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.party.view.fragment.LoginFragment;

public class AuthActivity extends AbstractAuthActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login / Sign up");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }

    @Override
    protected Fragment getInitContent() {
        Fragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(AbstractAuthActivity.ARG_ACCOUNT_TYPE, getApp().getAccountType());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getTitleId() {
        return R.id.tvTitle;
    }

}
