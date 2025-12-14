package model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int publishYear;
    private boolean isAvailable;
    private int pageNumber;

    public Book(int bookId, String title, String author, int publishYear, int pageNumber) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.isAvailable = true;
        this.pageNumber = pageNumber;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }
    public void borrowBook() { isAvailable = false; }
    public void returnBook() { isAvailable = true; }
}