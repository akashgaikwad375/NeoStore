package com.example.webwerks.neostore.view.product.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.product.adapter.ProductListingAdapter;

public class ProductListingActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title;
    private ProductListingAdapter mAdapter;
    private RecyclerView productList;

    @Override
    protected int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        productList=findViewById(R.id.recycler_view);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        mAdapter= new ProductListingAdapter(this);
        productList.setAdapter(mAdapter);
        productList.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
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
        String s=getIntent().getStringExtra("Title");
        title.setText(s);
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
