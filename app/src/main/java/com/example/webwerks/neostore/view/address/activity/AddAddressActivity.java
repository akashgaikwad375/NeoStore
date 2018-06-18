package com.example.webwerks.neostore.view.address.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private Button btnSaveAddress;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.add_address);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initView() {
        btnSaveAddress=findViewById(R.id.btnSaveAddress);
    }

    @Override
    public void setListener() {
        btnSaveAddress.setOnClickListener(this);
    }

    @Override
    protected boolean needActionBar() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(AddAddressActivity.this, AddressListActivity.class);
        startActivity(i);
    }
}
