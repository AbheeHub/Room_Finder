package com.room.saksham.kamre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ContactUsActivity extends AppCompatActivity {
    private ImageView mContactUsImage;
    private LinearLayout mLayout1;
    private LinearLayout mLayout2;
    private LinearLayout mLayoutWhatsApp;
    private LinearLayout mLayoutWebsite;
    private TextView mCall1;
    private TextView mCall2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mContactUsImage = (ImageView)findViewById(R.id.contact_us_imageview);
        mLayout1 = (LinearLayout)findViewById(R.id.contact_us_llayout1);
        mLayout2 = (LinearLayout)findViewById(R.id.contact_us_llayout2);
        mLayoutWhatsApp = (LinearLayout)findViewById(R.id.contact_us_wapp_llayout);
        mLayoutWebsite= (LinearLayout)findViewById(R.id.contact_us_site_llayout);
        mCall1 = (TextView)findViewById(R.id.contact_us_call1_textview);
        mCall2 = (TextView)findViewById(R.id.contact_us_call2_textview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Glide.with(this).load(R.drawable.room_photo).centerCrop().crossFade().into(mContactUsImage);

        mLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mCall1.getText().toString()));
                startActivity(callIntent);
            }
        });

        mLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mCall2.getText().toString()));
                startActivity(callIntent);
            }
        });

        mLayoutWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsAppIntent = new Intent("android.intent.action.MAIN");
                whatsAppIntent.setAction(Intent.ACTION_VIEW);
                whatsAppIntent.setPackage("com.whatsapp");
                String url = "https://api.whatsapp.com/send?phone=" + "+919672234498" + "&text=" + "Hi I want to contact you regarding your Ruumz android app.\nAnd I found this contact number from your Ruumz Project from Github.";
                whatsAppIntent.setData(Uri.parse(url));
                //check id there is whatsapp installed
                if(whatsAppIntent.resolveActivity(getPackageManager()) != null){
                    whatsAppIntent.setPackage("com.whatsapp");
                    startActivity(whatsAppIntent);
                }

            }
        });

        mLayoutWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visitWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rooms.co.zw"));
                if(visitWebsite.resolveActivity(getPackageManager()) != null) {
                    startActivity(visitWebsite);
                }
            }
        });


        //adding a back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          //show the back button icon
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }
}
