package com.example.online_voting_app;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registerDb {

    registerDb(String fname, String lname, String id){
        this.ID = id;
        this.lname = lname;
        this.fname = fname;
    }

    Connection connected;

    private String fname;
    private String lname;
    private String ID;
    private String password;


    private String age;
    private String gender;

    private boolean id_found;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public boolean isId_found() {
        return id_found;
    }
    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    dbconnection connect = new dbconnection();


    public boolean checkCitizenExists() {

        try {
            connected = connect.Initiate();
            String sql = "SELECT fname, lname, id FROM citizen WHERE id = ?";
            PreparedStatement statement = connected.prepareStatement(sql);
            statement.setString(1, ID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String fName = result.getString("fname");
                String lName = result.getString("lname");
                String id = result.getString("id");

                // TODO: 6/27/2023 check weather the person is allowed to vote age
                // TODO: 6/13/2023 trim the white spaces

                if (fName.equals(this.fname) && lName.equals(this.lname) && id.equals(this.ID)) {
                    Log.i("thesuccess", "thispoint is reached ");
                    this.id_found = true;
                } else {
                    this.id_found = false;
                }
            } else {
                Log.i("thesucsses","No rows found with ID: " + ID);
                this.id_found = false;
            }

            result.close();
            statement.close();
            connected.close();
        } catch (SQLException e) {
            Log.i("thesuccess", e.getMessage());
            // handle the exception appropriately
        }
        return this.id_found;
    }

    public boolean registerVoter(String password) throws SQLException {

        Log.i("thesuccess", "this method has been called");

        String fName = null;
        String lName = null;
        String id = null;
        String Age = null;
        String gender = null;

        boolean dataget = false;

        try {
            connected = connect.Initiate();
            String sql = "SELECT fname, lname, id, age , gender FROM citizen WHERE id = ?";
            PreparedStatement statement = connected.prepareStatement(sql);
            statement.setString(1, ID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                fName = result.getString("fname");
                lName = result.getString("lname");
                id = result.getString("id");
                Age = result.getString("age");
                gender = result.getString("gender");

                dataget = true;
                String did_it_work;

                if(dataget) did_it_work = "true"; else did_it_work = "false";
            }


        }
        catch (SQLException e) {
            throw new SQLException(e);
        }

        String sql = "INSERT INTO registered (fname, lname, id , age, gender, oneTimePassword) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsInserted = 0;

        try{
            if(dataget){
                PreparedStatement statement = connected.prepareStatement(sql);
                statement.setString(1, fName);
                statement.setString(2, lName);
                statement.setString(3, id);
                statement.setInt(4, Integer.parseInt(Age));
                statement.setString(5, gender);
                statement.setString(6, password);
                rowsInserted = statement.executeUpdate();

                Log.i("thesuccess", "this indeed worked");
            }
        }catch (SQLException e){
            Log.i("thesuccess", e.getMessage());

            Log.i("thesuccess", "class gets created");
            //registerActivity.toster(registerActi,e.getMessage());
            // TODO: 6/29/2023 make the message to be displayed specific to the error  
            Log.i("thesuccess", "the toster call works");

        }

        if(rowsInserted > 0){
            return true;
        }
        else  return false;
    }
    public static int dblogin(String fname, String lname, String ID, String password) throws SQLException {
        Connection conn;
        conn = dbconnection.Initiate();

        String sql = "SELECT fname, lname, id, oneTimePassword, voted FROM registered WHERE id = ?";
        String fName="", lName = "", id = "",Password = "";

        boolean voted = false;

        try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, ID);
        ResultSet result = statement.executeQuery();

            if (result.next()) {
                fName = result.getString("fname");
                lName = result.getString("lname");
                id = result.getString("id");
                Password = result.getString("oneTimePassword");
                voted = result.getBoolean("voted");

            }
        }catch (SQLException e){
            Log.i("thesuccess", e.getMessage()); 
        }

        if(id.isEmpty()){
            return 1;
        } else if (voted == true) {
            return 2;
        }else if (fname.equals(fName) && lname.equals(lName)&& id.equals(ID) && password.equals(Password)) {
            return 3;
        } else {
            return 4;
        }


    }
}