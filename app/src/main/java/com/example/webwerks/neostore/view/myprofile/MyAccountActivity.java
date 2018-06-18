package com.example.webwerks.neostore.view.myprofile;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.login.activity.ResetPasswordActivity;

public class MyAccountActivity extends BaseActivity implements View.OnClickListener {

    private Button btnResetPassword,btnEditProfile;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.my_account);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_account;
    }

    @Override
    protected void initView() {
        btnResetPassword=findViewById(R.id.btnResetPassword);
        btnEditProfile=findViewById(R.id.btnEditProfile);
    }

    @Override
    protected void setListener() {
        btnResetPassword.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
    }

    @Override
    protected boolean needActionBar() {
        return true;
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
