package com.example.webwerks.neostore.view.home.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.view.address.activity.AddAddressActivity;
import com.example.webwerks.neostore.view.address.activity.AddressListActivity;
import com.example.webwerks.neostore.view.home.adapter.HomeBannerSliderAdapter;
import com.example.webwerks.neostore.view.login.LoginActivity;
import com.example.webwerks.neostore.view.mycart.activity.MyCartActivity;
import com.example.webwerks.neostore.view.myorder.activity.MyOrdersActivity;
import com.example.webwerks.neostore.view.product.activity.ProductListingActivity;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private CircleIndicator circleIndicator;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView title;
    private ViewPager viewPager;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView tables,sofas,chairs,cupboards;
    private static int current_page=0;

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        circleIndicator=findViewById(R.id.slider_indicator);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        viewPager=findViewById(R.id.viewpager);
        tables=findViewById(R.id.tables_image);
        sofas=findViewById(R.id.sofas_image);
        chairs=findViewById(R.id.chairs_image);
        cupboards=findViewById(R.id.cupboards_image);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void setListener() {
        tables.setOnClickListener(this);
        sofas.setOnClickListener(this);
        chairs.setOnClickListener(this);
        cupboards.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(R.string.login_header);
        setAdapter();
    }

    public void setAdapter() {
        viewPager.setAdapter(new HomeBannerSliderAdapter(this));
        circleIndicator.setViewPager(viewPager);

        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if(current_page==4)
                    current_page=0;
                viewPager.setCurrentItem(current_page++,true);
            }
        };
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2500,2500);

        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.tables_image:
                i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Tables");
                startActivity(i);
                break;
            case R.id.sofas_image:
                i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Sofas");
                startActivity(i);
                break;
            case R.id.chairs_image:
                i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Chairs");
                startActivity(i);
                break;
            case R.id.cupboards_image:
                i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Cupboards");
                startActivity(i);
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
            case R.id.action_search:
                return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        Intent i;
        switch (item.getItemId()){
            case R.id.my_cart_action:
                i=new Intent(HomeActivity.this,MyCartActivity.class);
                startActivity(i);
                break;
            case R.id.table_action: return true;
            case R.id.sofas_action: return true;
            case R.id.chairs_action: return true;
            case R.id.cupboards_action: return true;
            case R.id.my_account_action:
                i=new Intent(HomeActivity.this, AddressListActivity.class);
                startActivity(i);
                break;
            case R.id.my_address_action:
                i=new Intent(HomeActivity.this, AddAddressActivity.class);
                startActivity(i);
                break;
            case R.id.store_locator_action: return true;
            case R.id.my_orders_action:
                i=new Intent(HomeActivity.this, MyOrdersActivity.class);
                startActivity(i);
                break;
            case R.id.logout_action: return true;
        }
        return true;
    }
}

