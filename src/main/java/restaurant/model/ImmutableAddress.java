package restaurant.model;

public final class ImmutableAddress {

    private final String street;
    private final String city;
    private final String country;

    public ImmutableAddress(String s, String c, String co) {
        this.street = s;
        this.city = c;
        this.country = co;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
