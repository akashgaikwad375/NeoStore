package com.example.webwerks.neostore.view.address.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.address.adapter.AddressListAdapter;

public class AddressListActivity extends BaseActivity {

    private AddressListAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.add_address);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_address_list;
    }

    @Override
    public void initView() {
        recyclerView=findViewById(R.id.recycler_view);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter= new AddressListAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

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
}
