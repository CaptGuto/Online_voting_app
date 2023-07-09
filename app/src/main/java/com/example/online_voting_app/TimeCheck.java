package com.example.online_voting_app;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeCheck {
    public static boolean checkRegestrationTime(){
        String sql = "select regestration from timer";
        Connection connected;
        connected = dbconnection.Initiate();
        boolean timeIsRight = false;
        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                timeIsRight = result.getBoolean("regestration");

            }
        } catch (SQLException e) {
            Log.i("thesuccess", e.getMessage());
        }

        return timeIsRight;
    }
    public static boolean checkVotingOpen(){
        String sql = "select voteEnd from timer";
        Connection connected;
        connected = dbconnection.Initiate();
        boolean timeIsRight = false;
        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                timeIsRight = result.getBoolean("VoteEnd");

            }
        } catch (SQLException e) {
            Log.i("thesuccess", e.getMessage());
        }

        return timeIsRight;
    }
    public static boolean checkVoteEnd() {
        String sql = "select voting from timer";
        Connection connected;
        connected = dbconnection.Initiate();
        boolean timeIsRight = false;
        try {
            PreparedStatement statement = connected.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                timeIsRight = result.getBoolean("voting");

            }
        } catch (SQLException e) {
            Log.i("thesuccess", e.getMessage());
        }

        return timeIsRight;
    }
}
