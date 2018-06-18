package com.example.webwerks.neostore.view.login.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.APIClient;
import com.example.webwerks.neostore.common.base.APIInterface;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.model.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    private EditText edtEmail;
    private Button btnSubmit;
    private APIInterface apiInterface;
    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void initView() {

        edtEmail=findViewById(R.id.email_id);
        btnSubmit=findViewById(R.id.email_submit);
    }

    @Override
    protected void setListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface= APIClient.getClient().create(APIInterface.class);

                Call call=apiInterface.forgotPassword(edtEmail.getText().toString());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        BaseModel model= (BaseModel) response.body();
                        Toast.makeText(ForgotPasswordActivity.this,
                                model.getUser_msg(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected boolean needActionBar() {
        return false;
    }

}
