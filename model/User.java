package model;
import java.util.ArrayList;
import java.util.List;

class Person{
    String name;
    
}

public class User extends Person {
    private static int idCounter = 1; // static counter shared across all users
    private int userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.userId = idCounter++; // auto-increment ID
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    /* Borrow a book from the library */
    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook(); // mark as unavailable
            
            System.out.println(name + " borrowed: " + book.getTitle());

            return true;
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available.");
            return false;
        }
    }

    /* Return a book to the library */
    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook(); // mark as available
            System.out.println(name + " returned: " + book.getTitle());
            return true;
        } else {
            System.out.println(name + " does not have " + book.getTitle());
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