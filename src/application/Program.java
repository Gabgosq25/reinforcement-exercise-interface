package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Locale;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.DomainException;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {


            System.out.println("enter the contract details");
            System.out.print("number: ");
            int number = sc.nextInt();
            System.out.print("date (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(sc.next(), fmt);
            System.out.print("contract value: ");
            double totalValue = sc.nextDouble();
            System.out.print("enter the number of installments: ");
            int months = sc.nextInt();
            sc.nextLine();

            Contract contract = new Contract(number, date, totalValue);


            ContractService contractService = new ContractService(new PaypalService());
            contractService.processContract(contract, months);

            System.out.println("installments:");

            for (Installment installment : contract.getInstallments()) {
                System.out.println(installment);
            }


        } catch (DomainException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } finally {
            sc.close();
        }

    }
}
