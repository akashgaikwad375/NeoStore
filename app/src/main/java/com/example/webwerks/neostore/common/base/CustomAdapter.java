package com.example.webwerks.neostore.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.webwerks.neostore.R;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList list;

    public CustomAdapter(ArrayList list) {
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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView listtextView;
        ImageView listimageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            listtextView = itemView.findViewById(R.id.product_name);
            listimageView = itemView.findViewById(R.id.product_image);
        }


        public void bind(int position) {
            listtextView.setText(String.valueOf(list.get(getPosition())));
            listimageView.setImageResource(R.drawable.tableicon);

        }
    }
}
