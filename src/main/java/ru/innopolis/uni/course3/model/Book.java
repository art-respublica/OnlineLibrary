package ru.innopolis.uni.course3.model;

/**
 *
 */
public class Book {

    private Integer id;
    private String author;
    private String title;
    private Integer year;

    public Book() {
    }

    public Book(Book b) {
        this(b.getId(), b.getAuthor(), b.getTitle(), b.getYear());
    }

    public Book(String author, String title, Integer year) {
        this(null, author, title, year);
    }

    public Book(Integer id, String author, String title, Integer year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
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
}
