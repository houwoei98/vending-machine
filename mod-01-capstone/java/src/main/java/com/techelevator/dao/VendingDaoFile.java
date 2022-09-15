package com.techelevator.dao;

import com.techelevator.handler.Logger;
import com.techelevator.model.VendingMachine;
import com.techelevator.model.VendingMachineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingDaoFile {

    private String fileName;
    private Logger logger;

    public VendingDaoFile(String fileName, Logger logger) {
        this.fileName = fileName;
        this.logger = logger;
    }

    public List<VendingMachineItem> Load() {
        List<VendingMachineItem> items = new ArrayList<VendingMachineItem>();
        File inputFile = new File(fileName);
        //loop through lines of input files
        try (Scanner input = new Scanner(inputFile)) {
            while (input.hasNextLine()){
                String[] itemInfo = input.nextLine().split("\\|");
                // String slot location | String name | double price | String type
                VendingMachineItem vendingMachineItem = new VendingMachineItem(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), itemInfo[3]);
                items.add(vendingMachineItem);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
