package com.example.webwerks.neostore.view.login;

import android.app.Activity;
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
import com.example.webwerks.neostore.view.home.HomeActivity;

public class LoginActivity extends BaseActivity {

    private TextView loginheader, forgotpass, newaccount;
    private EditText username, password;
    private Button login;
    private ImageView addaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/gotham-bold-59d3606e4a6a1.otf");
        Typeface typeface1=Typeface.createFromAsset(getAssets(),"fonts/gotham-medium-59d36183eb56f.otf");
        loginheader.setTypeface(typeface);
        username.setTypeface(typeface1);
        password.setTypeface(typeface1);
        forgotpass.setTypeface(typeface1);
        newaccount.setTypeface(typeface1);
        login.setTypeface(typeface1);

        addaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }

    private void initView() {
        loginheader=findViewById(R.id.loginheader);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        forgotpass=findViewById(R.id.forgotPass);
        newaccount=findViewById(R.id.newaccount);
        login=findViewById(R.id.login);
        addaccount=findViewById(R.id.addaccount);

    }
}
