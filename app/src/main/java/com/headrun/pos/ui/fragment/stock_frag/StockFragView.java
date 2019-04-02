package com.headrun.pos.ui.fragment.stock_frag;

import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.fragment.base.FragView;

import java.util.List;

public interface StockFragView extends FragView {

    void showCategories(List<ProductModel> list);
    void onSearchPressed();
    void onCrossPressed();
}
