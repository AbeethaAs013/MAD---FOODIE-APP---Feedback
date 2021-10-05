package com.example.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //EditText edit_text1;
    //EditText edit_text2;
    //EditText edit_text3;
    //EditText edit_text4;
    Button submitbutton;
    Button nextbutton;
    //DatabaseReference ref;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nextbutton = (Button) findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener((V) ->
        {
            startActivity(new Intent(com.example.foodie.MainActivity.this, Feedback.class));


            final EditText edit_name = findViewById(R.id.edit_name);
            final EditText edit_Inquiry = findViewById(R.id.edit_Inquiry);
            final EditText edit_Contact = findViewById(R.id.edit_Contact);
            final EditText edit_Inquire = findViewById(R.id.edit_Inquire);
            submitbutton = findViewById(R.id.submitbutton);

            submitbutton.setOnClickListener(v -> {

                //initialize validation style
                awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                //Add validation for name
                awesomeValidation.addValidation(this, R.id.edit_name,
                        RegexTemplate.NOT_EMPTY, R.string.invalid_name);

                //validation for Inquiry
                awesomeValidation.addValidation(this, R.id.edit_Inquiry,
                        RegexTemplate.NOT_EMPTY, R.string.invalid_inquiry);

                //validation for contact
                awesomeValidation.addValidation(this, R.id.edit_Contact
                        , "[5-9]{1}[0-9]{9}$", R.string.invalid_number);

                //validation for Inquire
                awesomeValidation.addValidation(this, R.id.edit_Inquire,
                        RegexTemplate.NOT_EMPTY, R.string.invalid_inquire);




            /*submitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (awesomeValidation.validate()) {
                        Toast.makeText(getApplicationContext()
                                , "Successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext()
                                ,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });*/


                DAOFeed dao = new DAOFeed();

                Feed feed = new Feed(edit_name.getText().toString(), edit_Inquiry.getText().toString(), edit_Contact.getText().toString(), edit_Inquire.getText().toString());
                dao.add(feed).addOnSuccessListener(suc ->
                {
                    if (awesomeValidation.validate()) {
                        Toast.makeText(this, "Inquiry was submitted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(er ->
                        Toast.makeText(this, "" + er, Toast.LENGTH_SHORT).show());




            /*HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", edit_name.getText().toString());
            hashMap.put("Inquiry", edit_Inquiry.getText().toString());
            hashMap.put("Contact", edit_Contact.getText().toString());
            hashMap.put("Inquire", edit_Inquire.getText().toString());

            dao.update("-MkMlKbKhpREFol5JVKr", hashMap).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er ->

            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });*/


            /*dao.remove("-MkMlKbKhpREFol5JVKr").addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Record is removed", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er ->

            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });*/

            });

        });
    }
    }


