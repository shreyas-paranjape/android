package in.co.foodamigo.customer.module.app.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import in.co.foodamigo.customer.databinding.FragmentInitInfoBinding;
import in.co.foodamigo.customer.module.app.view.activity.HomeActivity;
import in.co.foodamigo.customer.module.app.view.model.Tutorial;


public class InitTutorialPageFragment extends Fragment {

    private static final String ARG_TUTORIAL = "tutorial";
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
        //TODO Load Image
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
