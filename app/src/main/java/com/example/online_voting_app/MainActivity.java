package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchLogin(View V){
        //launch the login activity

        Intent i = new Intent(this, OurLoginActivity2.class);
        startActivity(i);
    }

    public void launchRegister(View v){
        //launch the Register Activity

        Intent i = new Intent(this, registerActivity.class);
        startActivity(i);
    }

    public void launchResult (View v) {
        //launching the view result activity
        boolean timeIsRight = true;

        if (timeIsRight) {
            Intent i = new Intent(this, viewResultActivity.class);
            startActivity(i);

            //TODO: display the candidate with the highest count by retrieving the name
            // from the database and generating a text view here saying "winner is ___!"
        }
        else {
            Toast.makeText(this, "Results are not available now.", Toast.LENGTH_SHORT).show();
        }

    }
    //I think this is working
}