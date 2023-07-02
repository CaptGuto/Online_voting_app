package com.example.online_voting_app;


import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    public static Connection Initiate() {

        Log.i("thesuccess", "this method has been called");
        String url = "jdbc:mysql://10.6.196.174/VOTE";
        String username = "voting";
        String password = "qwertyuiop0987654321";
        Connection conn = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            conn = DriverManager.getConnection(url, username, password);
            Log.i("thesuccess", "connection success");

            return conn;
        } catch (SQLException e) {

            Log.i("thesuccess", "this is not working");
            Log.i("thesuccess", e.getMessage());
            Log.i("thesuccess", e.getSQLState());
            Log.i("thesuccess", String.valueOf(e.getErrorCode()));
            System.err.println(e.getMessage());

            return conn;
        }
    }

}
