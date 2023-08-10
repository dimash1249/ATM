package com.company;

import com.sun.net.httpserver.Authenticator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<ATM> dataATM(){
        ArrayList<ATM> atm = new ArrayList<ATM>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("dataATM.txt"));
            String s = "";
            while( (s = reader.readLine())!=null ){
                String[] tokens = s.split(" ");
                ATM atm1 = new ATM();
                atm1.setATM(tokens[0]);
                atm1.setSurname(tokens[1]);
                atm1.setName(tokens[2]);
                atm1.setPinCode(tokens[3]);
                atm1.setCardNumber(tokens[4]);
                atm1.setBalance(Double.parseDouble(tokens[5]));
                atm.add(atm1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return atm;
    }

    public static void saveDataATM(ArrayList<ATM> atms){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataATM.txt"));
            String s = "";
            for(int i=0;i<atms.size();i++){
                s += atms.get(i).toString() + "\r\n";
            }
            writer.write(s);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataATM.txt"));
            writer.write("Halyk_Bank Nogaibaev Dimash 0234 1234-5646-1356-0978 20000" + "\r\n");
            writer.write("Kaspi_Bank Smith Kevin 4537 4324-0697-5457-1376 23600" + "\r\n");
            writer.write("Sber_Bank Vydsovksy Pierson 4545 4324-4545-6778-0987 31090" + "\r\n");
            writer.close();
        }catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<ATM> atms = new ArrayList<ATM>();
        Scanner input = new Scanner(System.in);
        int ATMBalance = 30000;
        atms = dataATM();
        while(true){
            System.out.println("PRESS [0] to close ATM");
            System.out.println("PRESS [1] to open ATM");
            int key = input.nextInt();
            if(key == 0){
                System.exit(0);
            }
            else if(key == 1){
                boolean process = true;
                int operation = 1;
                System.out.println("Please insert the card\nLoading...");
                System.out.println("Please enter your pin code");
                String pin = input.next();
                int q = 0;
                int yet = 0;
                int index = 0;
                for(int i=0;i<atms.size();i++){
                    if(atms.get(i).getPinCode().equals(pin)){
                        q = 1;
                        index = i;
                    }
                }
                while(process){
                    if(q == 1){
                        yet = 1;
                        System.out.println("PRESS [1] to add money");
                        System.out.println("PRESS [2] to take money");
                        System.out.println("PRESS [3] to show balance");
                        int key1 = input.nextInt();
                        if(key1 == 1){
                            System.out.println("Balance replenishment");
                            System.out.print("Enter the amount of money: ");
                            double amountAdd = input.nextDouble();
                            atms.get(index).setBalance(atms.get(index).getBalance() + amountAdd);
                            saveDataATM(atms);
                            System.out.println("Print the receipt?\nPRESS [0] - no    PRESS[1] - yes");
                            int receipt = input.nextInt();
                            if(receipt == 0){
                            }
                            else if(receipt == 1){
                                atms.get(index).receiptDataAdd(amountAdd);
                            }
                        }
                        else if(key1 == 2){
                            System.out.println("Issuing money");
                            System.out.print("Enter the amount of money: ");
                            double amountTake = input.nextDouble();
                            if(ATMBalance >= amountTake){
                                if(amountTake > atms.get(index).getBalance()){
                                    System.out.println("You don't have enough funds on your card!");
                                }
                                else{
                                    System.out.println("Please wait the money is being counted");
                                    atms.get(index).setBalance(atms.get(index).getBalance() - amountTake);
                                    System.out.println("Print the receipt?\nPRESS [0] - no    PRESS[1] - yes");
                                    int receipt = input.nextInt();
                                    if(receipt == 0){
                                    }
                                    else if(receipt == 1){
                                        atms.get(index).receiptDataTake(amountTake);
                                    }
                                    System.out.println("Please don't forget to take the money!");
                                    saveDataATM(atms);
                                }
                            }
                            else{
                                System.out.println("Sorry, the ATM is temporarily down");
                            }

                        }
                        else if(key1 == 3){
                            System.out.println("Surname:");
                            System.out.println(atms.get(index).getSurname() + "\n\nName:");
                            System.out.println(atms.get(index).getName() + "\n\nAvailable funds\nAmount: " + atms.get(index).getBalance() + "\n");

                        }
                        else {
                            process = false;
                            operation = 0;
                        }
                    }
                    else {
                        System.out.println("Invalid pin code!");
                        process = false;
                    }
                    if(yet == 1 && operation == 1){
                        System.out.println("Do you want to perform more operations?\nPRESS [0] - no\nPRESS [1] - yes");
                        int moreKey = input.nextInt();
                        if(moreKey == 0){
                            System.out.println("Please don't forget to bring your card!");
                            process = false;
                        }
                        else if(moreKey == 1){
                            process = true;
                        }
                    }
                }
            }
            else{
                break;
            }
        }
    }
}
