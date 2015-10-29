package in.co.foodamigo.customer.module.app.view.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.view.activity.AbstractActivity;

import java.util.HashMap;
import java.util.Map;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.profile.view.component.AddressFormFragment;
import in.co.foodamigo.customer.module.profile.view.component.PersonalDetailsFormFragment;

public class FormActivity extends AbstractActivity {

    private final Map<String, Fragment> formFragmentMapping = new HashMap<>();
    private InputMethodManager inputMethodManager;

    public FormActivity() {
        formFragmentMapping.put(Constant.PERSONAL_DETAILS, new PersonalDetailsFormFragment());
        formFragmentMapping.put(Constant.ADDRESS, new AddressFormFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                hideKeyboard();
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_layout_container;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected Fragment getInitContent() {
        return formFragmentMapping.get(
                getIntent().getExtras().getString(Constant.FORM)
        );
    }

    @Override
    protected int getTitleId() {
        return R.id.tvTitle;
    }
}
