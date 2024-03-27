package model;

import java.time.LocalDateTime;

public class Entry {
    private  int id;
    private String title;
    private String body;
    private LocalDateTime dateCreated;
    private String author;

    public Entry(int id,String title,String body,String author) {
        this.id = id;
        this.body = body;
        this.title =title;
        this.author = author;
    }
    public Entry(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
