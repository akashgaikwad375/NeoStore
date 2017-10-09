package com.example.webwerks.neostore.view.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.ViewPagerSlideAdapter;
import com.example.webwerks.neostore.view.address.AddAddressActivity;
import com.example.webwerks.neostore.view.product.ProductListingActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener {

    private CircleIndicator circleIndicator;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView title;
    private ViewPager viewPager;
    private ImageView tables,sofas,chairs,cupboards;

    private static int current_page=0;
    private static Integer[] image_slider={R.drawable.slider_img1, R.drawable.slider_img2,
            R.drawable.slider_img3, R.drawable.slider_img4};

    private ArrayList<Integer> images=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(R.string.login_header);

        viewPager.setAdapter(new ViewPagerSlideAdapter(this,images));
        circleIndicator.setViewPager(viewPager);

        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if(current_page==image_slider.length)
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.my_cart_action: return true;
                    case R.id.table_action: return true;
                    case R.id.sofas_action: return true;
                    case R.id.chairs_action: return true;
                    case R.id.cupboards_action: return true;
                    case R.id.my_account_action: return true;
                    case R.id.store_locator_action: return true;
                    case R.id.my_orders_action:
                        Intent i=new Intent(HomeActivity.this, AddAddressActivity.class);
                        startActivity(i);
                        break;
                    case R.id.logout_action: return true;
                }
                return true;

            }

        });

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this,drawerLayout, toolbar,
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

    private void initView() {

        for(int slider=0; slider<image_slider.length; slider++)
            images.add(image_slider[slider]);


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

        tables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Tables");
                startActivity(i);
            }
        });
        sofas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Sofas");
                startActivity(i);
            }
        });
        chairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Chairs");
                startActivity(i);
            }
        });
        cupboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, ProductListingActivity.class);
                i.putExtra("Title","Cupboards");
                startActivity(i);
            }
        });

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
    public void onFragmentInteraction(Uri uri) {

    }


}

