package com.headrun.pos.utils;

import android.graphics.Bitmap;

import io.paperdb.Paper;

public class DbUtils {

    public static void storeItemTotal(int totalItems){
        Paper.book().write("totalItems",totalItems);
    }

    public static int getItemTotal(){
        return Paper.book().read("totalItems");
    }

    public static void storeTotalPrice(double total_price){
        Paper.book().write("total_price",total_price);
    }

    public static double getTotalPrice(){
        return Paper.book().read("total_price");
    }

    public static void storeGrandTotal(double grand_total){
        Paper.book().write("grand_total",grand_total);
    }

    public static double getGrandTotal(){
        return Paper.book().read("grand_total");
    }

    public static void storeBitmap(Bitmap bitmap){
        Paper.book().write("bitmap",bitmap);
    }

    public static Bitmap getBitmap(){
        return Paper.book().read("bitmap");
    }


    public static void storeDeviceName(String deviceName){
        Paper.book().write("deviceName",deviceName);
    }

    public static String getDeviceName(){
        return Paper.book().read("deviceName");
    }

    public static void storeDeviceAddress(String deviceAddress){
        Paper.book().write("deviceAddress",deviceAddress);
    }

    public static String getDeviceAddress(){
        return Paper.book().read("deviceAddress");
    }

    public static void storeUserSessionTime(long sessionTime){
        Paper.book().write("sessionTime",sessionTime);
    }

    public static long getUserSessionTime(){
        return Paper.book().read("sessionTime");
    }
}
