package com.goaamigo.traveller.module.product.view.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SingleChoiceDialog extends DialogFragment {

    CharSequence[] data={"Low to high","popularity","High to low","Rating"};
    int pos;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort").setSingleChoiceItems(data, pos,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        pos = position;
                        dismiss();
                    }
                });
        return builder.create();
    }
}