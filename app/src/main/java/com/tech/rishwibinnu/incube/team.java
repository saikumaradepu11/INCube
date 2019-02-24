package com.tech.rishwibinnu.incube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class team extends AppCompatActivity {

    TextView rtv,stv,ratv,htv,mtv,rtv1,stv1,ratv1,htv1,mtv1;

    ImageView rishwipic,saipic,ravipic,madhavipic,hodpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);



        rtv = findViewById(R.id.rishwitxt);
        stv = findViewById(R.id.saitxt);
        ratv = findViewById(R.id.ravindertxt);
        htv = findViewById(R.id.madhavitxt);
        mtv = findViewById(R.id.hodtxt);
        rtv1 = findViewById(R.id.rishwitxt1);
        stv1 = findViewById(R.id.saitxt1);
        ratv1 = findViewById(R.id.ravindertxt1);
        htv1 = findViewById(R.id.madhavitxt1);
        mtv1 = findViewById(R.id.hodtxt1);

        rishwipic=findViewById(R.id.rishwipic);
        saipic=findViewById(R.id.saipic);
        ravipic=findViewById(R.id.ravinderpic);
        madhavipic=findViewById(R.id.madhavipic);
        hodpic=findViewById(R.id.hodpic);


        rtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,RishwiActivity.class);
                startActivity(i);

            }
        });

        rishwipic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(team.this,RishwiActivity.class);
                startActivity(r);
            }
        });

        stv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,SaiActivity.class);
                startActivity(i);

            }
        });

        saipic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sai = new Intent(team.this,SaiActivity.class);
                startActivity(sai);

            }
        });

        ratv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,RavinderActivity.class);
                startActivity(i);

            }
        });

        ravipic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ravi = new Intent(team.this,RavinderActivity.class);
                startActivity(ravi);
            }
        });

        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,HodActivity.class);
                startActivity(i);

            }
        });

        madhavipic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ma = new Intent(team.this,MadhaviActivity.class);
                startActivity(ma);
            }
        });

        htv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,MadhaviActivity.class);
                startActivity(i);

            }
        });

        hodpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(team.this,HodActivity.class);
                startActivity(h);
            }
        });

        rtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,RishwiActivity.class);
                startActivity(i);

            }
        });

        stv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,SaiActivity.class);
                startActivity(i);

            }
        });

        ratv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,RavinderActivity.class);
                startActivity(i);

            }
        });

        mtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,HodActivity.class);
                startActivity(i);

            }
        });

        htv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(team.this,MadhaviActivity.class);
                startActivity(i);

            }
        });




    }
}
