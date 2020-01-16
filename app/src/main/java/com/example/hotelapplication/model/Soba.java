package com.example.hotelapplication.model;

import java.io.Serializable;

public class Soba implements Serializable {

    private int imageID;
    private int imageUslugeID;
    private String naziv;
    private String opis;
    private  String details;

    public Soba(int imageID, int imageUslugeID,String title, String description,String Details) {
        this.imageID = imageID;
        this.imageUslugeID=imageUslugeID;
        this.naziv = title;
        this.opis = description;
        this.details=Details;

    }
    public int getImageID() {
        return imageID;
    }
    public int getImageUslugeIDID() {
        return imageUslugeID;
    }

    public String getTitle() {
        return naziv;
    }
    public String getDetails() {
        return details;
    }

    public String getDescription() {
        return opis;
    }
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public void setImageUslugeIDID(int imageUslugeIDID) {
        this.imageUslugeID = imageUslugeIDID;
    }

    public void setTitle(String title) {
        this.naziv = title;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public void setDescription(String description) {
        this.opis = description;
    }

}
