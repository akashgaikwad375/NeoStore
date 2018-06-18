package com.example.webwerks.neostore.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.webwerks.neostore.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        initView();
        if(needActionBar()){
            Toolbar toolbar=findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView title = toolbar.findViewById(R.id.title);
            title.setText(setTitle());
        }
        setListener();
    }

    abstract protected String setTitle();
    abstract protected int getContentView();
    abstract protected void initView();
    abstract protected void setListener();
    abstract protected boolean needActionBar();
}
