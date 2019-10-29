package org.direction.app.main.java.eventProfile;

public class EventColumn {
    public String EID;
    public String location;
    public String date;
    public int budget;
    public int attendance;

    public EventColumn(String EID, String location, String date, int budget, int attendance) {
        this.EID = EID;
        this.location = location;
        this.date = date;
        this.budget = budget;
        this.attendance = attendance;
    }

    public String getEID() {
        return EID;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getBudget() {
        return budget;
    }

    public int getAttendance() {
        return attendance;
    }
}
