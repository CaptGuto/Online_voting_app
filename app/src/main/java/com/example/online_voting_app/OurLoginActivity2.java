package com.example.online_voting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OurLoginActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_login2);
    }



    public void handleLogin(View V) throws SQLException {
        TextView fnameInput = findViewById(R.id.fname_input_field);
        TextView lnameInput = findViewById(R.id.lname_input_feild);
        TextView idInput = findViewById(R.id.id_input_feild);
        TextView otpInput = findViewById(R.id.otp_input_feild);

        String fname = fnameInput.getText().toString().toLowerCase().trim();
        String lname = lnameInput.getText().toString().toLowerCase().trim();
        String id = idInput.getText().toString().toUpperCase().trim();
        String password = otpInput.getText().toString().trim();


        //based on whether user exists in database or not, handle the onClick
        //TODO: add the code here to check whether the name and password entered exists in the-
        //TODO: registered table and return a boolean
        boolean exists = true;
        int returned_message = 0;
        if(fname.isEmpty()){
            Toast.makeText(this, "Please Input First Name", Toast.LENGTH_SHORT).show();
        } else if(lname.isEmpty()){
            Toast.makeText(this, "Please Input Last Name", Toast.LENGTH_SHORT).show();
        } else if(id.isEmpty()){
            Toast.makeText(this, "Please Input ID", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show();
        }
        // TODO: 6/30/2023 continue the else if condition for more cases
        else {
            returned_message = registerDb.dblogin(fname, lname, id, password);
        }


        if(returned_message == 1){
            Toast.makeText(this, "No such user exists", Toast.LENGTH_SHORT).show();
        }
        else if(returned_message == 2){
            Toast.makeText(this, "You have already voted", Toast.LENGTH_SHORT).show();
        }
        else if (returned_message == 3) {
            Intent i = new Intent(this, voteActivity.class);
            startActivity(i);

                Log.i("thesuccess", "this returns true");
                userHasVoted(id);

            //if it returns true update the registered tables voted to true so that the user doesn't vote again!!!!!!!
            finish();
        }
        else if (returned_message == 4){
            Toast.makeText(this, "The credentials you provided is incorrect ", Toast.LENGTH_SHORT).show();
        }
        else { ////Don't think this code matters
            //TODO: display message to user that they have to register
            Toast.makeText(getApplicationContext(), "Please register first.", Toast.LENGTH_SHORT).show();

        }

    }

    /////Don't think this is it's place!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void userHasVoted(String theid) {
        Connection conn;
        conn = dbconnection.Initiate();

        Log.i("thesuccess", theid);

        try{
            String sql = "UPDATE registered SET voted = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setString(2,theid);

            int rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            Log.i("thesuccess", e.getMessage());
        }




    }
}