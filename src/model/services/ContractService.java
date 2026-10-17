package model.services;


import java.time.LocalDate;

import model.exceptions.DomainException;
import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

    private OlinePaymentService onlinePaymentService;


    public ContractService(OlinePaymentService paymentService) {
        this.onlinePaymentService = paymentService;
    }


    public void processContract(Contract contract, Integer months) {

        if (months == null || months <= 0) {
            throw new DomainException("Number of months must be greater than zero.");
        }


        double baseInstallment = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);
            Double installmentWithInterest = baseInstallment + onlinePaymentService.interest(baseInstallment, i);
            Double installmentWithFee = installmentWithInterest + onlinePaymentService.paymentFee(installmentWithInterest);
            Installment installment = new Installment(dueDate, installmentWithFee);
            contract.getInstallments().add(installment);

        }
    }
}
