package com.example.webwerks.neostore.view.product.adapter;

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
import com.example.webwerks.neostore.view.product.activity.ProductDetailsActivity;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.CustomViewHolder> {

    private Context context;

    public ProductListingAdapter(Context context) {
        this.context=context;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_lists, null,true);
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;
        ImageView productImage;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            productName = itemView.findViewById(R.id.product_name);
            productImage = itemView.findViewById(R.id.product_image);
        }


        public void bind(int position) {
            productName.setText("Product: "+position);
            productImage.setImageResource(R.drawable.tableicon);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), (getAdapterPosition()+1)+" OF "+10, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,ProductDetailsActivity.class);
            intent.putExtra("Data",productName.getText().toString());
            view.getContext().startActivity(intent);
        }
    }
}
