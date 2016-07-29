package de.peachcomment.vocabularyapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by PeachComment on 29.07.2016.
 */
public class Vocabulary {

    private int id;
    private String word;
    private List<Translation> translations;
    private Date createdOn;
    private Date lastEditedOn;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Translation> getTranslations() {
        return this.translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastEditedOn() {
        return this.lastEditedOn;
    }

    public void setLastEditedOn(Date lastEditedOn) {
        this.lastEditedOn = lastEditedOn;
    }

}
