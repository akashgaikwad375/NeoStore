package com.example.webwerks.neostore.view.product.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.webwerks.neostore.R;

public class ProductDetailsAdapter extends
        RecyclerView.Adapter<ProductDetailsAdapter.CustomViewHolder> {

    private Context context;
    private ViewPager viewPager;
    public ProductDetailsAdapter(Context context,ViewPager viewPager) {
        this.context=context;
        this.viewPager=viewPager;
    }
    @Override
    public ProductDetailsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_details_image_list,null);
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind();

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView listimageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listimageView = itemView.findViewById(R.id.imgPd_image);

        }


        public void bind() {
            listimageView.setImageResource(R.drawable.slider_img1);
        }

        @Override
        public void onClick(View view) {

            viewPager.addView(view,0);
            Toast.makeText(view.getContext(), (getAdapterPosition()+1)+" OF "+4, Toast.LENGTH_SHORT).show();
        }
    }
}

