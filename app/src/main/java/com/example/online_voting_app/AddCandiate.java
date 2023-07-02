package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

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
        // TODO: 7/2/2023 Add a user to the candidate table
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

        if(rowsInserted > 1){
            Toast.makeText(this, "Candidate add success", Toast.LENGTH_SHORT).show();
            // TODO: 7/2/2023 do a popup instead of toast

            /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your message here").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // do things when the user clicks OK
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
             */
        }



        // TODO: 7/2/2023 Add the id and and a count of 0 to the count of candidate table
    }
}