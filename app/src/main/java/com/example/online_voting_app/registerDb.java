package com.example.online_voting_app;

import android.util.Log;

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
    private String fname;
    private String lname;
    private String ID;
    private String password;
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

    public boolean checkCitizenExists() {
        dbconnection connect = new dbconnection();
        Connection connected;
        try {
            connected = connect.Initiate();
            String sql = "SELECT fname, lname, id FROM citizen WHERE id = ?";
            PreparedStatement statement = connected.prepareStatement(sql);
            statement.setString(1, ID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String fName = result.getString("fname");
                Log.i("thesuccess",fName);
                String lName = result.getString("lname");
                Log.i("thesuccess", lName);
                String id = result.getString("id");
                Log.i("thesuccess", id);

                // TODO: 6/13/2023 trim the white spaces
                if (fName.equals(this.fname) && lName.equals(this.lname) && id.equals(this.ID)) {
                    Log.i("thesuccess", "thispoint is reached ");
                    this.id_found = true;
                } else {
                    this.id_found = false;
                }
            } else {
                System.out.println("No rows found with ID: " + ID);
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

    public void registerVoter(){
        // TODO: 6/13/2023 write the definition of the class
    }
}
