package com.example.webwerks.neostore.view.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webwerks.neostore.R;


public class HomeBannerSliderAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;

    public HomeBannerSliderAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        ImageView imageView=view.findViewById(R.id.slide_image);
        imageView.setImageResource(R.drawable.slider_img1);
        container.addView(view,0);
        return view;
    }
}
