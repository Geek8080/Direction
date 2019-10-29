package org.direction.app.main.java.resources;

import javafx.application.Platform;

import java.io.Serializable;

public class User implements Serializable {
    private String UID;
    private String name;
    private String post;
    private String password;
    private String contact;
    private String hometown;
    private String yearOfJoining;

    /*
    This constructor is only for testing purpose...
     */
    public User(String uid){
        this.UID = uid;
    }

    public User(){
        yearOfJoining = Utils.getYear();
        setUID(generateUID());
    }

    private String generateUID() {
        //UID has format U[last 2 digits of joining year][count]
        try{

            String id = "U" + yearOfJoining.charAt(2) + yearOfJoining.charAt(3);
            int count = DatabaseHandler.getCount("User");
            id += (count+1);
            return id;

        }catch(Exception ex){

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("Initialising user","Something went wrong at User.java[20].");
                }
            });

            return null;

        }
    }

    public String getUID() {
        return UID;
    }

    private void setUID(String UID){
        this.UID = UID;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getYearOfJoining() {
        return yearOfJoining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
