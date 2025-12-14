import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Book;
import model.User;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        LibraryCollections library = new LibraryCollections();
        LibraryMember members = new LibraryMember();

        String continueChoice;


        do {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter your User ID (or type 'new' to register): ");
            int userId = sc.nextInt();

            // String newUserName = "John"; // simulate input

            User isMember = members.getUserByName(name);
            User iMember = members.getUserByID(userId);

            if (isMember != null) {
                System.out.println("Registered");
            } else {
                System.out.println(isMember);
            }
            if (iMember != null) {
                System.out.println("Registered");
            } else {
                System.out.println("KK");
            }


            // System.out.println("\n--- Library Features ---");
            // System.out.println("1. Borrow");
            // System.out.println("2. Return");
            // System.out.println("3. Track");
            // System.out.println("4. Reserve");
            // System.out.println("5. Logout");
            // System.out.println("6. Exit Program");
            // System.out.print(">> Enter your choice: ");
            // int option = sc.nextInt();


            System.out.print("\nDo you want to continue? (Y/N): ");
            sc.nextLine();
            continueChoice = sc.nextLine();

        } while (continueChoice.equalsIgnoreCase("Y"));
    }
}
