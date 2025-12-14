package model;

public abstract class Person {
    private static int idCounter = 1;
    private int personId;
    protected String name;

    public Person(String name) {
        this.personId = idCounter++;
        this.name = name;
    }

    public int getPersonId() { return personId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
