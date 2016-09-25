package de.peachcomment.vocabularyapp.model;

import java.util.Date;

/**
 * Created by PeachComment on 29.07.2016.
 */
public class Translation {

    private Integer id;
    private Vocabulary vocabulary;
    private int number;
    private Date timestampInsert;
    private Date timestampLastUpdate;

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

    public Date getTimestampInsert() {
        return timestampInsert;
    }

    public void setTimestampInsert(Date timestampInsert) {
        this.timestampInsert = timestampInsert;
    }

    public Date getTimestampLastUpdate() {
        return timestampLastUpdate;
    }

    public void setTimestampLastUpdate(Date timestampLastUpdate) {
        this.timestampLastUpdate = timestampLastUpdate;
    }

}
