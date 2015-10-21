package in.co.foodamigo.customer.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import java.util.HashMap;
import java.util.Map;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.profile.view.component.AddressFormFragment;
import in.co.foodamigo.customer.module.profile.view.component.PersonalDetailsFormFragment;

public class FormActivity extends AbstractActivity {

    private final Map<String, Fragment> formFragmentMapping = new HashMap<>();

    public FormActivity() {
        formFragmentMapping.put(Constant.PERSONAL_DETAILS, new PersonalDetailsFormFragment());
        formFragmentMapping.put(Constant.ADDRESS, new AddressFormFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutId() {
        return R.layout.activity_form;
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

    protected int getDrawerFragmentId() {
        return 0;
    }

    protected DrawerLayout getDrawerLayout() {
        return null;
    }


}
