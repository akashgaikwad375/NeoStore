package com.example.webwerks.neostore.view.login;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;

public class ResetPasswordActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title;
    @Override
    protected int getContentView() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(R.string.resetpassword);
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
