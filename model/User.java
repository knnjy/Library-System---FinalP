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
        super(name); // still inherit name from Person
        this.userId = idCounter++;
        this.contactNumber = contactNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /* Borrow a book and record transaction */
    public boolean borrowBook(Book book, TransactionLog log) {
        if (borrowedBooks.size() >= 3) {
            System.out.println("Borrowing limit reached. You can only borrow up to 3 books.");
            return false; // ✅ must return a boolean
        }
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();

            Transaction t = new Transaction(this);
            t.addBook(book);
            t.setBorrowDetails(new Date(), new Date(System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000));
            log.addTransaction(t);

            System.out.println(name + " borrowed: " + book.getTitle());
            return true;
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available.");
            return false;
        }
    }

    public boolean returnBook(Book book, TransactionLog log, Date returnDate) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();

            // Find the borrow transaction for this book
            Transaction borrowTx = null;
            for (Transaction t : log.getAllTransactions()) { // ✅ use getter, not showAllTransactions
                if (t.getClient().equals(this)
                        && t.getBooks().contains(book)
                        && "BORROWED".equals(t.getStatus())) {
                    borrowTx = t;
                    break;
                }
            }

            Transaction returnTx = new Transaction(this);
            returnTx.addBook(book);
            returnTx.setReturnDetails(returnDate);

            // ✅ carry over the due date from borrow transaction
            if (borrowTx != null) {
                returnTx.setBorrowDetails(borrowTx.getDateBorrowed(), borrowTx.getDateDue());
            }

            log.addTransaction(returnTx);

            System.out.println(getName() + " returned: " + book.getTitle());

            long fine = returnTx.calculateFine();
            if (fine > 0) {
                System.out.println("⚠️ Overdue Fine: ₱" + fine);
            } else {
                System.out.println("No fines.");
            }

            return true;
        } else {
            System.out.println(getName() + " does not have " + book.getTitle());
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
