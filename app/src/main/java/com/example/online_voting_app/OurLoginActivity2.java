package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OurLoginActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_login2);
    }

    public void handleLogin(View V){
        //based on whether user exists in database or not, handle the onClick
        //TODO: add the code here to check whether the name and password entered exists in the-
        //TODO: registered table and return a boolean
        boolean exists = true;

        if (exists) {
            Intent i = new Intent(this, voteActivity.class);
            startActivity(i);
        }
        else {
            //TODO: display message to user that they have to register
            Toast.makeText(getApplicationContext(), "Please register first.", Toast.LENGTH_SHORT).show();

        }



    }
}