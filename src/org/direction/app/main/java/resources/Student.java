package org.direction.app.main.java.resources;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.io.Serializable;

public class Student implements Serializable {
    private String SID;
    private String EID;
    private String UID;
    private String name;
    private int marks;
    private char gender;
    private int cls;
    private int age;
    private String address;
    private String contact;
    private String parentName;
    private String parentContact;

    public Student(String SID, String EID, String UID, String name, int marks, char gender, int cls, int age, String address, String contact, String parentName, String parentContact) {
        this.SID = SID;
        this.EID = EID;
        this.UID = UID;
        this.name = name;
        this.marks = marks;
        this.gender = gender;
        this.cls = cls;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.parentName = parentName;
        this.parentContact = parentContact;
    }

    public Student(){

    }


    public String getSID() {
        return SID;
    }

    public void setSID() {
        this.SID = generateSID();
    }

    private String generateSID() {
        String sid = "S";
        sid+=EID.substring(1);
        sid+=UID.substring(1);
        sid+=(DatabaseHandler.getCount("Student")+1);
        System.out.println(sid);
        return sid;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        if(validateCls(cls))
            this.cls = cls;
        else
            this.cls = 0;
    }

    private boolean validateCls(int cls) {
        if(cls>=1&&cls<=12)
            return true;
        return false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(validateAge(age))
            this.age = age;
        else
            this.age = 0;
    }

    private boolean validateAge(int age) {
        if(age>0&&age<150)
            return true;
        return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if(validateContact(contact))
            this.contact = contact;
        else
            this.contact = "0";
    }

    private boolean validateContact(String contact) {
        if((contact).length()==10)
                return true;
        return false;

    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }
}
