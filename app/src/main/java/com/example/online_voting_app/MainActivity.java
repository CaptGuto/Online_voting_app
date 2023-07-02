package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        boolean timeIsRight = false;

        String sql = "select voteEnd from timer";
        Connection connected;
        connected = dbconnection.Initiate();

        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                timeIsRight = result.getBoolean("VoteEnd");

            }
        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        if (timeIsRight) {
            Intent i = new Intent(this, viewResultActivity.class);
            startActivity(i);

        }
        else {
            Toast.makeText(this, "Results are not available now.", Toast.LENGTH_SHORT).show();
        }

    }
    //I think this is working

    public void launchAdminLogin(View v){
        Intent i = new Intent(this, AdminLoginActivity.class);
        startActivity(i);
    }
}