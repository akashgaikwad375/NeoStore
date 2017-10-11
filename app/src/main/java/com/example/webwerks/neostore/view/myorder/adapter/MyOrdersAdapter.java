package com.example.webwerks.neostore.view.myorder.adapter;

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

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.CustomViewHolder> {

    private Context context;

    public MyOrdersAdapter(Context context) {
        this.context=context;
    }
    @Override
    public MyOrdersAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.order_list, null,true);
        MyOrdersAdapter.CustomViewHolder viewHolder=new MyOrdersAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyOrdersAdapter.CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView OrderNo;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            OrderNo = itemView.findViewById(R.id.order_no);
        }


        public void bind(int position) {
            OrderNo.setText(""+position);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
