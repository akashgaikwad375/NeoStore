package com.example.webwerks.neostore.common.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.view.home.HomeActivity;

import java.util.ArrayList;



public class ViewPagerSlideAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public ViewPagerSlideAdapter(Context context, ArrayList<Integer> images) {
        this.context=context;
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
