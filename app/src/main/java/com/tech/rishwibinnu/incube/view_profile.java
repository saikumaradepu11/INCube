package com.tech.rishwibinnu.incube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class view_profile extends AppCompatActivity {

    PhotoView image;
    TextView username;

    String postkey;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        postkey=getIntent().getExtras().get("postkey").toString();

        ref=FirebaseDatabase.getInstance().getReference().child("Posts").child(postkey);

        image=findViewById(R.id.view_userprofile);
        username=findViewById(R.id.viewusername);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.hasChild("profileimage"))
                    {
                        final String profileimage=dataSnapshot.child("profileimage").getValue().toString();

                        Picasso.get().load(profileimage).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.image_placeholder)
                                .into(image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load(profileimage).placeholder(R.drawable.image_placeholder).into(image);
                                    }
                                });

                    }
                    if(dataSnapshot.hasChild("fullname"))
                    {
                        String name=dataSnapshot.child("fullname").getValue().toString();
                        username.setText(name);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
