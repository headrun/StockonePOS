package com.headrun.pos.callback;

import java.util.Map;

public interface OnCheckoutItemClicked {

    void onProductIncDec(Map<String, Integer> map);

    void onTotalPrice(double price);

    void onDelete(double price, int quantity);
}
