package com.techelevator.model;

import com.techelevator.view.AnsiColors;

import java.text.MessageFormat;
import java.util.*;

public class VendingMachineItem {
    private String location;
    private String name;
    private double price;
    private SnackType type;
    private int stock;

    public VendingMachineItem(String location, String name, double price, String type) {
        this.location = location;
        this.name = name;
        this.price = price;
        try {
            this.type = SnackType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            this.type = SnackType.DEFAULT;
        }
        this.stock = 5;
    }


    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public SnackType getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public String getSound() {
        return type.sound;
    }

    public boolean buyItem() {
        int refBalance = this.stock - 1;

        if (refBalance >= 0) {
            this.stock -= 1;
            return true;
        } else {
            return false;
        }

    }

    // basic format
    public String displayItem() {
        String currentStock = (this.stock == 0) ? "  SOLD OUT" :  ("  Stock available: " + this.stock);
        String output = getLocation() + " " + getName() + "  $" + getPrice() + currentStock;
        return output;
    }

    // formatted in columns
    public String displayItemFormatted() {
        String MUNCHY_LINE_FORMAT = "{0} ${1,number,0.00} ({2}) {3}";
        String currentStock = (this.stock == 0) ? "SOLD OUT" : String.valueOf(this.stock);
        String output = MessageFormat.format(MUNCHY_LINE_FORMAT, getLocation(), getPrice(), currentStock, getName());
        return output;
    }

    // formatted with colours
    public String displayItemPrettyAnsi() {
        String MUNCHY_LINE_FORMAT = "{0}   ${1,number,0.00}   ({2})   {3}";
        String currentStock = (this.stock == 0) ? "SOLD OUT" : String.valueOf(this.stock);
        String output = MessageFormat.format(MUNCHY_LINE_FORMAT,
                AnsiColors.redValue(getLocation(), true),
                getPrice(),
                (getStock()>0 ?
                AnsiColors.greenValue(String.valueOf(getStock()),true) :
                AnsiColors.redValue("SOLD OUT",true)),
                getName());
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineItem that = (VendingMachineItem) o;
        return Double.compare(that.price, price) == 0 && stock == that.stock && location.equals(that.location) && name.equals(that.name) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, name, price, type, stock);
    }
}
