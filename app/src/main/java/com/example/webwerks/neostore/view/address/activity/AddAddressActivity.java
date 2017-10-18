package com.example.webwerks.neostore.view.address.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView title;
    private Button btnSaveAddress;

    @Override
    public int getContentView() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        title = toolbar.findViewById(R.id.title);
        btnSaveAddress=findViewById(R.id.btnSaveAddress);
    }

    @Override
    public void setListener() {
        btnSaveAddress.setOnClickListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(R.string.add_address);
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
