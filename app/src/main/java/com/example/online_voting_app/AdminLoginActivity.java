package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void handleAdminLogin (View v) {
        //TODO: Check if username and password is correct and set the boolean accordingly
        boolean isAdmin = true;
        if (isAdmin) {
            Intent i = new Intent (this, adminActionsActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }

    }
}