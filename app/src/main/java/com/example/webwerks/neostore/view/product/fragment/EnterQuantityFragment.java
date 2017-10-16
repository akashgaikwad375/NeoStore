package com.example.webwerks.neostore.view.product.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.webwerks.neostore.R;

public class EnterQuantityFragment extends DialogFragment implements View.OnClickListener {

    private Button btnBuy;
    private static final String TAG = EnterQuantityFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.enter_quantity,null);
        btnBuy= view.findViewById(R.id.btnEnterSubmit);
        btnBuy.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }


    @Override
    public void onClick(View view) {
        Log.e(TAG, "onClick: dialog 1" );
        Toast.makeText(getActivity(), "Dialog 1", Toast.LENGTH_SHORT).show();
    }
}
