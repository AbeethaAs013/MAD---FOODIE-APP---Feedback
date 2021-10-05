package com.example.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    Button btnWith;
    TextView text_feedback3;
    RatingBar ratingBar;
    EditText editTextTextMultiLine;
    Button submit1;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        final EditText editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        submit1 = findViewById(R.id.submit1);

        DAOFeed2 dao = new DAOFeed2();

        submit1.setOnClickListener(v->{

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation(this,R.id.editTextTextMultiLine,
                RegexTemplate.NOT_EMPTY,R.string.invalid_feedback);

        /*submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext()
                            , "Successfully Feedback", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext()
                            , "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/



            Feed2 feed2 = new Feed2(editTextTextMultiLine.getText().toString());
            dao.add(feed2).addOnSuccessListener(suc->
            {
                if (awesomeValidation.validate()) {
                    Toast.makeText(this, "Successfully Feedback", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(er->
                    Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show());



        btnWith=(Button)findViewById(R.id.With);
        btnWith.setOnClickListener((z) ->{
            startActivity(new Intent(Feedback.this,Contact_Us.class));
        });

        getSupportActionBar().setTitle("FOODIE Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text_feedback3 = findViewById(R.id.text_feedback3);
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v == 0)
                {
                    text_feedback3.setText("Very Dissatisfied");
                }

                else if (v == 1)
                {
                    text_feedback3.setText("Dissatisfied");
                }

                else if (v == 2 || v == 3)
                {
                    text_feedback3.setText("Ok");
                }

                else if (v == 4)
                {
                    text_feedback3.setText("Satisfied");
                }

                else if (v == 5)
                {
                    text_feedback3.setText("Very Satisfied");
                }

                else
                {

                }

            }
        });
    });
    }
}