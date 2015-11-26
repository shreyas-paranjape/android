package com.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cache.ObjectCache;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilterFragment<T> extends DialogFragment {

    protected List<Filter<T>> filters = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    protected void setupToolbar(Toolbar toolbar, int backDrawable, int menu,
                                Toolbar.OnMenuItemClickListener listener) {
        toolbar.setNavigationIcon(getResources().getDrawable(backDrawable));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.setOnMenuItemClickListener(listener);
        toolbar.inflateMenu(menu);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getDialogAnimation() != 0) {
            dialog.getWindow().getAttributes().windowAnimations = getDialogAnimation();
        }
        return dialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object cachedFilters = ObjectCache.get(getArgumentKey());
        if (cachedFilters != null) {
            filters = (List<Filter<T>>) cachedFilters;
        } else {
            filters = new ArrayList<>();
            ObjectCache.put(getArgumentKey(), filters);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        initView(root);
        return root;
    }

    protected int getDialogAnimation() {
        return 0;
    }

    protected abstract Object getArgumentKey();

    protected abstract void initView(View root);

    protected abstract int getLayoutId();

}
