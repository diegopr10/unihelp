package com.example.unihelp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "Nombre")
    private String title;

    @ColumnInfo(name = "Asignatura")
    private String subject;
    @ColumnInfo(name = "Nota")
    private int mark;

    @ColumnInfo(name = "Porcentaje")
    private int percentage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Note(String title, String subject, int mark, int percentage) {
        this.title = title;
        this.subject = subject;
        this.mark = mark;
        this.percentage = percentage;
    }
}
