package com.example.webwerks.neostore.view.mycart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.webwerks.neostore.R;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.CustomViewHolder> {

    private Context context;

    public MyCartAdapter(Context context) {
        this.context=context;
    }
    @Override
    public MyCartAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_cart_list, null,true);

        MyCartAdapter.CustomViewHolder viewHolder=new MyCartAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyCartAdapter.CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView orderImage;
        Spinner spnQuantity;
        TextView orderName,orderType,orderPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            orderImage = itemView.findViewById(R.id.order_image);
            orderName = itemView.findViewById(R.id.order_name);
            orderType = itemView.findViewById(R.id.order_type);
            orderPrice = itemView.findViewById(R.id.order_price);
            spnQuantity=itemView.findViewById(R.id.spQuantity);
            ArrayAdapter<CharSequence> adapter=
                    ArrayAdapter.createFromResource(context,R.array.quantity,
                            android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnQuantity.setAdapter(adapter);
        }


        public void bind(int position) {
            orderName.setText(""+position);
            orderType.setText("("+position+")");
            orderPrice.setText("Rs. "+position);
        }

        @Override
        public void onClick(View view) {

        }


    }
}
