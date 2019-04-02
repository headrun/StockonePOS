package com.headrun.pos.ui.fragment.categories_frag;


import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.fragment.base.FragView;

import java.util.List;

public interface CategoriesFragView extends FragView {

    void showCategories(List<CategoryModel> list);
    void onSearchPressed();
    void onCrossPressed();

}
