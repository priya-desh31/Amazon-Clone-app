package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PlaceOrderActivity extends AppCompatActivity implements PaymentResultListener {

    EditText shipName,shipPhone,shipAddress,shipCity;
    AppCompatButton confirmOrder;
    FirebaseAuth auth;
    Intent intent;
    String totalAmount;
    TextView cartpriceTotal;
    Toolbar cartToolbar;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        shipName=findViewById(R.id.shipname);
        shipPhone=findViewById(R.id.shipphone);
        shipAddress=findViewById(R.id.shipaddress);
        shipCity=findViewById(R.id.shipcity);
        confirmOrder=findViewById(R.id.confirmorder);
        cartpriceTotal=findViewById(R.id.cartpricetotal);
        cartToolbar=findViewById(R.id.cartToolbarplaceord);
        auth=FirebaseAuth.getInstance();

        cartToolbar.setBackgroundResource(R.drawable.bg_color);
        confirmOrder.setBackgroundResource(R.drawable.bg_color);
        intent=getIntent();
        totalAmount=intent.getStringExtra("totalAmount");
        cartpriceTotal.setText(totalAmount);

        String sAmount="100";
        amount=Math.round(Float.parseFloat(sAmount)*100);


        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               check();
            }
        });
    }

    private void check()
    {
        if (TextUtils.isEmpty(shipName.getText().toString()))
        {
            shipName.setError("Enter name");
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(shipPhone.getText().toString()))

        {
            shipPhone.setError("Enter Phone no.");
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(shipAddress.getText().toString()))
        {
            shipAddress.setError("Enter Address.");
            Toast.makeText(this, "Please enter your Address", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(shipCity.getText().toString()))
        {
            shipCity.setError("Enter City.");
            Toast.makeText(this, "Please enter your City", Toast.LENGTH_SHORT).show();
        }
        else {
            paymentFunc();
        }
    }

    private void paymentFunc()
    {
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_t4p3lr678XRevA");
        checkout.setImage(R.drawable.razorpay);

        JSONObject object=new JSONObject();
        try {
            object.put("name","Android user");
            object.put("description","Test Payment");
            object.put("currency","INR");
            object.put("amount",amount);
            object.put("prefill.contact","7083912349");
            object.put("name","Android user");
            checkout.open(PlaceOrderActivity.this,object);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void confirmOrderFunc()
    {

      final String saveCurrentDate,saveCurrentTime;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());

        final DatabaseReference orderRef= FirebaseDatabase.getInstance().getReference()
                .child("Orders").child(auth.getCurrentUser().getUid()).child("History")
                .child(saveCurrentDate.replaceAll("/","-")+""+saveCurrentTime);

        HashMap<String,Object>ordersMap= new HashMap<>();
        ordersMap.put("totalAmount",totalAmount);
        ordersMap.put("name",shipName.getText().toString());
        ordersMap.put("phone",shipPhone.getText().toString());
        ordersMap.put("Address",shipAddress.getText().toString());
        ordersMap.put("city",shipCity.getText().toString());
        ordersMap.put("date",saveCurrentDate);
        ordersMap.put("time",saveCurrentTime);

        orderRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child("User View").child(auth.getCurrentUser().getUid())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(PlaceOrderActivity.this, "Your order has been placed successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent1=new Intent(PlaceOrderActivity.this,HomeActivity.class);
                                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent1);
                                        finish();
                                    }
                                }
                            });
                }

            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {
        confirmOrderFunc();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}