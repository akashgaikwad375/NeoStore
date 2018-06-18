package com.example.webwerks.neostore.view.product.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.model.ProductListModel;
import com.example.webwerks.neostore.view.product.activity.ProductDetailsActivity;

import java.util.Collections;
import java.util.List;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.CustomViewHolder> {

    private Context context;
    private ProductListModel current;
    private List<ProductListModel> mdata= Collections.emptyList();

    public ProductListingAdapter(Context context, List<ProductListModel> data) {
        this.context=context;
        mdata=data;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_lists, parent,false);
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.itemView.setTag(mdata.get(position));
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName,productDetail,productPrice;
        ImageView productImage;
        RatingBar ratingBar;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            productName = itemView.findViewById(R.id.product_name);
            productImage = itemView.findViewById(R.id.product_image);
            productDetail = itemView.findViewById(R.id.product_detail);
            productPrice = itemView.findViewById(R.id.product_cost);
            ratingBar = itemView.findViewById(R.id.product_rating);
        }

        public void bind(int position) {
            current=mdata.get(position);
            productName.setText(current.getName());
            productDetail.setText(current.getProducer());
            productPrice.setText("Rs. "+current.getCost());
            ratingBar.setRating(current.getRating());

            Glide.with(context).load(current.getProduct_images())
                    .into(productImage);
        }

        @Override
        public void onClick(View view) {

            ProductListModel current = (ProductListModel) view.getTag();
            Intent intent=new Intent(context,ProductDetailsActivity.class);
            intent.putExtra("Data",current.getName());
            intent.putExtra("id",current.getId()+"");
            view.getContext().startActivity(intent);
        }
    }
}
