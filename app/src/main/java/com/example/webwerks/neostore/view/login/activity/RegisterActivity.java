package com.example.webwerks.neostore.view.login.activity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView terms, agree, header, gender, male, female;
    private EditText firstname,lastname,email,confirmPass, password,phone;
    private Button register;

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        agree=findViewById(R.id.agree);
        terms=findViewById(R.id.terms);
        header=findViewById(R.id.registerheader);
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        confirmPass=findViewById(R.id.confirmpass);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.register);

        toolbar=findViewById(R.id.toolbar);
        terms.setPaintFlags(terms.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

    }


    @Override
    public void setListener() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: onBackPressed();
                                    break;
        }
        return true;
    }
}
