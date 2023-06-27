package com.example.online_voting_app;

import static com.example.online_voting_app.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

public class voteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        //code to get list of candidates from database (using database helper)
        // and stores in the list "candidateList"
        List<String> candidateList = Arrays.asList("Bezawit", "Yanet", "Rediet", "Emat", "Nafiyad", "Blen");

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

        constraintSet.applyTo(cl);
        constraintSet.centerHorizontally(radioGroup.getId(), ConstraintSet.PARENT_ID);
        constraintSet.centerVertically(radioGroup.getId(), ConstraintSet.PARENT_ID);


    }

}

