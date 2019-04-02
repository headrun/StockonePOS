package com.headrun.pos.ui.fragment.items_frag;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.fragment.base.FragPresenter;
import com.headrun.pos.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemsFragPresenter extends FragPresenter<ItemsFragView> {

    private List<ProductModel> list;

    protected ItemsFragPresenter(ItemsFragView view) {
        super(view);
        list = new ArrayList<>();
    }


    public void getItems(){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.PRODUCTS).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CREATED_PRODUCTS).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ProductModel model = new ProductModel();
                            model.setProduct_img(document.getString("product_img"));
                            model.setProduct_name(document.getString("product_name"));
                            model.setProduct_price(document.getDouble("product_price"));
                            model.setCategory(document.getString("category"));
                            model.setStock(document.getString("stock"));
                            model.setSell_by(document.getString("sell_by"));
                            model.setProduct_id(document.getString("product_id"));
                            model.setDescription(document.getString("description"));
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
        List<ProductModel> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (ProductModel s : list) {
            String filterText = s.getProduct_name().toLowerCase();
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
