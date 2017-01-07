package ru.innopolis.uni.course3.model;

/**
 *
 */
public class Book {

    private Integer id;
    private String author;
    private String title;
    private Integer year;
    private String text;

    public Book() {
    }

    public Book(Book b) {
        this(b.getId(), b.getAuthor(), b.getTitle(), b.getYear(), b.getText());
    }

    public Book(String author, String title, Integer year, String text) {
        this(null, author, title, year, text);
    }

    public Book(Integer id, String author, String title, Integer year, String text) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.text = text;
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

    @Override
    public String toString() {
        return title + " - " + author;
    }

}
