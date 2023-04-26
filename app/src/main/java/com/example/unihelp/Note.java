package com.example.unihelp;

public class Note {
    private String examTitle;
    private String subject;
    private int mark;

    public Note(String examTitle, String subject, int mark) {
        this.examTitle = examTitle;
        this.subject = subject;
        this.mark = mark;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
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
}
