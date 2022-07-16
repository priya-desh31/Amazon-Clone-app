package com.example.amazonapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.R;
import com.example.amazonapp.interfaces.ItemClickListener;

public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView orderPrice,orderName,orderCity,orderAddress,orderPhone,orderDate;
    private ItemClickListener itemClickListener;


    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        orderName=itemView.findViewById(R.id.orderName);
        orderAddress=itemView.findViewById(R.id.orderAddress);
        orderCity=itemView.findViewById(R.id.orderCity);
        orderPhone=itemView.findViewById(R.id.orderPhone);
        orderPrice=itemView.findViewById(R.id.orderPrice);
        orderDate=itemView.findViewById(R.id.orderDate);

    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
