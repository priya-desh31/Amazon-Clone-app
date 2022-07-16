package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazonapp.constant.Constant;
import com.example.amazonapp.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailEdittext,passwordEdittext ;
    AppCompatButton signInbtn;
    LinearLayout signUptext;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

        emailEdittext=findViewById(R.id.loginemail);
        passwordEdittext=findViewById(R.id.loginpassword);
        signInbtn=findViewById(R.id.signInbtn);
        signInbtn.setBackgroundResource(R.drawable.intro_signin);
        signUptext=findViewById(R.id.signuptext);


        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                String email=emailEdittext.getEditableText().toString();
                String password=passwordEdittext.getEditableText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern))
                {
                    progressDialog.dismiss();
                  emailEdittext.setError("Invalid Email");
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<=6)
                {
                    progressDialog.dismiss();
                    passwordEdittext.setError("Invalid Password");
                    Toast.makeText(LoginActivity.this, "Please enter more than 6 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Error in login. Try again", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

      signUptext.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent=new Intent(LoginActivity.this,RegisterActivitynew.class);
              startActivity(intent);

          }
      });

    }


}