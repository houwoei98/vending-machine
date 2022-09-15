package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.handler.*;
import com.techelevator.model.VendingMachine;
import com.techelevator.view.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        //read in input file (csv)
        // parse file with String and double
        LogDao logDao = new LogDaoFile();
        Logger logger = new LogHandler(logDao);
        VendingDaoFile vendingReader = new VendingDaoFile("vendingmachine.csv", logger);

        VendingMachine vendingMachine = new VendingMachine(vendingReader.Load());

        InputStream userInput = System.in;
        PrintStream userOutput = System.out;
        Menu menu = new Menu(userInput, userOutput, true);

        VendingCli vendingCli = new VendingCli(menu, userOutput, userInput, logger, vendingMachine);
        vendingCli.run();

    }
}
