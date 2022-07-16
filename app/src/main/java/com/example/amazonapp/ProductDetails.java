package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.amazonapp.model.AddProModel;
import com.example.amazonapp.viewholder.RelatedProductsHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {

    Intent intent;
    ImageView prodImg;
    TextView prodName,prodPrice,prodCategory,prodDesc;
    AppCompatButton order;
    Toolbar detailsToolbar;
    FirebaseAuth auth;
    String uniqueId;
    String relatedCategory;
    String name ;
    String checkname;
    RecyclerView relatedProdlist;
    ImageView back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        auth=FirebaseAuth.getInstance();

        prodImg=findViewById(R.id.prodImage);
        prodName=findViewById(R.id.ProdName);
        prodPrice=findViewById(R.id.prodPrice);
        prodCategory=findViewById(R.id.ProdCategory);
        prodDesc=findViewById(R.id.prodDesc);
        detailsToolbar=findViewById(R.id.detailsToolbar);
        detailsToolbar.setBackgroundResource(R.drawable.bg_color);
        back=findViewById(R.id.prodBack);
        order=findViewById(R.id.order);
        relatedProdlist=findViewById(R.id.relatedprodlist);
        relatedProdlist.setLayoutManager(new LinearLayoutManager(ProductDetails.this,
                LinearLayoutManager.HORIZONTAL,true));

        relatedCategory=prodCategory.getText().toString();

        intent=getIntent();
        prodName.setText(intent.getStringExtra("name").replaceAll("\n",""));
        prodCategory.setText(intent.getStringExtra("category"));
        int id=intent.getIntExtra("id",1);
        uniqueId=intent.getStringExtra("uniqueId");

        if(id==1)
        {
            uniqueId=uniqueId.replaceAll("\n","");
            prodName.setText(intent.getStringExtra("name").replaceAll("\n",""));
            prodPrice.setText("₹745");
            prodDesc.setText("Sonata analog watch for women");
            prodImg.setImageResource(R.drawable.watch1);

        }

        else if (id==2)
        {
            uniqueId=uniqueId.replaceAll("\n","");
            prodName.setText(intent.getStringExtra("name").replaceAll("\n",""));
            prodPrice.setText("₹4695");
            prodDesc.setText("Fastrack ruffles analog watch");
            prodImg.setImageResource(R.drawable.watch2);

        }
        else if (id==3)
        {
            prodName.setText(intent.getStringExtra("name").replaceAll("\n",""));
            prodPrice.setText(intent.getStringExtra("price"));
            prodDesc.setText(intent.getStringExtra("description"));
            String img=intent.getStringExtra("imageName");
            prodImg.setImageResource(this.getResources().getIdentifier(img,"drawable",this.getPackageName()));


        }
        else {

            prodName.setText(intent.getStringExtra("addProdName"));
            prodPrice.setText(intent.getStringExtra("addProdPrice"));
            prodDesc.setText(intent.getStringExtra("addProdDesc"));
            prodCategory.setText(intent.getStringExtra("addProdCategory"));
            String imgUri=intent.getStringExtra("img");
            Picasso.get().load(imgUri).into(prodImg);


        }

        back.bringToFront();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetails.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCartList();
            }
        });

        onStart();

    }

    private void addingToCartList()
    {
        String saveCurrentDate,saveCurrentTime;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());

        DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cartMap=new HashMap<>();

        cartMap.put("pid",uniqueId);
        cartMap.put("name",prodName.getText().toString());
        cartMap.put("price",prodPrice.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);

        cartListRef.child("User View").child(auth.getCurrentUser().getUid()).child("Products").child(uniqueId)
                .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ProductDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            Intent intentcart=new Intent(ProductDetails.this,HomeActivity.class);
                            intentcart.putExtra("cartadd","yes");
                            startActivity(intentcart);
                        }

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference prodListRef= FirebaseDatabase.getInstance().getReference().child("View All").
                child("User View").child("Products");

        FirebaseRecyclerOptions<AddProModel>options=new FirebaseRecyclerOptions.Builder<AddProModel>()
                .setQuery(prodListRef.orderByChild("category").startAt(relatedCategory),AddProModel.class).build();

        FirebaseRecyclerAdapter<AddProModel, RelatedProductsHolder>adapter=new
                FirebaseRecyclerAdapter<AddProModel, RelatedProductsHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull RelatedProductsHolder holder, int position, @NonNull AddProModel model) {
                        name= model.getName();
                        String price= model.getPrice();
                        String imgUri= model.getImg();

                        holder.relatedProdName.setText(name);
                        holder.relatedProdPrice.setText(price);
                        Picasso.get().load(imgUri).into(holder.relatedProdImg);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(ProductDetails.this,ProductDetails.class);
                                intent.putExtra("id",4);
                                intent.putExtra("uniqueId",name);
                                intent.putExtra("addProdName",name);
                                intent.putExtra("addProdPrice",price);
                                intent.putExtra("addProdDesc",model.getDescription());
                                intent.putExtra("addProdCategory",model.getCategory());
                                intent.putExtra("img",imgUri);
                                startActivity(intent);

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public RelatedProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.related_products_adapter,parent,false);
                        RelatedProductsHolder holder=new RelatedProductsHolder(view);

                        return holder;
                    }
                };
        relatedProdlist.setAdapter(adapter);
        adapter.startListening();
    }
}