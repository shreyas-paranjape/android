package in.co.foodamigo.customer.module.profile.view.component;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.databinding.FragmentProfileBinding;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.app.view.activity.FormActivity;
import in.co.foodamigo.customer.module.profile.view.adapter.LocationAdapter;
import model.common.Location;
import model.party.Party;

public class ProfileFragment extends Fragment {

    private Party party;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Profile");
        EventBus.getDefault().register(this);
        party = new Party();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProfileBinding rootBinding =
                FragmentProfileBinding.inflate(inflater, container, false);
        rootBinding.setParty(party);
        rootBinding.btnAddressAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForm(Constant.ADDRESS);
            }
        });
        rootBinding.ibEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForm(Constant.PERSONAL_DETAILS);
            }
        });

        rootBinding.addresses.setAdapter(
                // TODO get location for user
                new LocationAdapter(getActivity(),
                        R.layout.item_location,
                        new ArrayList<Location>()));
        return rootBinding.getRoot();
    }

    public void onEvent(LocationAdapter.LocationEditClickEvent event) {
        showForm(Constant.ADDRESS);
    }

    private void showForm(String which) {
        Intent intent = new Intent(getActivity(), FormActivity.class);
        Bundle args = new Bundle();
        args.putString(Constant.FORM, which);
        intent.putExtras(args);
        getActivity().startActivityForResult(intent, 999);
    }

}
