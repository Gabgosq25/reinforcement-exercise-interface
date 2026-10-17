package model.services;

public class PaypalService implements OlinePaymentService {

    private static final Double paymentFee = 0.02;
    private static final Double interest = 0.01;

    @Override
    public Double paymentFee(Double amount) {
        return amount * paymentFee;
    }

    @Override
    public Double interest(Double amount, Integer months) {
        return amount * interest * months;
    }

}
