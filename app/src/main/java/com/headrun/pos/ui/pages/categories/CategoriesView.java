package com.headrun.pos.ui.pages.categories;

import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;

public interface CategoriesView extends BaseView {

    void onHideProgressBar();
    void showCategories(List<CategoryModel> list);

}
