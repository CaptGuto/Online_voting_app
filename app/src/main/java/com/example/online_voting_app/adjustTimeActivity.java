package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class adjustTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_time);

        Switch myRegisterSwitch = findViewById(R.id.registerSwitch);
        Switch myVoteSwitch = findViewById(R.id.voteSwitch);
        Switch myShowResultSwitch = findViewById(R.id.showResultSwitch);

        //TODO: Check from database and return boolean
        boolean isRegisterTime = true;
        boolean isVoteTime = true;
        boolean isResultTime = true;

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


    }
}