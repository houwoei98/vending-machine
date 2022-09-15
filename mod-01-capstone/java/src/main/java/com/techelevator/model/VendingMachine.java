package com.techelevator.model;

import java.util.*;

public class VendingMachine {
    private Map<String, VendingMachineItem> itemsMap = new TreeMap<>();
    private double balance;

    public VendingMachine(List<VendingMachineItem> items) {
        loadMap(items);
        this.balance = 0;
    }

    public Collection<VendingMachineItem> getItems() {
        return itemsMap.values();

    }

    public Map<String, VendingMachineItem> getItemsMap() {
        return itemsMap;
    }

    public void addMoney(double feedMoney) {
        this.balance += feedMoney;
    }

    public void subtractMoney(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void loadMap(List<VendingMachineItem> items) {
        for (VendingMachineItem item : items) {
            itemsMap.put(item.getLocation(), item);
        }
    }
}
