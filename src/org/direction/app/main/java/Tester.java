package org.direction.app.main.java;

import org.direction.app.main.java.resources.*;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tester {
    public static void main(String []args){
        User us = new User();
        us.setPost("Technical Head");
        if(FileHandler.deflate("user.ser",us))
        System.out.println("Initialised user for testing.");

        Configuration conf = new Configuration();
        conf.DBExists = true;
        conf.user = true;
        conf.UID = "U192";
        conf.post = "Technical Head";
        conf.password = "1234";
        if(FileHandler.deflate("config.properties",conf))
        System.out.println("Initialised configurations for testing.");


        //try {
        //    DatabaseHandler.server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpSSL").start();
        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}
        //System.out.println("Server Started Successfully...\nServer IP: " + DatabaseHandler.ServerIP);
        //DatabaseHandler.ServerIP = "127.0.0.1";
        //try {
        //    Class.forName("org.h2.Driver");
        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}
        //try {
        //    DatabaseHandler.conn = DriverManager.getConnection("jdbc:h2:ssl://" + DatabaseHandler.ServerIP + "/~/Documents/Direction/Data/test", "", "");
        //} catch (Exception ex) {
        //    System.out.println("Couldn't make connection...\n" + ex.getMessage());
        //}
        //try {
        //    DatabaseHandler.stmt = DatabaseHandler.conn.createStatement();
        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}

        //DatabaseHandler.updateUsers();
    }
}
