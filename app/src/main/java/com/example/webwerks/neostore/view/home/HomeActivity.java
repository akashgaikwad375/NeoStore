package com.example.webwerks.neostore.view.home;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView title;
    private ViewPager viewPager;

    private final int current_page=0;
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
                    case R.id.my_orders_action: return true;
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

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        viewPager=findViewById(R.id.viewpager);
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

    class ViewPagerSlideAdapter extends PagerAdapter{

        private ArrayList<Integer> images;
        private LayoutInflater inflater;
        private Context context;

        public ViewPagerSlideAdapter(HomeActivity homeActivity, ArrayList<Integer> images) {
            this.context=homeActivity;
            this.images=images;
            inflater=LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=inflater.inflate(R.layout.fragment_home,container,false);
            ImageView imageView=view.findViewById(R.id.slide_image);
            imageView.setImageResource(images.get(position));
            container.addView(view,0);
            return view;
        }
    }
}

