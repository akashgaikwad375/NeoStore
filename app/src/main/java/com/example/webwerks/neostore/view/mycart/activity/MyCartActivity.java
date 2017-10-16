package com.example.webwerks.neostore.view.mycart.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.mycart.adapter.MyCartAdapter;

public class MyCartActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title;
    private MyCartAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_cart;
    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        recyclerView=findViewById(R.id.recycler_view);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter= new MyCartAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new
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
        title.setText(R.string.my_cart);
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