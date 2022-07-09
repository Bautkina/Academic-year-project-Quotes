package com.example.newappli;

public class Quote {
    private int id;
    private String user;
    private String quote;
    private String date;
    private String subject;
    private String teacher;

    public Quote(int id, String teacher, String subject, String quote) {
        this.id = id;
        this.quote = quote;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.user = user;
    }

    public Quote(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Quote(int id, String user, String quote, String subject, String teacher, String date) {
        this.id = id;
        this.quote = quote;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.user = user;
    }

    public Quote(String subject, String teacher, String quote) {
        this.id = id;
        this.quote = quote;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return quote;
    }

    public void setComment(String quote) {
        this.quote = quote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
