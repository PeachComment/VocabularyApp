package de.peachcomment.vocabularyapp.model;

import java.util.Date;

/**
 * Created by PeachComment on 29.07.2016.
 */
public class Translation {

    private int id;
    private Vocabulary vocabulary;
    private int number;
    private Date createdOn;
    private Date lastEditedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastEditedOn() {
        return lastEditedOn;
    }

    public void setLastEditedOn(Date lastEditedOn) {
        this.lastEditedOn = lastEditedOn;
    }

}
