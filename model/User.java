package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class User extends Person {
    private static int idCounter = 1;
    private int userId;
    private String contactNumber;
    private List<Book> borrowedBooks;

    public User(String name, String contactNumber) {
        super(name);                // still inherit name from Person
        this.userId = idCounter++;
        this.contactNumber = contactNumber;
        this.borrowedBooks = new ArrayList<>();
    }


    public int getUserId() { return userId; }
    public String getName() { return name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }


    /* Borrow a book and record transaction */
    public boolean borrowBook(Book book, TransactionLog log) {
        if (borrowedBooks.size() >= 3) {
            System.out.println("Borrowing limit reached. You can only borrow up to 3 books.");
            return false;   // ✅ must return a boolean
        }
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();

            Transaction t = new Transaction(this);
            t.addBook(book);
            t.setBorrowDetails(new Date(), new Date(System.currentTimeMillis() + 7L*24*60*60*1000));
            log.addTransaction(t);

            System.out.println(name + " borrowed: " + book.getTitle());
            return true;
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available.");
            return false;
        }
    }

    /* Return a book and record transaction */
    public boolean returnBook(Book book, TransactionLog log) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();

            Transaction t = new Transaction(this);
            t.addBook(book);
            t.setReturnDetails(new Date());
            log.addTransaction(t);

            System.out.println(name + " returned: " + book.getTitle());
            return true;
        } else {
            System.out.println(name + " does not have " + book.getTitle());
            return false;
        }
    }

    /* Reserve a book and record transaction */
    public boolean reserveBook(Book book, TransactionLog log) {
        if (!book.isAvailable()) {
            book.addReservation(this);

            Transaction t = new Transaction(this);
            t.addBook(book);
            t.setReserveDetails(new Date());
            log.addTransaction(t);

            System.out.println(name + " reserved: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book is available, you can borrow it directly.");
            return false;
        }
    }

    public void showBorrowedBooks() {
        System.out.println(name + "'s borrowed books:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("None");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println("- " + b.getTitle());
            }
        }
    }
}
