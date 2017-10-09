package com.example.webwerks.neostore.common.base;

import android.content.Context;
import android.content.DialogInterface;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList list;
    private Context context;

    public CustomAdapter(Context context,ArrayList list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_lists, null,true);
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        int height =  (int)(parent.getMeasuredHeight() / 5.4);
        view.setMinimumHeight(height);
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

        TextView listtextView;
        ImageView listimageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listtextView = itemView.findViewById(R.id.product_name);
            listimageView = itemView.findViewById(R.id.product_image);
        }


        public void bind() {
            listtextView.setText(String.valueOf(list.get(getPosition())));
            listimageView.setImageResource(R.drawable.tableicon);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), (getAdapterPosition()+1)+" OF "+list.size(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,ProductDetailsActivity.class);
            intent.putExtra("Data",String.valueOf(list.get(getAdapterPosition())));
            view.getContext().startActivity(intent);
        }
    }
}
