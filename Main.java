import java.util.Date;
import java.util.Scanner;
import model.Book;
import model.Librarian;
import model.LibraryCollections;
import model.LibraryMembers; 
import model.User;
import model.Transaction;
import model.TransactionLog;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryCollections library = new LibraryCollections();
        LibraryMembers members = new LibraryMembers(); 
        TransactionLog transactionLog = new TransactionLog();

        System.out.println("\n--- Login ---");
        System.out.println("1. Member");
        System.out.println("2. Librarian");
        System.out.print(">> Enter your role: ");
        int role = Integer.parseInt(sc.nextLine());

        if (role == 1) {
            runMemberProcess(members, library, transactionLog);
        } else if (role == 2) {
            runLibrarianProcess(library, transactionLog);
        }
    }

    // Member process
    private static void runMemberProcess(LibraryMembers members, LibraryCollections library,
            TransactionLog transactionLog) {
        String continueChoice;
        do {
            User isMember = loginOrRegister(members);

            boolean logout = false;
            while (!logout) {
                System.out.println("\n--- Library Features ---");
                System.out.println("1. Borrow");
                System.out.println("2. Return");
                System.out.println("3. Track");
                System.out.println("4. Reserve");
                System.out.println("5. Show Transactions");
                System.out.println("6. Logout");
                System.out.println("7. Exit Program");
                System.out.print(">> Enter your choice: ");

                int option = -1;
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number between 1-7.");
                    continue;
                }

                switch (option) {
                    case 1:
                        borrowBook(isMember, library, transactionLog);
                        break;
                    case 2:
                        returnBook(isMember, library, transactionLog);
                        break;
                    case 3:
                        isMember.showBorrowedBooks();
                        break;
                    case 4:
                        reserveBook(isMember, library, transactionLog);
                        break;
                    case 5:
                        transactionLog.showAllTransactions();
                        break;
                    case 6:
                        logout = true;
                        break;
                    case 7:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select 1-7.");
                        break;
                }
            }
            System.out.print("\nDo you want to continue? (Y/N): ");
            continueChoice = sc.nextLine();
        } while (continueChoice.equalsIgnoreCase("Y"));
    }

    // Librarian process
    private static void runLibrarianProcess(LibraryCollections library, TransactionLog transactionLog) {
        System.out.print("Enter librarian name: ");
        String libName = sc.nextLine();
        Librarian librarian = new Librarian(libName);
        System.out.println("Welcome Librarian " + librarian.getName());

        boolean logout = false;
        while (!logout) {
            System.out.println("\n--- Librarian Features ---");
            System.out.println("1. View Transactions");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Logout");
            System.out.println("5. Exit Program");
            System.out.print(">> Enter your choice: ");

            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    librarian.viewTransactions(transactionLog);
                    break;
                case 2:
                    addBook(library, librarian);
                    break;
                case 3:
                    removeBook(library, librarian);
                    break;
                case 4:
                    logout = true;
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
            }
        }
    }

    // Helper methods
    private static User loginOrRegister(LibraryMembers members) {
        User isMember = null;
        do {
            System.out.print("Enter your User ID (or type 'new' to register): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("new")) {
                System.out.print("Enter your name: ");
                String newName = sc.nextLine();

                String contactNumber;
                do {
                    System.out.print("Enter your contact number: ");
                    contactNumber = sc.nextLine();

                    if (!contactNumber.matches("\\d{10,11}") || !contactNumber.startsWith("09")) {
                        System.out.println("Invalid contact number. Please enter a 10–11 digit number starting with 09.");
                    }
                } while (!contactNumber.matches("\\d{10,11}") || !contactNumber.startsWith("09"));

                members.addUser(newName, contactNumber);
                isMember = members.getUserByName(newName);

                members.addUser(newName, contactNumber);
                isMember = members.getUserByName(newName);
            } else {
                try {
                    int userId = Integer.parseInt(input);
                    isMember = members.getUserByID(userId);
                    if (isMember == null) {
                        System.out.println("No user found with ID: " + userId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'new'.");
                }
            }
        } while (isMember == null);
        System.out.println("Welcome, " + isMember.getName() + "!");
        return isMember;
    }

    private static void borrowBook(User user, LibraryCollections library, TransactionLog log) {
        System.out.print("Enter book ID to borrow: ");
        try {
            int borrowId = Integer.parseInt(sc.nextLine());
            Book book = library.getBookById(borrowId);
            if (book != null) {
                user.borrowBook(book, log); // ✅ pass log
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid book ID. Please enter a number.");
        }
    }

    private static void returnBook(User user, LibraryCollections library, TransactionLog log) {
        System.out.print("Enter book ID to return: ");
        try {
            int returnId = Integer.parseInt(sc.nextLine());
            Book book = library.getBookById(returnId);
            if (book != null) {
                user.returnBook(book, log); // ✅ pass log, let User handle transaction
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid book ID. Please enter a number.");
        }
    }

    private static void reserveBook(User user, LibraryCollections library, TransactionLog log) {
        System.out.print("Enter book ID to reserve: ");
        try {
            int reserveId = Integer.parseInt(sc.nextLine());
            Book book = library.getBookById(reserveId);
            if (book != null) {
                user.reserveBook(book, log); // ✅ pass log, let User handle transaction
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid book ID. Please enter a number.");
        }
    }

    private static void addBook(LibraryCollections library, Librarian librarian) {
        System.out.print("Enter book ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter year: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Enter pages: ");
        int pages = Integer.parseInt(sc.nextLine());
        Book newBook = new Book(id, title, author, year, pages);
        librarian.addBook(library, newBook);
    }

    private static void removeBook(LibraryCollections library, Librarian librarian) {
        System.out.print("Enter book ID to remove: ");
        int removeId = Integer.parseInt(sc.nextLine());
        librarian.removeBook(library, removeId);
    }
}
