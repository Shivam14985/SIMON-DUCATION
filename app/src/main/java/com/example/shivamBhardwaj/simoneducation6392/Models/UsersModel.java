package com.example.shivamBhardwaj.simoneducation6392.Models;

public class UsersModel {
    String Email, Dob, Name,Number;
    String Profile;
    private boolean Partner;
    private int NotificationCount,LearboardRank,Contribution;

    public UsersModel() {
    }

    public UsersModel(String email, String dob, String name, String number, boolean partner) {
        Email = email;
        Dob = dob;
        Name = name;
        Number = number;
        Partner = partner;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public boolean isPartner() {
        return Partner;
    }

    public void setPartner(boolean partner) {
        Partner = partner;
    }

    public int getNotificationCount() {
        return NotificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        NotificationCount = notificationCount;
    }

    public int getLearboardRank() {
        return LearboardRank;
    }

    public void setLearboardRank(int learboardRank) {
        LearboardRank = learboardRank;
    }

    public int getContribution() {
        return Contribution;
    }

    public void setContribution(int contribution) {
        Contribution = contribution;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }
}
