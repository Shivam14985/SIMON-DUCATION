package com.example.shivamBhardwaj.simoneducation6392.Models;

public class PracticeQuestionModel {
    String Description,DescriptionSubject,descriptionId,language,addedBy;
    private long addedAt;

    public PracticeQuestionModel() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescriptionSubject() {
        return DescriptionSubject;
    }

    public void setDescriptionSubject(String descriptionSubject) {
        DescriptionSubject = descriptionSubject;
    }

    public long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(long addedAt) {
        this.addedAt = addedAt;
    }

    public String getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(String descriptionId) {
        this.descriptionId = descriptionId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
