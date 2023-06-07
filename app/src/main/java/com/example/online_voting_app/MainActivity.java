package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchVote(View V){
        //launch the vote activity

        Intent i = new Intent(this, voteActivity.class);
        startActivity(i);
    }

    public void launchRegister(View v){
        //launch the Register Activity

        Intent i = new Intent(this, registerActivity.class);
        startActivity(i);
    }

    //I think this is working
}