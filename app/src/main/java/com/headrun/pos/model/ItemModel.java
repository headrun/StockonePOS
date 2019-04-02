package com.headrun.pos.model;

import com.headrun.pos.R;

import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    private int item_image;
    private String item_id;
    private String item_name;
    private double item_price;
    private int quantity = 1;
    private int cgst_percentage, sgst_percentage;

    public ItemModel(int item_image, String item_name, double item_price, int cgst_percentage, int sgst_percentage) {
        this.item_image = item_image;
        this.item_name = item_name;
        this.item_price = item_price;
        this.sgst_percentage = sgst_percentage;
        this.cgst_percentage = cgst_percentage;
    }

    public ItemModel(String name, double price, int quantity) {
        this.item_name = name;
        this.item_price = price;
        this.quantity = quantity;
    }

    public void setName(String item_name) {
        this.item_name = item_name;
    }

    public void setPrice(double item_price) {
        this.item_price = item_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return item_name;
    }

    public double getPrice() {
        return item_price;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getCgst_percentage() {
        return cgst_percentage;
    }

    public void setCgst_percentage(int cgst_percentage) {
        this.cgst_percentage = cgst_percentage;
    }

    public int getSgst_percentage() {
        return sgst_percentage;
    }

    public void setSgst_percentage(int sgst_percentage) {
        this.sgst_percentage = sgst_percentage;
    }

    public static List<ItemModel> initData() {

        List<ItemModel> list = new ArrayList<>();
        list.add(new ItemModel(R.drawable.apple, "Apple", 100.00, 9 , 9));
        list.add(new ItemModel(R.drawable.orange, "Orange", 80.00, 9 , 9));
        list.add(new ItemModel(R.drawable.bananas, "Banana", 60.00, 9 , 9));
        list.add(new ItemModel(R.drawable.guava, "Guava", 150.00, 9 , 9));

        return list;
    }
}
