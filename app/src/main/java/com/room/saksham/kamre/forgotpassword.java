package com.room.saksham.kamre;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgotpassword extends AppCompatActivity {
    public EditText username,email,password,conpassword;
    public Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        username= (EditText)findViewById(R.id.for_user);
        email= (EditText)findViewById(R.id.for_email);
        password= (EditText)findViewById(R.id.new_password);
        conpassword= (EditText)findViewById(R.id.con_new_pass) ;
        btn=(Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString();
                String eid= email.getText().toString();
                String pass= password.getText().toString();
                String conpass= conpassword.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "reset password");
                intent.putExtra(Intent.EXTRA_TEXT, "Email: "+eid+"\n Username is: "+uname+"\n Password is: "+pass+"\n Retype Password is: "+conpass);
                intent.setData(Uri.parse("mailto:adarshtrivedi029@gmail.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
            }
        });

    }
}
