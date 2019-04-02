package com.headrun.pos.model;

import java.util.Map;

public class CheckoutModel {

    private int quantity = 1;
    private double price;
    private String name;
    private Map<String, Integer> map;
    private ItemModel itemModel;

    public CheckoutModel() {
    }

    public CheckoutModel(int quantity, double price, String name) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
