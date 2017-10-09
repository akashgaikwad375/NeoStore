package com.example.webwerks.neostore.view.product;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.CustomAdapter;
import com.example.webwerks.neostore.common.base.ProductDetailsCustomAdapter;
import com.example.webwerks.neostore.common.base.ViewPagerSlideAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDetailsActivity extends BaseActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView title;
    private ProductDetailsCustomAdapter mAdapter;
    private RecyclerView pdList;

    private static Integer[] image_slider={R.drawable.slider_img1, R.drawable.slider_img2,
            R.drawable.slider_img3, R.drawable.slider_img4};

    private ArrayList<Integer> images=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s=getIntent().getStringExtra("Data");
        title.setText(s);

        viewPager.setAdapter(new ViewPagerSlideAdapter(this,images));
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        pdList.setLayoutManager(layoutManager);
        mAdapter= new ProductDetailsCustomAdapter(this,images);
        pdList.setAdapter(mAdapter);
    }
    private void initView() {
        for(int slider=0; slider<image_slider.length; slider++)
            images.add(image_slider[slider]);
        viewPager=findViewById(R.id.viewpager);
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        pdList=findViewById(R.id.product_list);
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
