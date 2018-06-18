package com.example.webwerks.neostore.view.product.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.model.ProductDetailsModel;
import com.example.webwerks.neostore.model.ProductImagesModel;

import java.util.List;

public class ProductDetailsAdapter extends
        RecyclerView.Adapter<ProductDetailsAdapter.CustomViewHolder> {

    private ImageView imgSelected;
    private Context context;
    List<ProductImagesModel> modelList;
    public ProductDetailsAdapter(Context context, List<ProductImagesModel> productImagesModels, ImageView imgSelected) {
        this.context=context;
        this.modelList=productImagesModels;
        this.imgSelected=imgSelected;
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
        holder.itemView.setTag(modelList.get(position));
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView listimageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listimageView = itemView.findViewById(R.id.imgPd_image);
        }


        public void bind(int position) {
            Glide.with(context).load(modelList.get(position).getImage())
                    .into(listimageView);
        }

        @Override
        public void onClick(View view) {

            ProductImagesModel current= (ProductImagesModel) view.getTag();
            Glide.with(context).load(current.getImage())
                    .into(imgSelected);
        }
    }
}

