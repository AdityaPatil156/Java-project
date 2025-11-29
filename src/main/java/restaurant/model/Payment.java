package restaurant.model;

public sealed interface Payment permits CashPayment, CardPayment, OnlinePayment {
    double amount();
    PaymentMethod method();
    void process();
}
