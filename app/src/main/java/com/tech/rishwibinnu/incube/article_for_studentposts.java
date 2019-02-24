package com.tech.rishwibinnu.incube;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class article_for_studentposts extends AppCompatActivity {


    private ImageView articleImage;
    private EditText articleDesc,articlename,articlelink;
    private Button articleBtn;

    private Uri articleImageUri = null;

    String postdate,posttime,postrandomname,downloadurl;
    DatabaseReference useref,postref;
    String profileimage,username;
    String event;

    private ProgressBar newPostProgress;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private String current_user_id;
    String userid;
    String thumb_downloadurl;

    private Bitmap compressedImageFile=null;

    long countpost=0;

    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_for_studentposts);

        firebaseAuth = FirebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        useref=FirebaseDatabase.getInstance().getReference().child("Users");
        useref.keepSynced(true);
        postref=FirebaseDatabase.getInstance().getReference().child("Posts");
        postref.keepSynced(true);

        current_user_id = firebaseAuth.getCurrentUser().getUid();

        articleImage = findViewById(R.id.article_image);
        articleDesc = findViewById(R.id.article_desc);
        articlename = findViewById(R.id.articletname);
        articlelink = findViewById(R.id.article_link);
        articleBtn = findViewById(R.id.article_btn);
        newPostProgress = findViewById(R.id.article_progress);

        articleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(article_for_studentposts.this);

            }
        });

        articleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String disc=articleDesc.getText().toString();
                event=articlename.getText().toString();
                if(TextUtils.isEmpty(event))
                {
                    Toast.makeText(article_for_studentposts.this,"Plaese enter the Event Name",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(disc))
                {
                    Toast.makeText(article_for_studentposts.this,"Plaese fill the Discription of the Post",Toast.LENGTH_LONG).show();
                }
                else if(articleImageUri==null)
                {
                    Toast.makeText(article_for_studentposts.this,"Plaese Select the Image to Post",Toast.LENGTH_LONG).show();
                }
                else
                {
                    newPostProgress.setVisibility(View.VISIBLE);

                    Calendar calendardate=Calendar.getInstance();
                    SimpleDateFormat currentdate=new SimpleDateFormat("dd-MMMM-yyyy");
                    postdate=currentdate.format(calendardate.getTime());

                    Calendar calendartime=Calendar.getInstance();
                    SimpleDateFormat currenttime=new SimpleDateFormat("hh:mm a");
                    posttime=currenttime.format(calendartime.getTime());

                    postrandomname=postdate+posttime;


                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);

                    final byte[] cropbyte=byteArrayOutputStream.toByteArray();

                    StorageReference filepath=storageReference.child("postimages").child(userid+postdate+posttime+".jpg");

                    final StorageReference thumb_filepath=storageReference.child("postimages").child(userid+postdate+posttime+".jpg");

                    filepath.putFile(articleImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                UploadTask uploadTask=thumb_filepath.putBytes(cropbyte);

                                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task_thump) {

                                        thumb_downloadurl=task_thump.getResult().getDownloadUrl().toString();

                                        if(task_thump.isSuccessful())
                                        {
                                            savingpostinfo();

                                        }

                                    }
                                });


                            }

                        }
                    });


                }

            }
        });

    }

    private void savingpostinfo() {

        postref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    countpost=dataSnapshot.getChildrenCount();
                }
                else
                {
                    countpost=0;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        useref.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.hasChild("name"))
                    {
                        username=dataSnapshot.child("name").getValue().toString();
                    }
                    if(dataSnapshot.hasChild("profile"))
                    {
                        profileimage=dataSnapshot.child("profile").getValue().toString();
                    }
                    String description=articleDesc.getText().toString();

                    String counterpost=Long.toString(countpost);

                    link=articlelink.getText().toString();

                    HashMap postmap=new HashMap();
                    postmap.put("uid",current_user_id);
                    postmap.put("date",postdate);
                    postmap.put("time",posttime);
                    postmap.put("event",event);
                    postmap.put("discription",description);
                    postmap.put("postimage",thumb_downloadurl);
                    postmap.put("profileimage",profileimage);
                    postmap.put("fullname",username);
                    if(!TextUtils.isEmpty(link))
                    {
                        postmap.put("link",link);
                    }
                    postmap.put("counter",counterpost);
                    postmap.put("count","0");

                    postref.child(current_user_id+postrandomname).updateChildren(postmap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if(task.isSuccessful())
                            {
                                newPostProgress.setVisibility(View.INVISIBLE);
                                sendtomainactivity();
                            }
                            else
                            {
                                newPostProgress.setVisibility(View.INVISIBLE);
                                String msg=task.getException().toString();
                                Toast.makeText(article_for_studentposts.this,"Error Occured :"+msg,Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void sendtomainactivity() {

        Intent intent=new Intent(article_for_studentposts.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                articleImageUri = result.getUri();
                Picasso.get().load(articleImageUri).networkPolicy(NetworkPolicy.OFFLINE).into(articleImage);

                File crop_path=new File(articleImageUri.getPath());

                userid=firebaseAuth.getCurrentUser().getUid();

                try {
                    compressedImageFile=new Compressor(this)
                            .setMaxHeight(300)
                            .setMaxWidth(300).setQuality(100).compressToBitmap(crop_path);
                }

                catch (IOException e)
                {
                    e.printStackTrace();
                }






            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();


            }
        }

    }
}
