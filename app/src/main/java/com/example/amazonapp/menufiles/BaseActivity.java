package com.example.amazonapp.menufiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.amazonapp.AddProduct;
import com.example.amazonapp.CartActivity;
import com.example.amazonapp.HomeActivity;
import com.example.amazonapp.PlaceOrderActivity;
import com.example.amazonapp.ProfileActivity;
import com.example.amazonapp.R;
import com.example.amazonapp.SearchActivity;

public class BaseActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    RadioButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        radioGroup1=findViewById(R.id.radioGraup1);
        home=findViewById(R.id.bottomHome);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;

                switch (checkedId)
                {
                    case R.id.bottomHome:
                        in=new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.bottomAddproduct:
                        in=new Intent(getBaseContext(), AddProduct.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.bottomsearch:
                        in=new Intent(getBaseContext(), SearchActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.bottomMycart:
                        in=new Intent(getBaseContext(), PlaceOrderActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.bottomProfile:
                        in=new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}