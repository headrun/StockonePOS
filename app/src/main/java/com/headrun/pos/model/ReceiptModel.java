package com.headrun.pos.model;

public class ReceiptModel {

    private int total_quant;
    private double total_price;
    private double grand_total;

    public ReceiptModel() {
    }

    public int getTotal_quant() {
        return total_quant;
    }

    public void setTotal_quant(int total_quant) {
        this.total_quant = total_quant;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }
}
