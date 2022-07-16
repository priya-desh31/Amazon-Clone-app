package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.amazonapp.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivitynew extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    EditText regName,regEmail,regPass,regConfirmPass;
    AppCompatButton signUpButton;
    LinearLayout signInText;
    String imgUri;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activitynew);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        regName=findViewById(R.id.regusername);
        regEmail=findViewById(R.id.regemailaddress);
        regPass=findViewById(R.id.regpassword);
        regConfirmPass=findViewById(R.id.regconfirmpass);
        signUpButton=findViewById(R.id.signUpbtn);
        signUpButton.setBackgroundResource(R.drawable.intro_signin);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

        signInText=findViewById(R.id.signintext);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                String name=regName.getEditableText().toString();
                String email=regEmail.getEditableText().toString();
                String password=regPass.getEditableText().toString();
                String confirmpass=regConfirmPass.getEditableText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(confirmpass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivitynew.this,"Enter valid data",Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern))
                {
                    progressDialog.dismiss();
                    regEmail.setError("Invalid Email");
                    Toast.makeText(RegisterActivitynew.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<=6)
                {
                    progressDialog.dismiss();
                    regPass.setError("Invalid Password");
                    Toast.makeText(RegisterActivitynew.this,"Please enter more than 6 characters",Toast.LENGTH_SHORT).show();

                }
                else if(!password.equals(confirmpass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivitynew.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                DatabaseReference reference=database.getReference().child("users").child(auth.getCurrentUser().getUid());
                                StorageReference storageReference=storage.getReference().child("upload").child(auth.getCurrentUser().getUid());
                                imgUri="https://firebasestorage.googleapis.com/v0/b/app-27525.appspot.com/o/profilepicicon.jpg?alt=media&token=3cbaa7bd-c828-4dca-8dd3-8ce0cd4bba08";
                                Users users=new Users(auth.getCurrentUser().getUid(),name,email,imgUri);
                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful())
                                        {
                                            progressDialog.dismiss();
                                            startActivity(new Intent(RegisterActivitynew.this,HomeActivity.class));
                                        }
                                        else {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivitynew.this, "Error in creating a new user", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }

                            else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivitynew.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivitynew.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}