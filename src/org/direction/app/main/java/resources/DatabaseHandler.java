package org.direction.app.main.java.resources;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.eventProfile.EventColumn;
import org.direction.app.main.java.studentProfile.StudentColumn;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.sql.*;
import java.util.LinkedList;

public class DatabaseHandler {

    public static String ServerIP = "127.0.0.1";
    public static String Port = "8082";
    public static String ClientIP = "";
    public static Server server;
    public static Connection conn;
    public static Statement stmt;


    public static boolean updateUsers() {
        try{
            //Run this block when running this in testing mode
            /*DatabaseHandler.stmt.executeUpdate("DELETE FROM USER WHERE UID='U191'");
            DatabaseHandler.stmt.executeUpdate("INSERT INTO USER VALUES('" +
                    Main.USER.getUID() + "', '" +
                    Main.USER.getPost() + "', '" +
                    Main.USER.getName() + "', '" +
                    Main.USER.getPassword() + "', '" +
                    Main.USER.getContact() + "', '" +
                    Main.USER.getHometown() + "' " +
                    ")");

             */

            DatabaseHandler.stmt.executeUpdate("UPDATE User" +
                    " SET username='" +
                    //Main.USER.getUID() + "', '" +
                    Main.USER.getName() + "', password='" +
                    Main.USER.getPassword() + "', contact='" +
                    Main.USER.getContact() + "', hometown='" +
                    Main.USER.getHometown() + "'" +
                    " WHERE UID='" + Main.USER.getUID() + "';");
            ResultSet rs = DatabaseHandler.stmt.executeQuery("Select * from User;");
            System.out.println(rs);
            rs.first();
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));


            System.out.println("Records updated in Direction.User where uid=" + Main.USER.getUID() + "... ");
            return true;
        }catch(Exception ex){
            Platform.runLater(new Runnable(){

                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
            return false;
        }
    }



    public static int getCount(String table) {
        if(table.equalsIgnoreCase("User")){
            return 1;
        }
        if(table.equalsIgnoreCase("Event")){
            try {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(EID) FROM Event;");
                if(rs.first()){
                    return rs.getInt(1);
                }else{
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(table.equalsIgnoreCase("Student")){
            try {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(SID) FROM Student;");
                if(rs.first()){
                    return rs.getInt(1);
                }else{
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static LinkedList getStudentColumns() {
        //return LinkedList<StudentColumn>
        //step1: Retrieve all the columns from Student table and corresponding name of event and user
        //step2: Make event String to be displayed as [EID-Date]
        //step3: Make User String to be displayed as [UID-Name]
        //step4: Make StudentColumn object using these data and add these to the LinkedList
        //step4: Do this till you have one more element in the resultSet
        LinkedList<StudentColumn> StudentList = new LinkedList<>();
        try {
            ResultSet resultSet = DatabaseHandler.stmt.executeQuery("Select * FROM Student");
            while(resultSet.next()){
                StudentList.add(new StudentColumn(resultSet.getString("SID"),resultSet.getString("Name"),resultSet.getString("EID"),resultSet.getString("UID"),resultSet.getString("Gender"),resultSet.getInt("Class"),resultSet.getInt("Marks"),resultSet.getString("Contact")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentList;
    }

    public static LinkedList getStudentColumns(String eid) {
        //return LinkedList<StudentColumn>
        //step1: Retrieve all the columns from Student table and corresponding name of event and user
        //step2: Make event String to be displayed as [EID-Date]
        //step3: Make User String to be displayed as [UID-Name]
        //step4: Make StudentColumn object using these data and add these to the LinkedList
        //step4: Do this till you have one more element in the resultSet
        LinkedList<StudentColumn> StudentList = new LinkedList<>();
        try {
            ResultSet resultSet = DatabaseHandler.stmt.executeQuery("Select * FROM Student WHERE EID='" + eid + "'");
            while(resultSet.next()){
                StudentList.add(new StudentColumn(resultSet.getString("SID"),resultSet.getString("Name"),resultSet.getString("EID"),resultSet.getString("UID"),resultSet.getString("Gender"),resultSet.getInt("Class"),resultSet.getInt("Marks"),resultSet.getString("Contact")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentList;
    }

    public static LinkedList getEventColumns(String status) {
        //return LinkedList<EventColumn>
        //step1: Retrieve all the columns from Event table
        //step2: Make EventColumn object using these data and add these to the LinkedList
        //step4: Do this till you have one more element in the resultSet
        LinkedList<EventColumn> EventList = new LinkedList<>();
        try {
            ResultSet resultSet = DatabaseHandler.stmt.executeQuery("Select * FROM Event WHERE Status='" + status + "'");
            while(resultSet.next()){
                EventList.add(new EventColumn(resultSet.getString("EID"),resultSet.getString("Location"),resultSet.getString("Date"),resultSet.getInt("Budget"),resultSet.getInt("Attendance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EventList;
    }

    public static LinkedList getUserColumns() {
        LinkedList<String> userList = new LinkedList<>();
        try {
            ResultSet resultSet = DatabaseHandler.stmt.executeQuery("Select UID, username FROM USER");
            while(resultSet.next()){
                userList.add(resultSet.getString(1)+": "+resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    //public static boolean exists(){

    //}
}
