package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

    }
    public void GetWinner(View v){

        String sql = "SELECT candidate.fname, candidate.lname FROM candidate JOIN votesforcandidates ON candidate.id = votesforcandidates.id WHERE votesforcandidates.votes = (SELECT MAX(votes) FROM votesforcandidates)";
        Connection connected;
        connected = dbconnection.Initiate();

        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String fname = result.getString("fname");
                String lname = result.getString("lname");

                TextView nameoutput = findViewById(R.id.showWinner);

                nameoutput.setText(fname + "  " + lname);
            }
        }catch (SQLException e){
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}