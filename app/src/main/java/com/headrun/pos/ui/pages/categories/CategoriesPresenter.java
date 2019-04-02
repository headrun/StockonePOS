package com.headrun.pos.ui.pages.categories;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.ui.pages.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPresenter extends BasePresenter<CategoriesView> {


    private List<CategoryModel> list;

    protected CategoriesPresenter(CategoriesView view) {
        super(view);
        list = new ArrayList<>();
    }

    public void getAllCategories(){

        getCategories();
    }


    private void getCategories(){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.CATEGORIES).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CATEGORY).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            list.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LogUtils.e(document.getId() + " => " + document.getData());
                                CategoryModel model = new CategoryModel();
                                model.setType(1);
                                model.setCat_name(document.getString("cat_name"));
                                list.add(model);
                            }
                            view.onHideProgressBar();
                            view.showCategories(list);
                        } else {
                            LogUtils.e("Error getting documents: ", task.getException());
                            view.onHideProgressBar();
                        }
                    }
                }      );
    }

}
