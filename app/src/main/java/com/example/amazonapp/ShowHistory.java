package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.amazonapp.model.Orders;
import com.example.amazonapp.viewholder.CartViewHolder;
import com.example.amazonapp.viewholder.OrdersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowHistory extends AppCompatActivity {

    RecyclerView orderList;
    ImageView backProfile;
    Toolbar cartToolbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        orderList=findViewById(R.id.orederList);
        backProfile=findViewById(R.id.backProfile);
        cartToolbar=findViewById(R.id.cartToolbar);
        cartToolbar.setBackgroundResource(R.drawable.bg_color);
        orderList.setLayoutManager(new LinearLayoutManager(ShowHistory.this));
        auth=FirebaseAuth.getInstance();

        backProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowHistory.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference ordersRef= FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions<Orders>options=new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(ordersRef.child("Orders").child(auth.getCurrentUser().getUid()).child("History"),Orders.class).build();

        FirebaseRecyclerAdapter<Orders, OrdersViewHolder>adapter=new FirebaseRecyclerAdapter<Orders, OrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrdersViewHolder holder, int position, @NonNull Orders model) {

                holder.orderName.setText(model.getName());
                holder.orderPrice.setText(model.getTotalAmount());
                holder.orderPhone.setText(model.getPhone());
                holder.orderCity.setText(model.getCity());
                holder.orderDate.setText(model.getDate());
                holder.orderAddress.setText(model.getAddress());

            }

            @NonNull
            @Override
            public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_layout,parent,false);
                OrdersViewHolder holder=new OrdersViewHolder(view);
                return holder;
            }
        };

        orderList.setAdapter(adapter);
        adapter.startListening();
    }
}