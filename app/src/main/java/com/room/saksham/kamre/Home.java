package com.room.saksham.kamre;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {
    CardView Sell,NewsandNotifications,links,Buy,add ;
    Intent i ;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        ll = (LinearLayout) findViewById(R.id.ll);
        Sell = (CardView) findViewById(R.id.sellroomId);
        NewsandNotifications = (CardView) findViewById(R.id.newsAndNotifications);
        Buy = (CardView) findViewById(R.id.buyroomId);
//        links = (CardView) findViewById(R.id.links);
//        add = (CardView) findViewById(R.id.add);
//        i = new Intent(this,ae.class);
//        bank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(i);
//            }
//        });
//
//
        NewsandNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Home.this,NewsAndNotifications.class);
                startActivity(i2);
            }
        });
//
//

        Sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Home.this,SellHome.class);
                startActivity(i3);
            }
        });

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Home.this,BuyHomeActivity.class);
                startActivity(i3);
            }
        });
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i4 = new Intent(Home.this,add.class);
//                startActivity(i4);
//            }
//        });
//
//        ideas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i4 = new Intent(Home.this,ideas.class);
//                startActivity(i4);
//            }
//        });

    }
}
