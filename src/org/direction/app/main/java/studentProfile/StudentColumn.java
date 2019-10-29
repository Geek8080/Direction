package org.direction.app.main.java.studentProfile;

public class StudentColumn {

    public String SID;
    public String name;
    public String event;
    public String user;
    public String gender;
    public Integer cls;
    public Integer marks;
    public String contact;

    public StudentColumn(String SID, String name, String event, String user, String gender, Integer cls, Integer marks, String contact) {
        this.SID = SID;
        this.name = name;
        this.event = event;
        this.user = user;
        this.gender = gender;
        this.cls = cls;
        this.marks = marks;
        this.contact = contact;
    }

    public String getSID() {
        return SID;
    }

    public String getName() {
        return name;
    }

    public String getEvent() {
        return event;
    }

    public String getUser() {
        return user;
    }

    public String getGender() {
        return gender;
    }

    public Integer getCls() {
        return cls;
    }

    public Integer getMarks() {
        return marks;
    }

    public String getContact() {
        return contact;
    }

}
