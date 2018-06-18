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
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.BaseAsyncTask;
import com.example.webwerks.neostore.common.base.onAsyncTaskRequest;
import com.example.webwerks.neostore.model.RegistrationModel;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener,onAsyncTaskRequest {

    private TextView loginheader, forgotpass, newaccount;
    private EditText emailid, password;
    private Button login;
    private ImageView addaccount;
    private String url="http://staging.php-dev.in:8844/trainingapp/api/users/login";

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected String setTitle() {
        return null;
    }

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
    protected boolean needActionBar() {
        return false;
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
            BaseAsyncTask loginAsyncTask = new BaseAsyncTask(this,"POST",data);
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

    @Override
    public void onSuccess(String obj) {
        Gson gson=new Gson();
        RegistrationModel rgModel=gson.fromJson(obj.toString(),RegistrationModel.class);
        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences("Login_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", String.valueOf(rgModel.data.getUsername()));
        editor.putString("email", String.valueOf(rgModel.data.getEmail()));
        editor.putString("access_token", String.valueOf(rgModel.data.getAccess_token()));
        editor.commit();

        Toast.makeText(this, "Logged In successfully", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onFaliure(String obj) {
        Toast.makeText(this, "Username or password is wrong. try again", Toast.LENGTH_SHORT).show();
    }
}