package com.example.online_voting_app;

import static com.example.online_voting_app.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class voteActivity extends AppCompatActivity {
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        conn = dbconnection.Initiate();

        List<String> candidateList;

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT fname FROM candidate");

            candidateList= new ArrayList<>();
            while (rs.next()) {
                candidateList.add(rs.getString("fname"));
            }

            rs.close();
        } catch (SQLException e) {
            Log.i("thesuccess", e.getMessage());
            throw new RuntimeException(e);
        }

        // and stores in the list "candidateList"
        //List<String> candidateList = Arrays.asList("Bezawit", "Yanet", "Rediet", "Emat", "Nafiyad", "Blen");

        ConstraintLayout cl = findViewById(R.id.myConstraintLayout);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(cl);

        RadioGroup radioGroup = new RadioGroup(this);
        int id = View.generateViewId();
        radioGroup.setId(id);
        LinearLayout layout = findViewById(R.id.LinearLayout);
        layout.addView(radioGroup);

        for (String item : candidateList)
        {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(item);
            radioGroup.addView(radioButton);
        }
        //radioGroup.getCheckedRadioButtonId();

        constraintSet.applyTo(cl);
        constraintSet.centerHorizontally(radioGroup.getId(), ConstraintSet.PARENT_ID);
        constraintSet.centerVertically(radioGroup.getId(), ConstraintSet.PARENT_ID);

        Button button = findViewById(R.id.vote);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_radiobutton = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selected_radiobutton);
                String selectedCandidateName = radioButton.getText().toString();
                Log.i("thesuccess",selectedCandidateName);

                sendVote(selectedCandidateName);
                // TODO: 7/2/2023 A success activity before it exits back 
                finish();
            }
        });
    }
    public void sendVote(String votedPerson){

        boolean dataget = false;
        int rowsAffected = 0;

        String sql = "select votes from votesforcandidates join candidate on votesforcandidates.id = candidate.id where candidate.fname = ?";
       try {
           int numberofvotes;
            Log.i("thesuccess", "this is reached");
           PreparedStatement statement = conn.prepareStatement(sql);
           statement.setString(1, votedPerson);
           ResultSet result = statement.executeQuery();
           Log.i("thesuccess", "this is reached");

           if (result.next()) {
               numberofvotes = result.getInt("votes");
               Log.i("thesuccess", Integer.toString(numberofvotes));
               numberofvotes++;
               Log.i("thesuccess", "this is reached");

               String sql2 = "UPDATE votesforcandidates v JOIN candidate c ON v.id = c.id SET v.votes = ? WHERE c.fname = ?";
               PreparedStatement statement2 = conn.prepareStatement(sql2);
               statement2.setInt(1, numberofvotes);
               statement2.setString(2, votedPerson);

               rowsAffected = statement2.executeUpdate();
                ///Here if row afected is > 1 return true to the perivious activity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
           }

       }catch (SQLException e){
           Log.i("thesuccess", e.getMessage());
       }


    }

}

