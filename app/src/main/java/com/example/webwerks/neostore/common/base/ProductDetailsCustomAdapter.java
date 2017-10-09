package com.example.webwerks.neostore.common.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.view.product.ProductDetailsActivity;
import java.util.ArrayList;

public class ProductDetailsCustomAdapter extends RecyclerView.Adapter<ProductDetailsCustomAdapter.CustomViewHolder> {

    private ArrayList<Integer> list;
    private Context context;

    public ProductDetailsCustomAdapter(Context context,ArrayList list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public ProductDetailsCustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_details_image_list, null,true);
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView listimageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listimageView = itemView.findViewById(R.id.pd_image);
        }


        public void bind() {
            listimageView.setImageResource(list.get(getAdapterPosition()));
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), (getAdapterPosition()+1)+" OF "+list.size(), Toast.LENGTH_SHORT).show();
        }
    }
}

