package restaurant.model;

public class Staff extends Person {

    private final Role role;

    public Staff(String f, String l, Role r) {
        super(f, l);
        this.role = r;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String getFullName() {
        return "[Staff] " + super.getFullName();
    }
}
