package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.security.SecureRandom;


public class registerActivity extends AppCompatActivity {

    public Button registerButton;
    public EditText name, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //Defining method to take input and lead to the next activity also passing the data
    public void myOnClick (View registerButton) {
        name = (EditText) findViewById(R.id.name_input_field);
        id = (EditText) findViewById(R.id.id_input_field);

        //Generating password using method defined below
        String myPassword = generatePassword(10);

        Intent i = new Intent (registerActivity.this, viewOneTimePassword.class);

        //Passing the name, id, and password to next activity
        i.putExtra("name", name.getText().toString());

        i.putExtra("ID", id.getText().toString());

        i.putExtra("Password", myPassword);

        startActivity(i);

    }

    private static String generatePassword (int length)
    {
        //defining the list of characters the password can contain
        final String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        //randomly choosing a character by each iteration of the loop and appending to the
        //StringBuilder instance
        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(charList.length());
            password.append(charList.charAt(randomIndex));
        }

        return password.toString();
    }
}