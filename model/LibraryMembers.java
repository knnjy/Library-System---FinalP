package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMembers {
    private List<User> members;

    public LibraryMembers() {
        members = new ArrayList<>();
        members.add(new User("Alice", "09171234567"));
        members.add(new User("Ken", "09987654321"));

    }

    public User getUserByName(String name) {
        for (User u : members) {
            String uName = u.getName();
            if (uName != null && uName.equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public User getUserByID(int id) {
        for (User u : members) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public void addUser(String name, String contactNumber) {
        if (getUserByName(name) == null) {
            User newUser = new User(name, contactNumber);
            members.add(newUser);
            System.out.println("User added: " + newUser.getUserId() + " | " + name + " | " + contactNumber);
        } else {
            System.out.println("User already exists: " + name);
        }
    }

    public void showAllUsers() {
        System.out.println("=== ALL USERS ===");
        for (User u : members) {
            System.out.println(u.getUserId() + " | " + u.getName());
        }
    }
}
