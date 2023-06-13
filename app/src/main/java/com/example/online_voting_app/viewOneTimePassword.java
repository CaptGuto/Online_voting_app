package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import android.os.Bundle;
import java.security.SecureRandom;

public class viewOneTimePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one_time_password);

        //Retrieving user input from the previous activity and assigning them to string references
        String userName = getIntent().getStringExtra("fname") + "  " + getIntent().getStringExtra("lname");
        String userID = getIntent().getStringExtra("ID");
        String OTP = getIntent().getStringExtra("Password");

        //Getting references to the name and id text views
        TextView nameField = findViewById(R.id.nameOutPutter);
        TextView IDField = findViewById(R.id.idOutPutter);
        TextView passwordField = findViewById(R.id.passwordOutPutter);

        //Setting text of text views to the user input values
        nameField.setText(userName);
        IDField.setText(userID);

        //Setting text of password view to the generated password
        passwordField.setText(OTP);

    }




}