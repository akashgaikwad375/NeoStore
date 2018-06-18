package com.example.webwerks.neostore.view.product.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.APIClient;
import com.example.webwerks.neostore.common.base.APIInterface;
import com.example.webwerks.neostore.model.BaseModel;
import com.example.webwerks.neostore.view.login.activity.ForgotPasswordActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterQuantityFragment extends DialogFragment implements View.OnClickListener {

    private Button btnBuy;
    private ImageView imgProduct;
    private TextView txtProductName;
    private EditText edtQuantity;
    private int product_id;
    private APIInterface apiInterface;

    private static final String TAG = EnterQuantityFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.enter_quantity,null);
        btnBuy= view.findViewById(R.id.btnEnterSubmit);
        imgProduct=view.findViewById(R.id.productimage);
        txtProductName=view.findViewById(R.id.productname);
        edtQuantity=view.findViewById(R.id.edt_quantity);

        if(getArguments()!=null){
            Glide.with(this).load(getArguments().getString("product_image"))
                    .into(imgProduct);
            txtProductName.setText(getArguments().getString("product_name"));
            product_id=getArguments().getInt("product_id");
        }
        btnBuy.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }


    @Override
    public void onClick(View view) {

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Login_preference", Context.MODE_PRIVATE);
        apiInterface= APIClient.getClient().create(APIInterface.class);
        Call call= apiInterface.addToCart(sharedPreferences.getString("access_token", ""),
                product_id,Integer.parseInt(edtQuantity.getText().toString()));
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
                        "fail to add to cart", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
