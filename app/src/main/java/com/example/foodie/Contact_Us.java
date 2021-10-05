package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Us extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private TextView textuser;
    private Button callbutton;

    TextView textfacebook;
    TextView textinsta;
    TextView textgmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        textuser = findViewById(R.id.textuser);
        callbutton = findViewById(R.id.callbutton);
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbutton();
            }
        });
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textfacebook = findViewById(R.id.textfacebook);
        textinsta = findViewById(R.id.textinsta);
        textgmail = findViewById(R.id.textgmail);

        textfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://m.facebook.com/");
            }
        });

        textinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/accounts/login/");
            }
        });

        textgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://mail.google.com/mail/mu/mp/752/#tl/priority/%5Esmartlabel_personal");
            }
        });
    }

    private void gotoUrl(String s)
    {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void callbutton()
    {
        String number = textuser.getText().toString();
        if (number.trim().length()>0){
            if (ContextCompat.checkSelfPermission(Contact_Us.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contact_Us.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL)
        {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            callbutton();

            else
                {
                    Toast.makeText(this, "permission DENIED", Toast.LENGTH_SHORT).show();
            }

        }
    }
}




