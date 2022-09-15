package com.techelevator.view;

import com.techelevator.handler.Logger;
import com.techelevator.model.VendingMachine;
import com.techelevator.model.VendingMachineItem;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingCli {

    private Menu menu;
    private PrintStream out;
    private Scanner inputScanner;
    private Logger logger;
    private VendingMachine vendingMachine;

    public VendingCli(Menu menu, PrintStream out, InputStream in, Logger logger, VendingMachine vendingMachine) {
        this.menu = menu;
        this.out = out;
        this.inputScanner = new Scanner(in);
        this.logger = logger;
        this.vendingMachine = vendingMachine;
    }

    public void run() {
        boolean isLeaving = false;
        while (!isLeaving) {
            String choice = (String) menu.getChoiceFromOptions(new String[]{
                    "Display vending machine items",
                    "Purchase",
                    "Exit",
                    "(hide)Sales Report"}, null);

            switch (choice) {
                case "Display vending machine items":
                    displayItems();
                    break;
                case "Purchase":
                    purchaseItems();
                    break;
                case "Exit":
                    isLeaving = true;
                    break;
                case "Sales Report":
                    generateReport();
                    break;
            }
        }
    }

    private void displayItems() {
        this.out.println();
        this.out.println();
        for (VendingMachineItem item : vendingMachine.getItems()) {
            out.println(item.displayItemPrettyAnsi());
        }
//        displayItemBoxAnsi();

    }

    public void displayItemBoxAnsi() {
        String[] outputFormat = {"|--------{0}---------|   ",
                "| {2}|   ",
                "|-------------------|   ",
                "|${1}   {3}|   ",
                "|-------------------|   "};

        List<String> lines = new ArrayList<>();
        int numberOfColumns = 4;
        VendingMachineItem[] items = vendingMachine.getItems().toArray(new VendingMachineItem[0]);
        int numberOfRows = items.length/numberOfColumns;

        for (int currentRow = 0; currentRow<numberOfRows; currentRow++){
            //foreach line of output

            for (int outputFormatIndex = 0; outputFormatIndex<outputFormat.length; outputFormatIndex++){
                int startNumber = currentRow*numberOfColumns;
                int finalNumber = (currentRow*numberOfColumns) + numberOfColumns - 1;
                for (int itemPointer = startNumber; itemPointer<=finalNumber; itemPointer++){
                    lines.add(formatInventoryItem(outputFormat[outputFormatIndex],items[itemPointer]));
                }
                lines.add(System.lineSeparator());

            }

            lines.add(System.lineSeparator());
        }
        for (String line: lines){
            out.print(line);
        }
        out.flush();
    }

    private String formatInventoryItem(String formatString,VendingMachineItem item) {

        String name = item.getName() + "                    ";
        String inventoryFormat = "Remaining:{0}";
        String numberLeft = item.getStock()>0 ?
                MessageFormat.format(inventoryFormat,AnsiColors.yellowValue(String.valueOf(item.getStock()),false)) :
                AnsiColors.redValue("SOLD OUT",false);
        //Remaining:
        return AnsiColors.blackBgValue(MessageFormat.format(formatString,
                AnsiColors.redValue(item.getLocation(),false),
                AnsiColors.greenValue(MessageFormat.format("{0,number,0.00}",item.getPrice()),false),
                AnsiColors.cyanValue(name.substring(0,18),false),
                numberLeft
        ),true);
    }


    private void purchaseItems() {
        boolean isLeaving = false;
        while (!isLeaving) {
            String choice = (String) menu.getChoiceFromOptions(new String[]{
                    "Feed Money",
                    "Select Product",
                    "Finish Transaction"}, "You currently have: $" + String.format("%.2f", vendingMachine.getBalance()) + " left\n");

            switch (choice) {
                case "Feed Money":
                    vendingMachine.addMoney(feedMoney());
                    break;
                case "Select Product":
                    selectProduct();
                    break;
                case "Finish Transaction":
                    finishTransaction();
                    isLeaving = true;
                    break;
            }
        }
    }


    private double feedMoney() {
        double number;
        do {
            this.out.print("Enter amount as positive whole number: ");
            while (!inputScanner.hasNextInt()) {
                String input = inputScanner.nextLine();
                this.out.println(AnsiColors.redValue(System.lineSeparator() + "*** " + String.format("\"%s\" is not a valid number. Re-enter the amount: ", input) + System.lineSeparator(), true));
            }
            number = Double.parseDouble(inputScanner.nextLine());
        } while (number < 0);
        this.logger.writeAudit("FEED MONEY: $" + String.format("%.2f", vendingMachine.getBalance()) + " $" + String.format("%.2f", vendingMachine.getBalance() + number));
        return number;
    }


    private void selectProduct() {
        displayItems();
        String code = inputScanner.nextLine();
        if (!vendingMachine.getItemsMap().containsKey(code)) {
            this.out.println(AnsiColors.redValue(System.lineSeparator() + "*** " + code + " The code does not exist. Please try again." + System.lineSeparator(), true));
        } else {
            VendingMachineItem desiredItem = vendingMachine.getItemsMap().get(code);
            if (!desiredItem.buyItem()) {
                this.out.println(AnsiColors.redValue(System.lineSeparator() + "*** Item is out of stock. Please choose another product." + System.lineSeparator(), true));
            } else {
                if (desiredItem.getPrice() > vendingMachine.getBalance()) {
                    this.out.println(AnsiColors.redValue(System.lineSeparator() + "*** Not enough funds for this item. Please feed more money." + System.lineSeparator(), true));
                } else {
                    vendingMachine.subtractMoney(desiredItem.getPrice());
                    this.out.printf("\nYou bought this: %s $%.2f \n", desiredItem.getName(), desiredItem.getPrice());
                    this.out.println(desiredItem.getSound());
                    this.logger.writeAudit(desiredItem.getName() + " " + desiredItem.getLocation() + " " + String.format("%.2f", vendingMachine.getBalance() + desiredItem.getPrice()) + " $" + String.format("%.2f", vendingMachine.getBalance()));
                }
            }
        }
    }


    private void finishTransaction() {
        double money = vendingMachine.getBalance();
        int quarter, dime, nickel;
        this.logger.writeAudit("GIVE CHANGE: $" + String.format("%.2f", vendingMachine.getBalance()) + " $0.00");

        quarter = (int) Math.floor(money/0.25);
        money -= quarter * 0.25;
        dime = (int) Math.floor(money/0.1);
        money -= dime * 0.1;
        nickel = (int) Math.floor(money/0.05);
        vendingMachine.setBalance(0);
        this.out.println(AnsiColors.blueValue(System.lineSeparator() + "*** " + String.format("Thank you for your purchase. Here is your change: %d quarters, %d dimes, %d nickels", quarter, dime, nickel) + System.lineSeparator(), true));
    }


    private void generateReport() {
        String destinationFile = "SalesReport.txt";
        File outputFile = new File(destinationFile);
        try {
            if (!outputFile.createNewFile()) {
                System.out.println("\nSales Report generated. File overwritten");
            }
        } catch (IOException e) {
            System.err.println("Could not create output");
            System.exit(2);
        }

        if (outputFile.exists()) {
            try (
                    PrintWriter output = new PrintWriter(outputFile)
            ) {
                double totalSales = 0;
                for (VendingMachineItem item : vendingMachine.getItems()) {
                    output.println(item.getName() + "|" + (5 - item.getStock()));
                    totalSales += (5 - item.getStock()) * item.getPrice();
                }
                output.printf("\nTotal Sales: $%.2f", totalSales);

            } catch (FileNotFoundException e) {
                System.out.println("File is not there");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
