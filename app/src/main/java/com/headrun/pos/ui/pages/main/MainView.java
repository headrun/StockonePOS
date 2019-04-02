package com.headrun.pos.ui.pages.main;

import android.view.View;

import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;
import java.util.Map;

public interface MainView extends BaseView {

    void hideProgressBar();
    Map<String, Object> transactionMap();
    void ownerName(String ownerName);
    void orderTakenBy(String orderTakenBy);
    void showCategories(List<CategoryModel> list);
    void showProducts(List<ProductModel> list);
    void showProductsInGrid();
    void showProductsInLinear();
    void setViewForBle(View view);

}
