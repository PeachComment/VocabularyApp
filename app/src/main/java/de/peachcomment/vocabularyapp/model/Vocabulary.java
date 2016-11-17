package de.peachcomment.vocabularyapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by PeachComment on 29.07.2016.
 */
public class Vocabulary implements Serializable {

    private Long id;
    private String word;
    private List<Translation> translations;
    private Date timestampInsert;
    private Date timestampLastUpdate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public Date getTimestampInsert() {
        return this.timestampInsert;
    }

    public void setTimestampInsert(Date timestampInsert) {
        this.timestampInsert = timestampInsert;
    }

    public Date getTimestampLastUpdate() {
        return this.timestampLastUpdate;
    }

    public void setTimestampLastUpdate(Date timestampLastUpdate) {
        this.timestampLastUpdate = timestampLastUpdate;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public boolean isChanged() {
        if (isNew()) {
            return true;
        }
        return false;
    }

}
