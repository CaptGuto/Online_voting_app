package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class adjustTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_time);

        Switch myRegisterSwitch = findViewById(R.id.registerSwitch);
        Switch myVoteSwitch = findViewById(R.id.voteSwitch);
        Switch myShowResultSwitch = findViewById(R.id.showResultSwitch);

        boolean isRegisterTime = false;
        boolean isVoteTime = false;
        boolean isResultTime = false;

        Connection conn = dbconnection.Initiate();


        String sql = "select regestration, voting, voteEnd from timer";

        try{
            Statement statement = conn.createStatement();  ///couldn't understand the difference
            ResultSet result = statement.executeQuery(sql);


            if(result.next()){
                isRegisterTime = result.getBoolean("regestration");
                isVoteTime = result.getBoolean("voting");
                isResultTime = result.getBoolean("voteEnd");
            }

        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("thesuccess", e.getMessage());
        }



        if (isRegisterTime) {
            myRegisterSwitch.setChecked(true);
        }
        else {
            myRegisterSwitch.setChecked(false);
        }

        if (isVoteTime) {
            myVoteSwitch.setChecked(true);
        }
        else {
            myVoteSwitch.setChecked(false);
        }

        if (isResultTime) {
            myShowResultSwitch.setChecked(true);
        }
        else {
            myShowResultSwitch.setChecked(false);
        }

        Button button = findViewById(R.id.addCandidateBtn);


        button.setOnClickListener(new View.OnClickListener() {

            int rowsInserted = 0;

            Switch myRegisterSwitch = findViewById(R.id.registerSwitch);
            Switch myVoteSwitch = findViewById(R.id.voteSwitch);
            Switch myShowResultSwitch = findViewById(R.id.showResultSwitch);

            boolean registrationTimer;
            boolean votingTimer;
            boolean voteclosedtimer;


            @Override
            public void onClick(View v) {

                if (myRegisterSwitch.isChecked()){
                    registrationTimer = true;
                }else{
                    registrationTimer = false;
                }
                if (myVoteSwitch.isChecked()){
                    votingTimer = true;
                }else{
                    votingTimer = false;
                }
                if (myShowResultSwitch.isChecked()){
                    voteclosedtimer = true;
                }else{
                    voteclosedtimer = false;
                }

                String sql = "UPDATE timer set regestration = ?, voting = ?, voteEnd = ? ";
                try{
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setBoolean(1,registrationTimer);
                    statement.setBoolean(2, votingTimer);
                    statement.setBoolean(3,voteclosedtimer);

                    rowsInserted = statement.executeUpdate();
                }catch(SQLException e){
                    Log.i("thesuccess", e.getMessage());
                    Toast.makeText(adjustTimeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if(rowsInserted > 0){
                    // TODO: 7/3/2023 A pop up to show that the time update was success then press okay to close it then the finish()

                    finish();
                }
            }
        });
        // TODO: 7/3/2023 onclick method to send the current state of the the switch
    }
}