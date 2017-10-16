package com.example.webwerks.neostore.view.product.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;

/**
 * Created by webwerks on 11/10/17.
 */

public class RatingPopUpFragment extends DialogFragment implements View.OnClickListener {
    private Button btnRate;
    private static final String TAG = RatingPopUpFragment.class.getSimpleName();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.rating_pop_up, null);
        btnRate = view.findViewById(R.id.rate_now);
        btnRate.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        Log.e(TAG, "onClick: dialog 2");
        Toast.makeText(getActivity(), "Dialog 2", Toast.LENGTH_SHORT).show();
    }
}