package com.example.alexw.departments;

/**
 * Created by alexwalker on 27.08.17.
 */

public class User {
    String name;
    String email;
    String id;
    String phoneNumber;
    String userPicId;
    String faculty;
    String year;
    int groupNumber;

    public User() {
    }

    public User(String name, String email, String id, String phoneNumber, String userPicId, String faculty, String year, int groupNumber) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userPicId = userPicId;
        this.faculty = faculty;
        this.year = year;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPicId() {
        return userPicId;
    }

    public void setUserPicId(String userPicId) {
        this.userPicId = userPicId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
