package com.example.webwerks.neostore.view.product;

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
import com.example.webwerks.neostore.common.base.CustomAdapter;

import java.util.ArrayList;

public class ProductListingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView title;

    private ArrayList<String> list=new ArrayList<>();
    private CustomAdapter mAdapter;
    private RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s=getIntent().getStringExtra("Title");
        title.setText(s);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        mAdapter= new CustomAdapter(list);
        productList.setAdapter(mAdapter);
        productList.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        productList=findViewById(R.id.product_list);
        list.add("Stylish moder dining table");
        list.add("4 seater dining table");
        list.add("6 seater dining table");
        list.add("Stylish 4 seater dining table");
        list.add("Stylish 6 seater dining table");
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
