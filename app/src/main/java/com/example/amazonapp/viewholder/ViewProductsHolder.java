package com.example.amazonapp.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.interfaces.ItemClickListener;
import com.example.amazonapp.R;

public class ViewProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView addProductname,addProductprice;
    private ItemClickListener itemClickListener ;
    public ImageView addProdImage ;

    public ViewProductsHolder(@NonNull View itemView) {
        super(itemView);
        addProductname=(TextView) itemView.findViewById(R.id.addProductName);
        addProductprice=(TextView) itemView.findViewById(R.id.addProductPrice);
        addProdImage=(ImageView) itemView.findViewById(R.id.addProductImg);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
