package com.headrun.pos.ui.fragment.categories_frag;

import com.blankj.utilcode.util.LogUtils;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.fragment.base.FragPresenter;
import com.headrun.pos.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragPresenter extends FragPresenter<CategoriesFragView> {

    private List<CategoryModel> list;

    protected CategoriesFragPresenter(CategoriesFragView view) {
        super(view);
        list = new ArrayList<>();
    }

    public void getCategories(){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.CATEGORIES).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CATEGORY).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            CategoryModel model = new CategoryModel();
                            model.setType(1);
                            model.setCat_name(document.getString("cat_name"));
                            list.add(model);
                        }
                        if (view != null) {
                            view.hideProgressBar();
                            view.showCategories(list);
                        }
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        if (view != null)
                        view.hideProgressBar();
                    }
                });
    }

    public void filter(String text) {
        //new array list that will hold the filtered data
        List<CategoryModel> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (CategoryModel s : list) {
            String filterText = s.getCat_name().toLowerCase();
            if (filterText.contains(text.toLowerCase())) {
                filterdNames.add(s);
            }

            //calling a method of the adapter class and passing the filtered list
            if (view != null)
            view.showCategories(filterdNames);
        }

    }

    public void onSearchPressed(){
        if (view != null)
        view.onSearchPressed();
    }

    public void onCrossPressed(){
        if (view != null)
        view.onCrossPressed();
    }
}
