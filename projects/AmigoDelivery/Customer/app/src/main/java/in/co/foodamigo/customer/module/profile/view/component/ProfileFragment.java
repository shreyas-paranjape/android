package in.co.foodamigo.customer.module.profile.view.component;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import delivery.model.profile.Party;
import delivery.repository.PartyRepo;
import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.databinding.FragmentProfileBinding;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.app.view.component.FormActivity;
import in.co.foodamigo.customer.module.app.view.event.ChangeContentEvent;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.profile.view.adapter.AddressAdapter;

public class ProfileFragment extends Fragment {

    private Party party;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Profile");
        EventBus.getDefault().register(this);
        party = new Party(1, "Shreyas Paranjape", "");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu:
                showMenuView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMenuView() {
        EventBus.getDefault().post(new ChangeContentEvent(ChangeContentEvent.ContentType.FRAGMENT, null) {
            @Override
            public Class getContentClass() {
                return MenuFragment.class;
            }
        });
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
                new AddressAdapter(getActivity(),
                        R.layout.item_address,
                        PartyRepo.getAddresses(party)));
        return rootBinding.getRoot();
    }

    public void onEvent(AddressAdapter.AddressEditClickEvent event) {
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
