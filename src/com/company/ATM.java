package com.company;

public class ATM {
    private String ATM;
    private String surname;
    private String name;
    private String pinCode;
    private String cardNumber;
    private double balance;

    public ATM(){

    }

    public void setATM(String ATM) {
        this.ATM = ATM;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPinCode() {
        return pinCode;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return ATM + " " + surname + " " + name + " " + pinCode + " " + cardNumber + " " + balance;
    }
    public double commissionData(double amount){
        return amount * 1 / 100;
    }
    public void receiptDataTake(double bal){
        System.out.println("===========================");
        System.out.println("          RECEIPT          ");
        System.out.println("ATM: " + ATM + "\nTime: 15:10:36    №00463606\n\nNr CHECK 657845/57579679597\nNr CARD " + cardNumber +"\n\nCommission: " + commissionData(bal) + " KZT\n\nCash withdrawal\nAmount " + bal + " KZT\n\nAvailable funds:\n" + balance + " KZT");
        System.out.println("===========================");
    }
    public void receiptDataAdd(double bal){
        System.out.println("===========================");
        System.out.println("          RECEIPT          ");
        System.out.println("ATM: " + ATM + "\nTime: 15:10:36    №00463606\n\nNr CHECK 657845/57579679597\nNr CARD " + cardNumber +"\n\nCommission: " + commissionData(bal) + " KZT\n\nAdding cash\nAmount " + bal + " KZT\n\nAvailable funds:\n" + balance + " KZT");
        System.out.println("===========================");
    }
}
