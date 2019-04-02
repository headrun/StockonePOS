package com.headrun.pos.ui.pages.create_categories;

import com.headrun.pos.ui.pages.base.BaseView;

public interface CreateCatView extends BaseView {

    boolean validateText();
    void changeViews();
    String categoryName();
    void changeBtnText();
}
