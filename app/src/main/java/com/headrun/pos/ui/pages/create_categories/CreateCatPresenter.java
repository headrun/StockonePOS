package com.headrun.pos.ui.pages.create_categories;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.headrun.pos.utils.Constants;

public class CreateCatPresenter extends BasePresenter<CreateCatView> {

    protected CreateCatPresenter(CreateCatView view) {
        super(view);
    }

    public void createCategory(){

        if (view.validateText()) {

            view.changeBtnText();

            CategoryModel model = new CategoryModel();
            model.setCat_name(view.categoryName());
            model.setCreated_at(System.currentTimeMillis());
            model.setUser_id(MyApplication.get().getmAuth().getCurrentUser().getUid());

            MyApplication.get().getmFirebaseFirestore().collection(Constants.CATEGORIES)
                    .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                    .collection(Constants.CATEGORY).document()
                    .set(model).addOnSuccessListener(aVoid -> {

                view.changeViews();

            });
        }
    }
}
