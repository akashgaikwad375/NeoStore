package com.example.webwerks.neostore.view.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.HttpPostAsyncTask;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView loginheader, forgotpass, newaccount;
    private EditText emailid, password;
    private Button login;
    private ImageView addaccount;
    private String url="http://staging.php-dev.in:8844/trainingapp/api/users/login";

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginheader=findViewById(R.id.loginheader);
        emailid=findViewById(R.id.edtEmail);
        password=findViewById(R.id.password);
        forgotpass=findViewById(R.id.forgotPass);
        newaccount=findViewById(R.id.newaccount);
        login=findViewById(R.id.login);
        addaccount=findViewById(R.id.addaccount);

        SharedPreferences sharedPreferences= getApplicationContext().
                getSharedPreferences("Login_preference", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("username")){
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void setListener() {

        addaccount.setOnClickListener(this);
        login.setOnClickListener(this);
        forgotpass.setOnClickListener(this);
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.addaccount:
                intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                validate();
                break;
            case R.id.forgotPass:
                intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void validate() {
        if(!TextUtils.isEmpty(emailid.getText().toString()) && !TextUtils.isEmpty(password.getText().toString()))
        {
            Map<String, Object> data = new HashMap<>();
            data.put("email", emailid.getText().toString());
            data.put("password", password.getText().toString());
            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(data,this);
            loginAsyncTask.execute(url);

        }else {
            if (TextUtils.isEmpty(emailid.getText().toString())) {
                emailid.setError("Username is required");
            }
            if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("Password is required");
            }
        }
    }
}