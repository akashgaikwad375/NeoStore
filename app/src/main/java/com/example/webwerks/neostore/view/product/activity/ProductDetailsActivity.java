package com.example.webwerks.neostore.view.product.activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.home.adapter.HomeBannerSliderAdapter;
import com.example.webwerks.neostore.view.product.adapter.ProductDetailsAdapter;
import com.example.webwerks.neostore.view.product.fragment.EnterQuantityFragment;
import com.example.webwerks.neostore.view.product.fragment.RatingPopUpFragment;


public class ProductDetailsActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView title;
    private ProductDetailsAdapter mAdapter;
    private RecyclerView pdList;
    private ImageView share;
    private Button buy,rate;
    private DialogFragment dialogFragment;

    @Override
    public int getContentView() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initView() {
        viewPager=findViewById(R.id.viewpager);
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        pdList=findViewById(R.id.recycler_view);
        share=findViewById(R.id.share);
        buy=findViewById(R.id.buy_now);
        rate=findViewById(R.id.rate);
        setAdapter();
    }

    @Override
    public void setListener() {
        share.setOnClickListener(this);
        buy.setOnClickListener(this);
        rate.setOnClickListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s=getIntent().getStringExtra("Data");
        title.setText(s);
    }

    public void setAdapter() {
        viewPager.setAdapter(new HomeBannerSliderAdapter(this));
        viewPager.addOnPageChangeListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        pdList.setLayoutManager(layoutManager);
        mAdapter= new ProductDetailsAdapter(this,viewPager);
        pdList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.share:
                intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String choose=getString(R.string.choose);
                intent.putExtra(intent.EXTRA_TEXT,"");
                Intent chooser=Intent.createChooser(intent,choose);
                if(intent.resolveActivity(getPackageManager())!= null)
                    startActivity(chooser);
                break;
            case R.id.buy_now:
                dialogFragment=new EnterQuantityFragment();
                dialogFragment.show(getSupportFragmentManager(),"buy_product");
                break;
            case R.id.rate:
                dialogFragment=new RatingPopUpFragment();
                dialogFragment.show(getSupportFragmentManager(),"rate_product");
                break;
        }
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pdList.smoothScrollToPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
