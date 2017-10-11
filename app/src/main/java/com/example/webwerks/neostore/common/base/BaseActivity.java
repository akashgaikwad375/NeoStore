package com.example.webwerks.neostore.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        setListener();
        setActionBar();
    }

    abstract protected int getContentView();
    abstract protected void initView();
    abstract protected void setListener();
    abstract protected void setActionBar();
}
