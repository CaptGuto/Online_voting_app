package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.security.SecureRandom;
import java.sql.SQLException;


public class registerActivity extends AppCompatActivity {

    public Button registerButton;
    public EditText fnameInput, lnameInput, idInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //Defining method to take input and lead to the next activity also passing the data
    public void registration (View registerButton) throws SQLException {

        //dbconnection.Initiate();

        fnameInput = (EditText) findViewById(R.id.fname_input_field);
        lnameInput = (EditText) findViewById(R.id.lname_input_feild);
        idInput = (EditText) findViewById(R.id.otp_input_feild);

        //Generating password using method defined below
        String myPassword = PasswordGenerator.generatePassword(10);

        Intent i = new Intent (registerActivity.this, viewOneTimePassword.class);

        String fname = fnameInput.getText().toString().toLowerCase().trim();
        String lname = lnameInput.getText().toString().toLowerCase().trim();
        String ID = idInput.getText().toString().toUpperCase().trim();


        if(fname.isEmpty() || lname.isEmpty() ||ID.isEmpty() ){
            Toast.makeText(this, "Please input both your first, last name and ID", Toast.LENGTH_SHORT).show();
        }
        // TODO: 6/13/2023 look for other conditions that might break it
        else{
            dbconnection.Initiate();

            registerDb registeration = new registerDb(fname,lname, ID);
            boolean citizeExists = registeration.checkCitizenExists();

            if(citizeExists){ // if the citizenExist returns true(meaning with the above initiation of the registerdb class it has found id, fname, lname for it
                Toast.makeText(this, "The check was successful", Toast.LENGTH_SHORT).show();


                // TODO: 6/27/2023 do the actual registering on the database
                if(registeration.registerVoter(myPassword)){
                    //Passing the name, id, and password to next activity
                    i.putExtra("fname", fname);

                    i.putExtra("lname", lname);

                    i.putExtra("ID", ID);

                    i.putExtra("Password", myPassword);



                    // TODO: 6/27/2023 get the data of the citizens age and gender as an addition

                    startActivity(i);
                }

                else{
                    Toast.makeText(this, "Registration failed ! ", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(this, "There was no match", Toast.LENGTH_SHORT).show();
            }
        }



    }

    /*private static String generatePassword (int length)
    {
        //defining the list of characters the password can contain
        final String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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
    }*/

    public static void  toster(Context context, String message) {

        Log.i("thesuccess", "this method has been called");
        Toast.makeText(context, "The check was successful", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}