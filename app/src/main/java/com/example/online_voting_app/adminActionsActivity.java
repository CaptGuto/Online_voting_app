package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminActionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_actions);
    }

    public void launchAddCandidate(View v){
        Intent i = new Intent(this, AddCandiate.class);
        startActivity(i);
    }
}

// TODO: 7/3/2023 We might add delete candidate 