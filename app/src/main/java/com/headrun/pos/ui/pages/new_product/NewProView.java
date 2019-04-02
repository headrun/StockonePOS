package com.headrun.pos.ui.pages.new_product;

import com.headrun.pos.ui.pages.base.BaseView;

public interface NewProView extends BaseView {

    boolean validateText();
    String category();
    String name();
    String price();
    String stock();
    String sell_by();
    String description();
    String barcode();
    String getProductId();
    void changeBtnText();
    void changeViews();
    void setButtonToUpdateProduct();
    void updateViews();
}
