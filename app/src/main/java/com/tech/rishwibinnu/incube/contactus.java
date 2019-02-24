package com.tech.rishwibinnu.incube;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contactus extends AppCompatActivity {

    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        b1=findViewById(R.id.call1);
        b2=findViewById(R.id.call2);
        b3=findViewById(R.id.call3);
        b4=findViewById(R.id.call4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:8121496755" ));
                startActivity(intent1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9951128452" ));
                startActivity(intent2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:8328274702" ));
                startActivity(intent3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9849408908" ));
                startActivity(intent4);
            }
        });

    }
}
