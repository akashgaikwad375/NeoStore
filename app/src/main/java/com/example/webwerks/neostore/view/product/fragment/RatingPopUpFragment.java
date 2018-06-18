package com.example.webwerks.neostore.view.product.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.APIClient;
import com.example.webwerks.neostore.common.base.APIInterface;
import com.example.webwerks.neostore.model.BaseModel;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webwerks on 11/10/17.
 */

public class RatingPopUpFragment extends DialogFragment implements View.OnClickListener {

    private TextView txtProductName;
    private ImageView imgProduct;
    private RatingBar rbProductRating;
    private Button btnRate;
    private APIInterface apiInterface;
    private String product_id;
    private static final String TAG = RatingPopUpFragment.class.getSimpleName();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.rating_pop_up, null);

        txtProductName=view.findViewById(R.id.productname);
        imgProduct=view.findViewById(R.id.productimage);
        rbProductRating=view.findViewById(R.id.rating);

        if(getArguments()!=null){
            Glide.with(this).load(getArguments().getString("product_image"))
                    .into(imgProduct);
            txtProductName.setText(getArguments().getString("product_name"));
            product_id=getArguments().getString("product_id");
        }
        btnRate = view.findViewById(R.id.rate_now);
        btnRate.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        apiInterface= APIClient.getClient().create(APIInterface.class);
        Call call= apiInterface.productRating(product_id,(int)rbProductRating.getRating());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                BaseModel model= (BaseModel) response.body();
                Log.e(TAG, "onResponse: "+model.getStatus()+ " "+model.getUser_msg() );
                Toast.makeText(getActivity(),
                        model.getUser_msg(), Toast.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(),
                        "fail to add rating", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}