package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazonapp.menufiles.BaseActivity;
import com.example.amazonapp.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfileActivity extends BaseActivity {
    LinearLayout dynamicContent,bottomNavBar;
    ImageView back,done,profileImg;
    EditText profileUsername;
    TextView profileEmail,profileLogout,profileHistory,displayUsername;
    String emailId;
    Toolbar profileToolbar;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    Uri setImageUri;
    Dialog dialog;
    ProgressDialog progressDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        dynamicContent=findViewById(R.id.dynamicContet);
        bottomNavBar=findViewById(R.id.bottomNavBar);
        View wizard=getLayoutInflater().inflate(R.layout.activity_profile,null);
        dynamicContent.addView(wizard);
        RadioGroup rg=findViewById(R.id.radioGraup1);
        RadioButton rb=findViewById(R.id.bottomProfile);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F5185"));
        profileToolbar=findViewById(R.id.profileToolbar);
        profileToolbar.setBackgroundResource(R.drawable.bg_color);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait.....");
        progressDialog.setCancelable(false);

        back=findViewById(R.id.profileback);
        done=findViewById(R.id.done);
        profileImg=findViewById(R.id.profileimg);

        profileUsername=findViewById(R.id.profileusername);
        profileEmail=findViewById(R.id.profileemail);
        profileLogout=findViewById(R.id.profilelogout);
        profileHistory=findViewById(R.id.profilehistory);
        displayUsername=findViewById(R.id.displayusername);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        DatabaseReference reference=database.getReference().child("users").child(auth.getCurrentUser().getUid());
        StorageReference storageReference=storage.getReference().child("upload").child(auth.getCurrentUser().getUid());

        progressDialog.show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                emailId=snapshot.child("email").getValue().toString();
                String name=snapshot.child("name").getValue().toString();
                String img=snapshot.child("imgUri").getValue().toString();

                profileUsername.setText(name);
                displayUsername.setText(name);
                profileEmail.setText(emailId);
                Picasso.get().load(img).into(profileImg);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profileHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,ShowHistory.class);
                startActivity(intent);

            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String name=profileUsername.getText().toString();
                String email=profileEmail.getText().toString();
                displayUsername.setText(name);

                if (setImageUri!=null)
                {
                    storageReference.putFile(setImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String finalImageUri=uri.toString();
                                    Users users=new Users(auth.getCurrentUser().getUid(),name,email,finalImageUri);
                                    Log.i("Url",storageReference.getDownloadUrl().toString());

                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful())
                                            {
                                                progressDialog.dismiss();
                                                Toast.makeText(ProfileActivity.this, "Changes saved!", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                progressDialog.dismiss();
                                                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                                }
                            });

                        }
                    });
                }else {
                    String imgUri="https://firebasestorage.googleapis.com/v0/b/app-27525.appspot.com/o/profilepicicon.jpg?alt=media&token=3cbaa7bd-c828-4dca-8dd3-8ce0cd4bba08";
                    Users users=new Users(auth.getCurrentUser().getUid(),name,email,imgUri);

                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Toast.makeText(ProfileActivity.this, "Changes saved!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            }

        });

        profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(ProfileActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_layout);

                TextView yesbutton,noButton;
                yesbutton=dialog.findViewById(R.id.yesButton);
                noButton=dialog.findViewById(R.id.noButton);

                yesbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this,IntroActivity.class));
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setImageUri=data.getData();

        if (requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
              try {
                  Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),setImageUri);
                  profileImg.setImageBitmap(bitmap);
              }catch (Exception e)
              {
                  e.printStackTrace();
              }
        }
    }
}