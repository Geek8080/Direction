package org.direction.app.test.java;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static void main(String []args){
        try{
            Server server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpSSL", "-tcpPort", "8085").start();
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/~/stackoverflow", "sa", "");
            System.out.println("Connection Established: "
                    + conn.getMetaData().getDatabaseProductName() + "/" + conn.getCatalog());

            System.out.println("Server started successfully...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
