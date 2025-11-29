package restaurant.model;

public abstract class Person implements Identifiable {

    private static long nextId = 1;
    private final long id;
    private String firstName;
    private String lastName;

    protected Person(String firstName, String lastName) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() { return id; }
    public String getFullName() { return firstName + " " + lastName; }
}
