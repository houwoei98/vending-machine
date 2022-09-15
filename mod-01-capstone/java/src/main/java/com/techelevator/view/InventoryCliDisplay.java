package com.techelevator.view;


//import com.techelevator.handlers.*;
//import com.techelevator.helpers.*;
//import com.techelevator.model.*;
//
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryCliDisplay { //implements InventoryDisplay {
    private static final String MUNCHY_LINE_FORMAT = "{0} ${1} ({2}) {3}";
    private static final int NUMBER_VALUES_PRICE = 4;
    private static final String OUTPUT_STRING = "{0}  ${1}  ({3}) {2}";

    private static final String[] OUTPUT = {"|--------{0}---------|   ",
            "| {2}|   ",
            "|-------------------|   ",
            "|${1}   {3}|   ",
            "|-------------------|   "};


    private PrintWriter out;
    private Scanner in;
//    private LanguageResources resources;
//    private Vendable vendingHandler;
//git push
//    public InventoryCliDisplay(InputStream input, OutputStream output, LanguageResources resources,
//                               Vendable vendingHandler) {
//
//        this.out = new PrintWriter(output);
//        this.in = new Scanner(input);
//        this.resources = resources;
//        this.vendingHandler = vendingHandler;
//    }
//
//    //display inventory list
//    @Override
//    public void displayMunchyStock(MunchyStock munchyStock){
//        for (Munchy munchy : munchyStock.getMunchies()){
//            String msg = formatInventoryItem(OUTPUT_STRING,munchy);
//            out.println(msg);
//        }
//        out.flush();
//    }
//
//    //display individual item
//    @Override
//    public void displayMunchy(Munchy munchy){
//        //private static final String MUNCHY_LINE_FORMAT = "{0} ${1} ({2}) {3}";
//        out.println(MessageFormat.format(MUNCHY_LINE_FORMAT,
//                munchy.getMunchyCode(),
//                BigDecimalHelper.getFormattedBalance(munchy.getPrice(),NUMBER_VALUES_PRICE),
//                munchy.getNumberInStock(),
//                munchy.getMunchyName()));
//    }
//
//    public void feedMoney(MachineBalance machineBalance){
//        String value = "";
//        while (!value.equals("0")){
//            out.println(resources.getDisplay("FeedMoneyRequest"));
//            out.flush();
//            value = in.nextLine();
//            if (!vendingHandler.feedMoney(machineBalance,value)){
//                out.println(resources.getDisplay("ErrorOccurred"));
//                out.flush();
//            } else {
//                //print
//                out.println(resources.getDisplay("CurrentMoney")+ value);
//                out.println(resources.getDisplay("CurrentBalance")+ BigDecimalHelper.getFormattedBalance(machineBalance.getBalance(),NUMBER_VALUES_PRICE));
//            }
//        }
//    }
//    //ask for item (get and send to validate)
//    public void getMunchyFromVendingMachine(MunchyStock munchyStock, MachineBalance machineBalance){
//        this.displayMunchyStock(munchyStock);
//        boolean loop = true;
//        while (loop) {
//            out.println(resources.getDisplay("ChooseProduct"));
//            out.flush();
//            String munchyCode = in.nextLine();
//            if (munchyCode.isEmpty()) return;
//            switch (vendingHandler.purchaseItem(machineBalance,munchyStock,munchyCode)){
//                case Success:
//                    Munchy munchy = munchyStock.getMunchyByCode(munchyCode);
//                    Object[] params = new Object[]{};
//                    //{0} ({1}) ${2}\nBalance: {3}\n{4}"
//                    out.println(MessageFormat.format(resources.getDisplay("ItemPurchase"),
//                            munchy.getMunchyName(),
//                            munchy.getNumberInStock(),
//                            BigDecimalHelper.getFormattedBalance(munchy.getPrice(),NUMBER_VALUES_PRICE),
//                            machineBalance.getBalance(),
//                            munchy.getMunchyType().getMunchyMessage()));
//                    loop = false;
//                    break;
//                case NotEnoughMoney:
//                    out.println(resources.getDisplay("FeedNeed"));//LowFunds
//                    break;
//                case NoInventory:
//                    out.println(resources.getDisplay("SoldOut"));//LowFunds
//                    break;
//                case NoItemFound:
//                    out.println(MessageFormat.format(resources.getDisplay("CodeNotFound"),munchyCode));
//                    break;
//                case Error:
//                    out.println(resources.getDisplay("ErrorOccurred"));
//                    break;
//            }
//            out.flush();
//        }
//
//    }
//
//    public void displayMunchiesPretty(MunchyStock munchyStock) {
//        String[] outputFormat = OUTPUT;
//        List<String> lines = new ArrayList<>();
//        int numberOfColumns = 4;
//        Munchy[] items = munchyStock.getMunchies();
//        int numberOfRows = items.length/numberOfColumns;
//
//        for (int currentRow = 0;currentRow<numberOfRows;currentRow++){
//            //foreach line of output
//
//            for (int outputFormatIndex = 0;outputFormatIndex<outputFormat.length;outputFormatIndex++){
//                int startNumber = currentRow*numberOfColumns;
//                int finalNumber = (currentRow*numberOfColumns)+numberOfColumns-1;
//                for (int itemPointer = startNumber; itemPointer<=finalNumber; itemPointer++){
//                    lines.add(formatInventoryItem(outputFormat[outputFormatIndex],items[itemPointer]));
//                }
//                lines.add(System.lineSeparator());
//
//            }
//
//            lines.add(System.lineSeparator());
//        }
//        for (String line: lines){
//            out.print(line);
//        }
//        out.flush();
//    }
//    private String formatInventoryItem(String formatString,Munchy item) {
//        String name = item.getMunchyName() + "                    ";
//        String inventoryFormat = resources.getDisplay("InventoryDisplay");
//        String numberLeft = item.getNumberInStock()>0 ?
//                MessageFormat.format(inventoryFormat,AnsiColors.yellowValue(String.valueOf(item.getNumberInStock()),false)) :
//                AnsiColors.redValue(resources.getDisplay("SoldOutDisplay"),false);
//        //Remaining:
//        return AnsiColors.blackBgValue(MessageFormat.format(formatString,
//                AnsiColors.redValue(item.getMunchyCode(),false),
//                AnsiColors.greenValue(BigDecimalHelper.getFormattedBalance(item.getPrice(),NUMBER_VALUES_PRICE),false),
//                AnsiColors.cyanValue(name.substring(0,18),false),
//                numberLeft
//        ),true);
//    }

}
