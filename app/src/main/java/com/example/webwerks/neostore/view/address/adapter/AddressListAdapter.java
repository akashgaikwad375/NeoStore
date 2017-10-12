package com.example.webwerks.neostore.view.address.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.view.product.activity.ProductDetailsActivity;


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.CustomViewHolder> {

    private Context context;
    public int mSelectedItem = -1;
    public AddressListAdapter(Context context) {
        this.context=context;
    }
    @Override
    public AddressListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.shipping_address_list, null,true);
        AddressListAdapter.CustomViewHolder viewHolder=new AddressListAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressListAdapter.CustomViewHolder holder, int position) {
        holder.radioButton.setChecked(position==mSelectedItem);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView personName,addressDetails;
        RadioButton radioButton;
        ImageView clear,edit;
        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            personName = itemView.findViewById(R.id.person_name);
            addressDetails = itemView.findViewById(R.id.address_detail);
            clear = itemView.findViewById(R.id.clear);
            edit = itemView.findViewById(R.id.edit);
            radioButton = itemView.findViewById(R.id.select_address);
            radioButton.setOnClickListener(this);
        }


        public void bind(int position) {
            personName.setText("Person Name: "+position);
            addressDetails.setText("Address: "+position);

        }

        @Override
        public void onClick(View view) {
             mSelectedItem = getAdapterPosition();
        }
    }
}
