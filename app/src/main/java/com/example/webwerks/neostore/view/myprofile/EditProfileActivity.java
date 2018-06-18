package com.example.webwerks.neostore.view.myprofile;

import android.view.Menu;
import android.view.MenuItem;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;

public class EditProfileActivity extends BaseActivity {

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.editprofile);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
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

}
