package com.tech.rishwibinnu.incube;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_list extends AppCompatActivity {

    RecyclerView studentlist;
    DatabaseReference reference;
    FirebaseAuth auth;
    String userid;

    String postkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        postkey=getIntent().getExtras().get("postkey").toString();

        auth=FirebaseAuth.getInstance();
        userid=auth.getCurrentUser().getUid();

        studentlist=(RecyclerView)findViewById(R.id.student_list);
        studentlist.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        studentlist.setLayoutManager(linearLayoutManager);

        validatestudentlist();

    }

    private void validatestudentlist() {

        reference=FirebaseDatabase.getInstance().getReference().child("Events").child(postkey);

        FirebaseRecyclerAdapter<Students,studentlistviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Students, studentlistviewholder>
                (
                        Students.class,R.layout.registered_students_list,studentlistviewholder.class,reference
                ) {
            @Override
            protected void populateViewHolder(studentlistviewholder viewHolder, Students model, int position) {

                viewHolder.setHtno(model.getHtno());
                viewHolder.setName(model.getName());
                viewHolder.setMobile(model.getMobile());
                viewHolder.setBranch(model.getBranch());
                viewHolder.setYear(model.getYear());
                viewHolder.setSection(model.getSection());

            }
        };
        studentlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class studentlistviewholder extends RecyclerView.ViewHolder {
        View mview;
        public studentlistviewholder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }
        public void setName(String name) {
            TextView t1=mview.findViewById(R.id.d1);
            t1.setText(name);
        }
        public void setHtno(String htno) {
            TextView t2=mview.findViewById(R.id.d2);
            t2.setText(htno);
        }
        public void setMobile(String mobile) {
            TextView t3=mview.findViewById(R.id.d3);
            t3.setText(mobile);
        }
        public void setBranch(String branch) {
            TextView t4=mview.findViewById(R.id.d4);
            t4.setText(branch);
        }
        public void setYear(String year) {
            TextView t5=mview.findViewById(R.id.d5);
            t5.setText(year);
        }
        public void setSection(String section) {
            TextView t6=mview.findViewById(R.id.d6);
            t6.setText(section);
        }
    }
}
