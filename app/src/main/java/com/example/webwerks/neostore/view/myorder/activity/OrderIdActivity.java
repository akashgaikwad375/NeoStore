package com.example.webwerks.neostore.view.myorder.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.myorder.adapter.OrderIdAdapter;

public class OrderIdActivity extends BaseActivity {

    private OrderIdAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected String setTitle() {
        return "Order Id : "+getIntent().getStringExtra("Data");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_id;
    }

    @Override
    protected void initView() {
        recyclerView=findViewById(R.id.recycler_view);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter= new OrderIdAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
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
