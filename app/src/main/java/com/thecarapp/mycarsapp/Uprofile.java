package com.thecarapp.mycarsapp;

public class Uprofile {

    public String userEmail;
    public String FirstName;
    public String LastName;

    public Uprofile(String userEmail, String firstName, String lastName) {
        this.userEmail = userEmail;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
