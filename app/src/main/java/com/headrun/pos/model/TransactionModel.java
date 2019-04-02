package com.headrun.pos.model;

public class TransactionModel {

    private String date, transaction_total_price, transaction_total_sales,
            receipt_no, payment_type, total_items, total_price, item_name,
            item_price, item_qty, time;

    public TransactionModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransaction_total_price() {
        return transaction_total_price;
    }

    public void setTransaction_total_price(String transaction_total_price) {
        this.transaction_total_price = transaction_total_price;
    }

    public String getTransaction_total_sales() {
        return transaction_total_sales;
    }

    public void setTransaction_total_sales(String transaction_total_sales) {
        this.transaction_total_sales = transaction_total_sales;
    }

    public String getReceipt_no() {
        return receipt_no;
    }

    public void setReceipt_no(String receipt_no) {
        this.receipt_no = receipt_no;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getTotal_items() {
        return total_items;
    }

    public void setTotal_items(String total_items) {
        this.total_items = total_items;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
