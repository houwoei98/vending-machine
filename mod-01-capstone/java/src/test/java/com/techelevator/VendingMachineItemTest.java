package com.techelevator;

import com.techelevator.model.VendingMachineItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class VendingMachineItemTest {
    //String location, String name, double price, String type
    private VendingMachineItem cut;
    private String location = "A1";

    @Test
    public void getSoundTest() {
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Chip");
        assertEquals("Incorrect sound!", "Crunch Crunch, Yum!", cut.getSound());
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Candy");
        assertEquals("Incorrect sound!", "Munch Munch, Yum!", cut.getSound());
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Drink");
        assertEquals("Incorrect sound!", "Glug Glug, Yum!", cut.getSound());
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Gum");
        assertEquals("Incorrect sound!", "Chew Chew, Yum!", cut.getSound());
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "asd");
        assertEquals("Incorrect sound!", "", cut.getSound());

    }

    @Test
    public void buyItemTest() {
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Chip");
        cut.buyItem();
        assertEquals("Incorrect stock!", 4, cut.getStock());
        cut.buyItem();
        cut.buyItem();
        cut.buyItem();
        cut.buyItem();
        assertEquals("Incorrect stock!", 0, cut.getStock());
        assertEquals("Item is out of stock - not available to buy", false, cut.buyItem());
    }

    @Test
    public void displayItemTest() {
        cut = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Chip");
        assertEquals("Display incorrectly!", "A1 Potato Crisps  $3.05  Stock available: 5", cut.displayItem());
        cut.buyItem();
        cut.buyItem();
        cut.buyItem();
        cut.buyItem();
        cut.buyItem();
        assertEquals("Display incorrectly!", "A1 Potato Crisps  $3.05  SOLD OUT", cut.displayItem());
    }
}
