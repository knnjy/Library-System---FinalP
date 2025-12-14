package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private static int transactionCounter = 1; // auto-increment transaction number
    
    private int transactionNo;
    private List<Book> books;       // books involved in transaction
    private User client;            // who performed the action
    private Date dateBorrowed;
    private Date dateDue;
    private Date dateReturned;
    private String status;          // "BORROWED", "RETURNED", "RESERVED"

    public Transaction(User client) {
        this.transactionNo = transactionCounter++;
        this.client = client;
        this.books = new ArrayList<>();
    }

    // Add a book to this transaction
    public void addBook(Book book) {
        books.add(book);
    }

    // Set borrow details
    public void setBorrowDetails(Date borrowed, Date due) {
        this.dateBorrowed = borrowed;
        this.dateDue = due;
        this.status = "BORROWED";
    }

    // Set return details
    public void setReturnDetails(Date returned) {
        this.dateReturned = returned;
        this.status = "RETURNED";
    }

    // Set reserve details
    public void setReserveDetails(Date reserved) {
        this.dateBorrowed = reserved; // reservation date
        this.status = "RESERVED";
    }

    public int getTransactionNo() {
        return transactionNo;
    }

    public String getStatus() {
        return status;
    }

    public User getClient() {
        return client;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void showTransaction() {
        System.out.println("Transaction #" + transactionNo);
        System.out.println("Client: " + client.getName());
        System.out.println("Books: ");
        for (Book b : books) {
            System.out.println(" - " + b.getTitle());
        }
        System.out.println("Status: " + status);
        System.out.println("Borrowed: " + dateBorrowed);
        System.out.println("Due: " + dateDue);
        System.out.println("Returned: " + dateReturned);
        System.out.println("---------------------------");
    }
}
