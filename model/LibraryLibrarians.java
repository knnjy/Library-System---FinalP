package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryLibrarians {
    private List<Librarian> librarians;

    public LibraryLibrarians() {
        librarians = new ArrayList<>();
        // Pre-register librarians
        librarians.add(new Librarian("Maria"));
        librarians.add(new Librarian("Juan"));
        librarians.add(new Librarian("Ana"));
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public boolean isRegistered(String name) {
        for (Librarian l : librarians) {
            if (l.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Librarian getLibrarianByName(String name) {
        for (Librarian l : librarians) {
            if (l.getName().equalsIgnoreCase(name)) {
                return l;
            }
        }
        return null;
    }
}
