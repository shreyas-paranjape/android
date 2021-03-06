package restaurant.order.module.app.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import restaurant.order.databinding.FragmentInitInfoBinding;
import restaurant.order.module.app.view.activity.HomeActivity;
import restaurant.order.module.app.view.model.Tutorial;


public class InitTutorialPageFragment extends Fragment {

    private static final String ARG_TUTORIAL = "tutorial";
    private static final String TAG = InitTutorialPageFragment.class.getName();
    private Tutorial tutorial;

    public static InitTutorialPageFragment newInstance(Serializable tutorial) {
        InitTutorialPageFragment fragment = new InitTutorialPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TUTORIAL, tutorial);
        fragment.setArguments(args);
        return fragment;
    }

    public InitTutorialPageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tutorial = (Tutorial) getArguments().getSerializable(ARG_TUTORIAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentInitInfoBinding rootBinding = FragmentInitInfoBinding.inflate(inflater, container, false);
        rootBinding.setTutorial(tutorial);
        /*Picasso.with(getActivity())
                .load(tutorial.getUrl())
                .into(rootBinding.imgTutorial, new Callback.EmptyCallback() {
                    @Override
                    public void onError() {
                        Log.d(TAG, "Could not load image");
                    }
                });*/
        if (tutorial.isShowHome()) {
            rootBinding.btnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent skipToHome = new Intent(getActivity(), HomeActivity.class);
                    getActivity().startActivity(skipToHome);
                    getActivity().finish();
                }
            });
        } else {
            rootBinding.btnSkip.setVisibility(View.GONE);
        }

        return rootBinding.getRoot();
    }
}
