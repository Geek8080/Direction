package org.direction.app.main.java.resources;

import javafx.application.Platform;

import java.rmi.server.UID;
import java.util.ArrayList;

public class Event {

    private String EID;
    private String location;
    private ArrayList<String> members;
    private String date;
    private int budget = 0;
    private int attendance = 0;
    private String status;


    public String getEID() {
        return EID;
    }

    public void setEID() {
        this.EID = generateEID();
    }

    private String generateEID() {
        //the EID will have format as: E[first three letters of the location][count]...
        //this way we may keep easy count of all the events organised at a particular place
        try{
            String id = "E";

            String loc = "";
            for(int i = 0;i<location.length()&&loc.length()<3;i++){
                if((location.charAt(i)>='A'&&location.charAt(i)<='Z')||(location.charAt(i)>='a'&&location.charAt(i)<='z'))
                    loc += location.charAt(i);
            }
            id+=loc.toLowerCase();

            int count = DatabaseHandler.getCount("Event");


            id+=(count+1);

            return id;
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("Initialising event","Something went wrong at Event.java[47].");
                }
            });

            return null;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
