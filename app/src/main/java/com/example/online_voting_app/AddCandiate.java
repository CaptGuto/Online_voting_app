package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCandiate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candiate);
    }

    public void AddCandidate(View v){
        Connection connect = dbconnection.Initiate();


        TextView firstnameinput = findViewById(R.id.firstNameinputcan);
        TextView lastnameinput = findViewById(R.id.lastnameinputcan);
        TextView idinput = findViewById(R.id.idinputcan);

        String fname = firstnameinput.getText().toString().toLowerCase().trim();
        String lname = lastnameinput.getText().toString().toLowerCase().trim();
        String id = idinput.getText().toString().toUpperCase().trim();

        String sql = "INSERT INTO candidate (fname, lname, id ) VALUES (?, ?, ?)";
        int rowsInserted = 0;

        try{
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, id);

            rowsInserted = statement.executeUpdate();

            Log.i("thesuccess", "this indeed worked");

        }catch (SQLException e){
            Log.i("thesuccess", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            // TODO: 6/29/2023 make the message to be displayed specific to the error
            Log.i("thesuccess", "the toster call works");
        }

        if(rowsInserted > 0){
            // TODO: 7/2/2023 do a popup instead of toast

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Candidate was added successfully").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }



        // TODO: 7/2/2023 Add the id and and a count of 0 to the count of candidate table
    }
}