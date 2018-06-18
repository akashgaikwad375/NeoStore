package com.example.webwerks.neostore.view.login.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
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

public class ResetPasswordActivity extends BaseActivity {

    private EditText edtCurrentPassword, edtNewPassword, edtConfirmPassword;
    private Button btnReset;
    private APIInterface apiInterface;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.resetpassword);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initView() {
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnReset = findViewById(R.id.btnResetPassword);
    }

    @Override
    protected void setListener() {

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString()))
                    Toast.makeText(ResetPasswordActivity.this,
                            "Invalid password", Toast.LENGTH_SHORT).show();
                else {
                    apiInterface = APIClient.getClient().create(APIInterface.class);
                    SharedPreferences sharedPreferences = getApplicationContext().
                            getSharedPreferences("Login_preference", Context.MODE_PRIVATE);
                    Call call = apiInterface.resetPassword(sharedPreferences.getString("access_token", ""),
                            edtCurrentPassword.getText().toString(),
                            edtNewPassword.getText().toString(),
                            edtConfirmPassword.getText().toString());
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            BaseModel model = (BaseModel) response.body();
                            Toast.makeText(ResetPasswordActivity.this,
                                    model.getUser_msg(), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(ResetPasswordActivity.this,
                                    "Fail to change password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected boolean needActionBar() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
