package ru.innopolis.uni.course3.model;

import java.io.Serializable;

/**
 *
 */
public class Book implements Serializable {

    private Integer id;
    private String author;
    private String title;
    private Integer year;
    private String text;

    private Integer version;

    public Book() {
    }

    public Book(Book b) {
        this(b.getId(), b.getAuthor(), b.getTitle(), b.getYear(), b.getText(), b.getVersion());
    }

    public Book(String author, String title, Integer year, String text, Integer version) {
        this(null, author, title, year, text, version);
    }

    public Book(Integer id, String author, String title, Integer year, String text, Integer version) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.text = text;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return title + " - " + author;
    }

}
