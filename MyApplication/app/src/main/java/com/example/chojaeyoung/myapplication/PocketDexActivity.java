package com.example.chojaeyoung.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import static com.example.chojaeyoung.myapplication.GameActivity.eight;
import static com.example.chojaeyoung.myapplication.GameActivity.eleven;
import static com.example.chojaeyoung.myapplication.GameActivity.five;
import static com.example.chojaeyoung.myapplication.GameActivity.four;
import static com.example.chojaeyoung.myapplication.GameActivity.nine;
import static com.example.chojaeyoung.myapplication.GameActivity.one;
import static com.example.chojaeyoung.myapplication.GameActivity.seven;
import static com.example.chojaeyoung.myapplication.GameActivity.six;
import static com.example.chojaeyoung.myapplication.GameActivity.ten;
import static com.example.chojaeyoung.myapplication.GameActivity.three;
import static com.example.chojaeyoung.myapplication.GameActivity.twelve;
import static com.example.chojaeyoung.myapplication.GameActivity.two;

public class PocketDexActivity extends Activity{

     ImageView imageView1;
     ImageView imageView2;
     ImageView imageView3;
     ImageView imageView4;
     ImageView imageView5;
     ImageView imageView6;
     ImageView imageView7;
     ImageView imageView8;
     ImageView imageView9;
     ImageView imageView10;
     ImageView imageView11;
     ImageView imageView12;



    @Override
    public void onBackPressed () {
        Intent itMain = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(itMain);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocketdex);


       /* ImageView imageview = null;

            imageview = (ImageView)findViewById(R.id.imageView);
        */
        if (one >= 1)

        {
            imageView1 = (ImageView)findViewById(R.id.imageView1);
            imageView1.setImageResource(R.drawable.g1_j);
        }
        if (two >= 1)

        {
            imageView2 = (ImageView)findViewById(R.id.imageView2);
            imageView2.setImageResource(R.drawable.g2_e);
        }
        if (three >= 1)

        {
            imageView3 = (ImageView)findViewById(R.id.imageView3);
            imageView3.setImageResource(R.drawable.g3_m);
        }
        if (four >= 1)

        {
            imageView4 = (ImageView)findViewById(R.id.imageView4);
            imageView4.setImageResource(R.drawable.p1_m);
        }
        if (five >= 1)

        {
            imageView5 = (ImageView)findViewById(R.id.imageView5);
            imageView5.setImageResource(R.drawable.p2_t);
        }
        if (six >= 1)

        {
            imageView6 = (ImageView)findViewById(R.id.imageView6);
            imageView6.setImageResource(R.drawable.p3_p);
        }
        if (seven >= 1)

        {
            imageView7 = (ImageView)findViewById(R.id.imageView7);
            imageView7.setImageResource(R.drawable.r1_n);
        }
        if (eight >= 1)

        {
            imageView8 = (ImageView)findViewById(R.id.imageView8);
            imageView8.setImageResource(R.drawable.r2_c);
        }
        if (nine >= 1)

        {
            imageView9 = (ImageView)findViewById(R.id.imageView9);
            imageView9.setImageResource(R.drawable.r3_l);
        }
        if (ten >= 1)

        {
            imageView10 = (ImageView)findViewById(R.id.imageView10);
            imageView10.setImageResource(R.drawable.y1_p);
        }
        if (eleven >= 1)

        {
            imageView11 = (ImageView)findViewById(R.id.imageView11);
            imageView11.setImageResource(R.drawable.y2_t);
        }
        if (twelve >= 1)

        {
            imageView12 = (ImageView)findViewById(R.id.imageView12);
            imageView12.setImageResource(R.drawable.y3_k);
        }

    }
}



