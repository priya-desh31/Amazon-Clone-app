package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.amazonapp.menufiles.BaseActivity;
import com.example.amazonapp.model.AddProModel;
import com.example.amazonapp.model.Product;
import com.example.amazonapp.viewholder.ViewProductsHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

public class SearchActivity extends BaseActivity {
    LinearLayout dynamicContent,bottomNavBar;
    EditText inputText;
    AppCompatButton searchBtn;
    RecyclerView searchList;
    String searchInput;
    ImageView backHome;
    Toolbar viewToolbar;



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);

        dynamicContent=findViewById(R.id.dynamicContet);
        bottomNavBar=findViewById(R.id.bottomNavBar);
       View wizard=getLayoutInflater().inflate(R.layout.activity_search,null);
       dynamicContent.addView(wizard);
        RadioGroup rg=findViewById(R.id.radioGraup1);
        RadioButton rb=findViewById(R.id.bottomsearch);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F5185"));

        viewToolbar=findViewById(R.id.viewToolbar);
        viewToolbar.setBackgroundResource(R.drawable.bg_color);
        searchBtn=findViewById(R.id.searchbtn);
        searchBtn.setBackgroundResource(R.drawable.intro_signin);
        inputText=findViewById(R.id.searchEdittext);
        searchList=findViewById(R.id.searchlist);
        searchList.setLayoutManager(new LinearLayoutManager(SearchActivity.this,
                LinearLayoutManager.VERTICAL,true));
        backHome=findViewById(R.id.backHome);




        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInput=inputText.getEditableText().toString();
                onStart();
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference prodListRef= FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseRecyclerOptions<Product>options=new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(prodListRef.orderByChild("name").startAt(searchInput),Product.class).build();

        FirebaseRecyclerAdapter<Product, ViewProductsHolder> adapter=new FirebaseRecyclerAdapter<Product, ViewProductsHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewProductsHolder holder, int position, @NonNull Product model) {


                String name=model.getpName().replaceAll("\n","");
                BigDecimal price=model.getpPrice();
                String imgUri=model.getpImageName();

                holder.addProductname.setText(name);
                holder.addProductprice.setText((CharSequence) price);
                Picasso.get().load(imgUri).into(holder.addProdImage);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SearchActivity.this,ProductDetails.class);
                        intent.putExtra("id",4);
                        intent.putExtra("uniqueId",name);
                        intent.putExtra("addProdName",name);
                        intent.putExtra("addProdPrice",price);
                        intent.putExtra("addProdDesc",model.getpDescription());

                        intent.putExtra("img",imgUri);
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public ViewProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_adapter,parent,false);
                ViewProductsHolder holder=new ViewProductsHolder(view);
                return holder;
            }
        };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }



}