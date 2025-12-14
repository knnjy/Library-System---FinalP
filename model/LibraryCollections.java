package model;
import java.util.ArrayList;
import java.util.List;

/* LIBRARY COLLECTION  */
public class LibraryCollections {
    private List<Book> books;

    public LibraryCollections() {
        books = new ArrayList<>();
        books.add(new Book(1, "Clean Code", "Robert C. Martin", 2008, 201));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", 2018, 278));
        books.add(new Book(3, "Design Patterns", "Erich Gamma", 1994, 308));
        books.add(new Book(4, "Java Concurrency in Practice", "Brian Goetz", 2006, 483));
        books.add(new Book(5, "Head First Java", "Kathy Sierra", 2005, 109));
        books.add(new Book(6, "Refactoring", "Martin Fowler", 1999, 302));
        books.add(new Book(7, "The Pragmatic Programmer", "Andrew Hunt", 1999, 280));
        books.add(new Book(8, "Algorithms", "Robert Sedgewick", 2011, 247));
        books.add(new Book(9, "Introduction to Algorithms", "Thomas H. Cormen", 2009, 230));
        books.add(new Book(10, "Java: The Complete Reference", "Herbert Schildt", 2021, 407));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(int bookId) {
        return books.removeIf(b -> b.getBookId() == bookId);
    }

    public void showBooks() {
        System.out.println("=== ALL BOOKS ===");
        for (Book b : books) {
            System.out.println(
                    b.getBookId() + " | " +
                            b.getTitle() + " | Available: " +
                            b.isAvailable());
        }
    }

    public Book getBookById(int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId)
                return b;
        }
        return null;
    }

    public int totalBooks() {
        return books.size();
    }
}


