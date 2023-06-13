package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.security.SecureRandom;


public class registerActivity extends AppCompatActivity {

    public Button registerButton;
    public EditText fnameInput, lnameInput, idInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //Defining method to take input and lead to the next activity also passing the data
    public void registration (View registerButton) {

        dbconnection.Initiate();

        fnameInput = (EditText) findViewById(R.id.fname_input_field);
        lnameInput = (EditText) findViewById(R.id.lname_input_feild);
        idInput = (EditText) findViewById(R.id.Id_input_feild);

        //Generating password using method defined below
        String myPassword = generatePassword(10);

        Intent i = new Intent (registerActivity.this, viewOneTimePassword.class);

        String fname = fnameInput.getText().toString();
        String lname = lnameInput.getText().toString();
        String ID = idInput.getText().toString();;


        if(fname.isEmpty() || lname.isEmpty() ||ID.isEmpty() ){
            Toast.makeText(this, "Please input both your first and last name", Toast.LENGTH_SHORT).show();
        }
        else{
            dbconnection.Initiate();


            registerDb registeration = new registerDb(fname,lname, ID);
            boolean citizeExists = registeration.checkCitizenExists();

            if(citizeExists){ // if the citizenExist returns true(meaning with the above initiation of the registerdb class it has found id, fname, lname for it
                Toast.makeText(this, "The check was successful", Toast.LENGTH_SHORT).show();
                registeration.registerVoter();

                //Passing the name, id, and password to next activity
                i.putExtra("fname", fname);

                i.putExtra("lname", lname);

                i.putExtra("ID", ID);

                i.putExtra("Password", myPassword);

                startActivity(i);
            }
            else{
                Toast.makeText(this, "There was no match", Toast.LENGTH_SHORT).show();
            }
        }



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