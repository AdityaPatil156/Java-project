package restaurant.model;

public final class CardPayment implements Payment {

    private final double value;        // made final
    private final String card;         // made final

    public CardPayment(PaymentMethod m, double v, String c) {
        this.value = v;
        this.card = c;
    }

    @Override
    public double amount() {
        return value;
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.CARD;
    }

    @Override
    public void process() {
        System.out.println("Card payment: " + card);
    }
}
