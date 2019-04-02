package com.headrun.pos.utils;

import android.content.Context;
import android.content.Intent;

import com.headrun.pos.ui.pages.login.Login;
import com.headrun.pos.ui.pages.main.MainActivity;
import com.headrun.pos.ui.pages.new_product.NewProduct;
import com.headrun.pos.ui.pages.products.Products;
import com.headrun.pos.ui.pages.register.Register;
import com.headrun.pos.ui.pages.scratch_card.ScratchCard;
import com.headrun.pos.ui.pages.settings.Settings;
import com.headrun.pos.ui.pages.show_cards.ShowCards;
import com.headrun.pos.ui.pages.transaction.Transactions;
import com.headrun.pos.ui.pages.user_details.UserDetails;

import spencerstudios.com.bungeelib.Bungee;

public class ActivityManager {

    public static void LOGIN(Context context){

        context.startActivity(new Intent(context, Login.class));
        Bungee.slideRight(context);
    }

    public static void REGISTER(Context context){

        context.startActivity(new Intent(context, Register.class));
        Bungee.slideLeft(context);
    }

    public static void HOME(Context context){

        context.startActivity(new Intent(context, MainActivity.class));
        Bungee.slideLeft(context);
    }

    public static void USER_DETAILS(Context context){

        Intent intent = new Intent(context, UserDetails.class);
        context.startActivity(intent);

        Bungee.slideLeft(context);
    }

    public static void SETTINGS(Context context){

        context.startActivity(new Intent(context, Settings.class));
        Bungee.slideLeft(context);
    }

    public static void PRODUCTS(Context context){

        context.startActivity(new Intent(context, Products.class));
        Bungee.slideLeft(context);
    }

    public static void TRANSACTION(Context context){

        context.startActivity(new Intent(context, Transactions.class));
        Bungee.slideLeft(context);
    }

    public static void NEW_PRODUCTS(Context context, String product_img, String product_name, double product_price,
                                   String product_category, String product_stock, String  product_sell_by, String product_id,
                                    String product_desc, boolean is_update){

        Intent intent = new Intent(context, NewProduct.class);
        intent.putExtra("product_img", product_img);
        intent.putExtra("product_name", product_name);
        intent.putExtra("product_price", product_price);
        intent.putExtra("product_category", product_category);
        intent.putExtra("product_stock", product_stock);
        intent.putExtra("product_sell_by", product_sell_by);
        intent.putExtra("product_id", product_id);
        intent.putExtra("product_desc", product_desc);
        intent.putExtra("is_update", is_update);
        context.startActivity(intent);
        Bungee.slideLeft(context);
    }

    public static void SCRATCH_CARD(Context context){

        context.startActivity(new Intent(context, ScratchCard.class));
        Bungee.slideLeft(context);
    }

    public static void SHOW_CARDS(Context context){

        context.startActivity(new Intent(context, ShowCards.class));
        Bungee.slideLeft(context);
    }
}
