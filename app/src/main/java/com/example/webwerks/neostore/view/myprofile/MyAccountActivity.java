package com.example.webwerks.neostore.view.myprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.address.activity.AddAddressActivity;
import com.example.webwerks.neostore.view.login.ResetPasswordActivity;

public class MyAccountActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView title;
    private Button btnResetPassword,btnEditProfile;
    @Override
    protected int getContentView() {
        return R.layout.activity_my_account;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
        btnResetPassword=findViewById(R.id.btnResetPassword);
        btnEditProfile=findViewById(R.id.btnEditProfile);

    }

    @Override
    protected void setListener() {
        btnResetPassword.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);

    }

    @Override
    protected void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(R.string.my_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: onBackPressed();
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.btnResetPassword:
                i=new Intent(MyAccountActivity.this, ResetPasswordActivity.class);
                startActivity(i);
                break;
            case R.id.btnEditProfile:
                i=new Intent(MyAccountActivity.this, EditProfileActivity.class);
                startActivity(i);
                break;
        }

    }
}
