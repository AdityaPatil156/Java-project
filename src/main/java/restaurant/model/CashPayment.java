package restaurant.model;

public final class CashPayment implements Payment {

    private final double value;   // made final

    public CashPayment(double v) {
        this.value = v;
    }

    @Override
    public double amount() {
        return value;
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.CASH;
    }

    @Override
    public void process() {
        System.out.println("Cash payment");
    }
}
