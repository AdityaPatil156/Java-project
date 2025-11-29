package restaurant.model;

public class Customer extends Person {

    private final ImmutableAddress address;
    private int loyaltyPoints;

    public Customer(String first, String last) {
        this(first, last, null, 0);
    }

    public Customer(String first, String last, ImmutableAddress address, int points) {
        super(first, last);
        this.address = address;
        this.loyaltyPoints = points;
    }

    public ImmutableAddress getAddress() {
        return address;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
