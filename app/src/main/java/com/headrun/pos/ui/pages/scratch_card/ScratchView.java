package com.headrun.pos.ui.pages.scratch_card;

import com.headrun.pos.ui.pages.base.BaseView;

public interface ScratchView extends BaseView {

    boolean validateLogin();
    String getFromDate();
    String getToDate();
    String getDiscountAmt();
    void hideButtons();
    void showButtons();
}
