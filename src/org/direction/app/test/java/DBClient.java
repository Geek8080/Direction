package org.direction.app.test.java;

import org.direction.app.main.java.resources.DatabaseHandler;
import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClient {
    public static void main(String []args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn;
        Statement stmt;
        try{
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/stackoverflow", "sa", "");
            stmt = conn.createStatement();
        }catch(Exception ex){
            System.out.println("Couldn't make connection...\n" + ex.getMessage());
        }

        //RunScript.execute(DatabaseHandler.conn, new FileReader("/org/direction/app/main/java/resources/db.sql"));
        System.out.println("DB successfully created...");

    }
}
