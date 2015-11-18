package restaurant.order.module.app.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import restaurant.order.R;


public abstract class FormFragment extends Fragment {

    private InputMethodManager inputMethodManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        showKeyboard();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.form_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideKeyboard();
        switch (item.getItemId()) {
            case R.id.item_save:
                onSaveClick();
                return true;
            case android.R.id.home:
                hideKeyboard();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showKeyboard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void onSaveClick() {
        hideKeyboard();
        ValidationResult result = validate();
        if (result.isValidated()) {
            saveChanges();
        } else {
            Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    protected abstract ValidationResult validate();

    protected abstract void saveChanges();

    protected class ValidationResult {
        private final boolean isValidated;
        private final String msg;


        public ValidationResult(boolean isValidated, String validationMessage) {
            this.isValidated = isValidated;
            this.msg = validationMessage;
        }

        public String getMsg() {
            return msg;
        }

        public boolean isValidated() {
            return isValidated;
        }
    }

}
