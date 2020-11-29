package models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import entities.Sales;

public class SalesDAO {
     List<Sales> list;
    private static SalesDAO INSTANCE = null;

    private SalesDAO() {
        this.list = new LinkedList<>();
    }

    public static SalesDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SalesDAO();
        }
        return INSTANCE;
    }

    public boolean add(Sales s) {
        return list.add(s);
    }

    public boolean addAll(Sales[] sales) {
        return list.addAll(Arrays.asList(sales));
    }

    public int getSize() {
        return list.size();
    }



}
