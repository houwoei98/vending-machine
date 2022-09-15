package com.techelevator;

import com.techelevator.dao.LogDao;
import com.techelevator.dao.LogDaoFile;
import com.techelevator.dao.VendingDaoFile;
import com.techelevator.handler.LogHandler;
import com.techelevator.handler.Logger;
import com.techelevator.model.VendingMachine;
import com.techelevator.model.VendingMachineItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VendingDaoFileTest {

    private VendingDaoFile cut;
    LogDao logDao = new LogDaoFile();
    Logger logger = new LogHandler(logDao);

    @Test
    public void loadTest() {
        cut = new VendingDaoFile("vendingmachinetest.csv", logger);
        VendingMachineItem item1 = new VendingMachineItem("A1", "Potato Crisps", 3.05, "Chip");
        VendingMachineItem item2 = new VendingMachineItem("A2", "Stackers", 1.45, "Chip");
        List<VendingMachineItem> vendingMachineList = new ArrayList<>();
        vendingMachineList.add(item1);
        vendingMachineList.add(item2);
        List<VendingMachineItem> cutTest = cut.Load();
        assertEquals(vendingMachineList, cutTest);
    }


}
