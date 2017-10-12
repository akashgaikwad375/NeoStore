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

public class OrderIdAdapter extends RecyclerView.Adapter<OrderIdAdapter.CustomViewHolder> {

    private Context context;

    public OrderIdAdapter(Context context) {
        this.context=context;
    }
    @Override
    public OrderIdAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.order_id_list, null,true);
        OrderIdAdapter.CustomViewHolder viewHolder=new OrderIdAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderIdAdapter.CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView orderImage;
        TextView orderName,orderType,orderQty,orderPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            orderImage = itemView.findViewById(R.id.order_image);
            orderName = itemView.findViewById(R.id.order_name);
            orderType = itemView.findViewById(R.id.order_type);
            orderQty = itemView.findViewById(R.id.order_qty);
            orderPrice = itemView.findViewById(R.id.order_price);
        }


        public void bind(int position) {
            orderName.setText(""+position);
            orderType.setText("("+position+")");
            orderQty.setText("QTY : "+position);
            orderPrice.setText("Rs. "+position);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
