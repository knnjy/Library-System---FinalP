package model;

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int publishYear;
    private boolean isAvailable;
    private int pageNumber;
    private Queue<User> reservationQueue;

    public Book(int bookId, String title, String author, int publishYear, int pageNumber) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.isAvailable = true;
        this.pageNumber = pageNumber;
        this.reservationQueue = new LinkedList<>();
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getBookPages() {
        return pageNumber;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public void addReservation(User user) {
        reservationQueue.add(user);
        System.out.println(user.getName() + " reserved: " + title);
    }

    public User getNextReservation() {
        return reservationQueue.poll(); // returns and removes next user
    }

    public boolean hasReservations() {
        return !reservationQueue.isEmpty();
    }
}