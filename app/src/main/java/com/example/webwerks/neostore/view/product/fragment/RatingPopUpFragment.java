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

public class RatingPopUpFragment extends DialogFragment {
    private Button rate;
    private static final String TAG = RatingPopUpFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.rating_pop_up,container,false);
        rate=view.findViewById(R.id.rate_now);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, "onClick: buy" );
                Toast.makeText(getContext(), "Order Placed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), HomeActivity.class);
                getActivity().startActivity(i);
                getDialog().dismiss();

            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.rating_pop_up,null));
        return builder.create();
    }


}
