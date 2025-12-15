package model;

import java.util.ArrayList;
import java.util.List;

public class Librarian extends Person {
    private static int idCounter = 1000; // separate ID range for librarians
    private int librarianId;
    private List<User> registeredUsers; 

    public Librarian(String name) {
        super(name); // call Person constructor
        this.librarianId = idCounter++;
        this.registeredUsers = new ArrayList<>();
    }

    public int getLibrarianId() {
        return librarianId;
    }

    // ✅ Manage registered users
    public void registerUser(User user) {
        registeredUsers.add(user);
        System.out.println("User registered by librarian: " + user.getName());
    }

    public void removeUser(User user) {
        if (registeredUsers.remove(user)) {
            System.out.println("User removed: " + user.getName());
        } else {
            System.out.println("User not found.");
        }
    }

    public void showRegisteredUsers() {
        System.out.println("Registered users under Librarian " + getName() + ":");
        if (registeredUsers.isEmpty()) {
            System.out.println("None");
        } else {
            for (User u : registeredUsers) {
                System.out.println("- " + u.getName() + " (ID: " + u.getUserId() + ")");
            }
        }
    }

    // ✅ Book management
    public void addBook(LibraryCollections library, Book book) {
        library.addBook(book);
        System.out.println("Book added permanently: " + book.getTitle());
    }

    public void removeBook(LibraryCollections library, int bookId) {
        if (library.removeBook(bookId)) {
            System.out.println("Book removed permanently: ID " + bookId);
        } else {
            System.out.println("Book not found: ID " + bookId);
        }
    }

    // ✅ Transaction viewing
    public void viewTransactions(TransactionLog log) {
        log.showAllTransactions();
    }
}
