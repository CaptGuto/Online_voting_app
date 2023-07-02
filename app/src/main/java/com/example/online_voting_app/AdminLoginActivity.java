package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    ////Still don't think this should be the place
    public void handleAdminLogin (View v) {
        //TODO: Check if username and password is correct and set the boolean accordingly

        Connection connected;
        connected = dbconnection.Initiate();
        boolean isAdmin = false;

        TextView usernameinput = findViewById(R.id.usernameInputField);
        TextView passwordinput = findViewById(R.id.passwordInputField);

        String USERNAME = usernameinput.getText().toString().toLowerCase().trim();
        String PASSWORD = passwordinput.getText().toString().toLowerCase().trim();

        String sql = "SELECT username, password FROM administrators WHERE username = ?";

        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            statement.setString(1, USERNAME);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                // TODO: 7/2/2023 create a show password btn to make the password as long as the button is pressed!!!!!! 
                if(USERNAME.equals(username) && PASSWORD.equals(password)){
                    isAdmin = true; // T
                }

            }

        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (isAdmin) {
            Intent i = new Intent (this, adminActionsActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }

    }
}