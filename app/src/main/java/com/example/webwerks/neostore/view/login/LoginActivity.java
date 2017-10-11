package com.example.webwerks.neostore.view.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView loginheader, forgotpass, newaccount;
    private EditText username, password;
    private Button login;
    private ImageView addaccount;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginheader=findViewById(R.id.loginheader);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        forgotpass=findViewById(R.id.forgotPass);
        newaccount=findViewById(R.id.newaccount);
        login=findViewById(R.id.login);
        addaccount=findViewById(R.id.addaccount);
    }

    @Override
    public void setListener() {
        addaccount.setOnClickListener(this);
        login.setOnClickListener(this);
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
                intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
