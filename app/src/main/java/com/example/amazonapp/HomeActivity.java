package com.example.amazonapp;


import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.amazonapp.constant.Constant;
import com.example.amazonapp.menufiles.BaseActivity;

import com.example.amazonapp.model.Product;
import com.example.amazonapp.viewholder.ProductAdapter;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

public  class HomeActivity extends BaseActivity {

    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;
    Toolbar toolbar;
    CardView watch1,watch2,watch3,watch4;
    TextView oddwatchname,oddwatchprice,evenwatchname,evenwatchprice,viewAll;
    public static ImageView homeCart;
    FirebaseStorage storage;
    StorageReference storageReference;

    Intent intentcart;
    String getcartUpdate;


    ViewPager viewPager;

    int images[] = {R.drawable.one, R.drawable.two, R.drawable.three};

    int currentPageCunter = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);





        dynamicContent=findViewById(R.id.dynamicContet);
        bottomNavBar=findViewById(R.id.bottomNavBar);
        View wizard=getLayoutInflater().inflate(R.layout.activity_home,null);
        dynamicContent.addView(wizard);
        RadioGroup rg=findViewById(R.id.radioGraup1);
        RadioButton rb=findViewById(R.id.bottomHome);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F5185"));




        viewPager = findViewById(R.id.viewpager);
        //add adapter
        viewPager.setAdapter(new SliderAdapter(images, HomeActivity.this));

        //auto change image
        final Handler handler = new Handler();
        final Runnable update  = new Runnable() {
            @Override
            public void run() {
                if (currentPageCunter == images.length){
                    currentPageCunter = 0 ;

                }

                viewPager.setCurrentItem(currentPageCunter++,true);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2500,2500);


        watch1=findViewById(R.id.watch1);
        watch2=findViewById(R.id.watch2);
        watch3=findViewById(R.id.watch3);
        watch4=findViewById(R.id.watch4);

        oddwatchname=findViewById(R.id.oddwatchname);
        oddwatchprice=findViewById(R.id.oddwatchprice);
        evenwatchname=findViewById(R.id.evenwatchname);
        evenwatchprice=findViewById(R.id.evenwatchprice);

        viewAll=findViewById(R.id.viewallproducts);
        homeCart=findViewById(R.id.homecart);

        intentcart=getIntent();
        if(intentcart.getStringExtra("cartadd")!=null && intentcart.getStringExtra("cartadd").equals("yes"))
        {
          homeCart.setImageResource(R.drawable.cartnoti);
        }
        else if (intentcart.getStringExtra("cartadd")!=null && intentcart.getStringExtra("cartadd").equals("no"))
        {
            homeCart.setImageResource(R.drawable.cart);
        }
        else {
            homeCart.setImageResource(R.drawable.cart);
        }

        storage=FirebaseStorage.getInstance();


        watch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,ProductDetails.class);
                i.putExtra("name",oddwatchname.getText().toString());
                i.putExtra("Category","Women's Wrist Watch");
                i.putExtra("price",oddwatchprice.getText().toString());
                i.putExtra("uniqueId",oddwatchname.getText().toString());
                i.putExtra("id",1);
                startActivity(i);

            }
        });

        watch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,ProductDetails.class);
                i.putExtra("name",evenwatchname.getText().toString());
                i.putExtra("Category","Women's Wrist Watch");
                i.putExtra("price",evenwatchprice.getText().toString());
                i.putExtra("uniqueId",evenwatchname.getText().toString());
                i.putExtra("id",2);
                startActivity(i);

            }
        });

        watch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,ProductDetails.class);
                i.putExtra("name",oddwatchname.getText().toString());
                i.putExtra("Category","Men's Wrist Watch");
                i.putExtra("price",oddwatchprice.getText().toString());
                i.putExtra("uniqueId",oddwatchname.getText().toString());
                i.putExtra("id",3);
                startActivity(i);

            }
        });

        watch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,ProductDetails.class);
                i.putExtra("name",oddwatchname.getText().toString());
                i.putExtra("Category","Smart Watch for Boys and Girls");
                i.putExtra("price",oddwatchprice.getText().toString());
                i.putExtra("uniqueId",oddwatchname.getText().toString());
                i.putExtra("id",4);
                startActivity(i);

            }
        });

        ListView lvProducts=findViewById(R.id.productslist);
        ProductAdapter productAdapter=new ProductAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product=Constant.PRODUCT_LIST.get(position);
                Intent intent=new Intent(HomeActivity.this,ProductDetails.class);
                intent.putExtra("id",3);
                intent.putExtra("uniqueId",product.getpName());
                intent.putExtra("name",product.getpName());
                intent.putExtra("description",product.getpDescription());
                intent.putExtra("category","Smartphone");
                intent.putExtra("pprice",Constant.CURRENCY+String.valueOf(product.getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
                intent.putExtra("imageName",product.getpImageName());
                startActivity(intent);

            }
        });

        homeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);

            }
        });



    }





}