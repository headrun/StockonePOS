package com.headrun.pos.model;

import com.headrun.pos.R;

import java.util.ArrayList;
import java.util.List;

public class SingleTransactionModel {

    private String name;
    private double price;
    private int quantity = 1;

    public SingleTransactionModel(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static List<SingleTransactionModel> initData() {

        List<SingleTransactionModel> list = new ArrayList<>();
        list.add(new SingleTransactionModel("Apple", 100.00, 1));
        list.add(new SingleTransactionModel("Orange", 80.00, 1));
        list.add(new SingleTransactionModel("Banana", 60.00, 1));
        list.add(new SingleTransactionModel("Guava", 150.00, 1));

        return list;
    }
}
