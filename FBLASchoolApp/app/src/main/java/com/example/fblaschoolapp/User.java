package com.example.fblaschoolapp;


//This is the contructor class that the user data will be formatted in the firebase database
//Creating the user object will help make pulling data easier on the frontend
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String studentID;
    private String imageURL;
    private String id;

    public User ()
    {

    }

    public User(String firstName, String lastName, String email, String password, String studentID, String imageURL, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.imageURL = imageURL;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
