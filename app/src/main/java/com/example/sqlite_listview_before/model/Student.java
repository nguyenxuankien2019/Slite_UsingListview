package com.example.sqlite_listview_before.model;

public class Student {
    private  int mID;
    private String mName;
    private String mphoneNumber;
    private String mAddress;
    private String mEmail;

    public Student() {
    }

    public Student(int mID, String mName, String mphoneNumber, String mAddress, String mEmail) {
        this.mID = mID;
        this.mName = mName;
        this.mphoneNumber = mphoneNumber;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public Student(String mName, String mphoneNumber, String mAddress, String mEmail) {
        this.mName = mName;
        this.mphoneNumber = mphoneNumber;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMphoneNumber() {
        return mphoneNumber;
    }

    public void setMphoneNumber(String mphoneNumber) {
        this.mphoneNumber = mphoneNumber;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
