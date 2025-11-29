package restaurant.model;

public final class OnlinePayment implements Payment {
    private double value;
    private String provider;

    public OnlinePayment(double v, String p) {
        value = v; provider = p;
    }

    @Override
    public double amount() { return value; }
    @Override
    public PaymentMethod method() { return PaymentMethod.ONLINE; }
    @Override
    public void process() { System.out.println("Online via " + provider); }
}
