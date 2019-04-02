package com.headrun.pos.ui.pages.main;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.SingleTransactionModel;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.pepperonas.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPresenter extends BasePresenter<MainView> {


    private ArrayList<String> categorylist;
    private List<CategoryModel> categoryModelList;
    List<ProductModel> productModelList;
    boolean isScanned;

    protected MainPresenter(MainView view) {
        super(view);
        categorylist = new ArrayList<>();
        categoryModelList = new ArrayList<>();
        productModelList = new ArrayList<>();
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
                            categorylist.clear();
                            categoryModelList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel model = new CategoryModel();
                                model.setCat_name(document.getString("cat_name"));
                                categorylist.add(model.getCat_name());
                                categoryModelList.add(model);
                            }
                            if (view != null)
                            view.showCategories(categoryModelList);
                            categorylist.add("All");
                        } else {
                            LogUtils.e("Error getting documents: ", task.getException());
                        }
                    }
                }      );
    }

    private void addItems(){

        String receipt_no = General.randomString(6);

        for (Map.Entry<String, Object> entry : view.transactionMap().entrySet()) {


            SingleTransactionModel model = (SingleTransactionModel) entry.getValue();

            final Map<String, Object> soldData = new HashMap<>();
            soldData.put("item_name", model.getName());
            soldData.put("item_price", String.valueOf(model.getPrice()));
            soldData.put("item_quantity", String.valueOf(model.getQuantity()));

            addTransaction(soldData, receipt_no);

        }

    }

    private void addTransaction(Map<String, Object> soldData, String receipt_no){

        final long time = System.currentTimeMillis();

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.TRANSACTIONS).document(General.getDateFormat(time, "dd-MM-yyyy"))
                .collection("Receipt").document(receipt_no)
                .collection("SoldItems").document().set(soldData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                MyApplication.get().getmFirebaseFirestore()
                        .collection(Constants.TRANSACTIONS).document(General.getDateFormat(time, "dd-MM-yyyy")).collection("Receipt").document(receipt_no)
                        .collection("SoldItems")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            int sales = 0;
                            double price = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LogUtils.e(document.getId() + " => " + document.getData());
                                try {
                                    sales+=Integer.parseInt(document.getString("item_quantity"));
                                    price+= Double.parseDouble(document.getString("item_price"));
                                }catch (NumberFormatException e){

                                }
                            }
                            LogUtils.e(sales);
                            LogUtils.e(price);
                            final Map<String, Object> receiptData = new HashMap<>();
                            receiptData.put("receipt_no", receipt_no);
                            receiptData.put("time", String.valueOf(time));
                            receiptData.put("payment_type", "cash");
                            receiptData.put("total_items", String.valueOf(sales));
                            receiptData.put("total_price", String.valueOf(price));
                            MyApplication.get().getmFirebaseFirestore()
                                    .collection(Constants.TRANSACTIONS).document(General.getDateFormat(time, "dd-MM-yyyy"))
                                    .collection("Receipt").document(receipt_no).set(receiptData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            MyApplication.get().getmFirebaseFirestore()
                                                    .collection(Constants.TRANSACTIONS).document(General.getDateFormat(time, "dd-MM-yyyy")).collection("Receipt")
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                            if (task.isSuccessful()) {
                                                                int sales = 0;
                                                                double price = 0;
                                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                                    LogUtils.e(document.getId() + " => " + document.getData());
                                                                    try {
                                                                        sales+=Integer.parseInt(document.getString("total_items"));
                                                                        price+= Double.parseDouble(document.getString("total_price"));
                                                                    }catch (NumberFormatException e){

                                                                    }
                                                                }
                                                                LogUtils.e(sales);
                                                                LogUtils.e(price);
                                                                final Map<String, Object> dateData = new HashMap<>();
                                                                dateData.put("total_price", String.valueOf(price));
                                                                dateData.put("total_sales", String.valueOf(sales));
                                                                MyApplication.get().getmFirebaseFirestore()
                                                                        .collection(Constants.TRANSACTIONS).document(General.getDateFormat(time, "dd-MM-yyyy")).set(dateData);
                                                            } else {
                                                                LogUtils.e("Error getting documents: ", task.getException());
                                                            }
                                                        }
                                                    });
                                        }
                                    });
                        } else {
                            LogUtils.e("Error getting documents: ", task.getException());
                        }

                    }
                });

            }
        });

    }

    public void getUser(){

        MyApplication.get().getmFirebaseFirestore().collection(Constants.USER_DETAILS)
                .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    String name = document.getString("first_name")+ " "+ document.getString("last_name");
                    view.ownerName(name);
                    view.orderTakenBy(name);
                }
                else {
                    LogUtils.e("Error getting documents: ", task.getException());
                }
            }
        });

    }

    public void getProductsCategories(String s, final int type){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.PRODUCTS).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CREATED_PRODUCTS).whereEqualTo("category", s)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        productModelList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            LogUtils.e(document.getData());
                            ProductModel model = new ProductModel();
                            model.setProduct_img(document.getString("product_img"));
                            model.setProduct_name(document.getString("product_name"));
                            model.setProduct_price(document.getDouble("product_price"));
                            model.setCategory(document.getString("category"));
                            model.setStock(document.getString("stock"));
                            model.setType(type);
                            productModelList.add(model);
                        }
                        if (type == 0){
                            view.showProductsInGrid();
                        }else {
                            view.showProductsInLinear();
                        }
                        view.hideProgressBar();
                        view.showProducts(productModelList);
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        view.hideProgressBar();
                    }
                });

    }

    public void getProductsByScan(String result){

        MyApplication.get().getmFirebaseFirestore()
                .collection(Constants.PRODUCTS).document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .collection(Constants.CREATED_PRODUCTS).whereEqualTo("product_id", result)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        productModelList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ProductModel model = new ProductModel();
                            model.setProduct_img(document.getString("product_img"));
                            model.setProduct_name(document.getString("product_name"));
                            model.setProduct_price(document.getDouble("product_price"));
                            model.setCategory(document.getString("category"));
                            productModelList.add(model);
                        }
                        LogUtils.e(productModelList.get(0).getProduct_name());
                        view.hideProgressBar();
                        view.showProducts(productModelList);
                    } else {
                        LogUtils.e("Error getting documents: ", task.getException());
                        view.hideProgressBar();
                    }
                    isScanned = false;
                });
    }

    public void getSearchableDevices(Context context){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bluetooth_list, null);
        this.view.setViewForBle(view);
    }
}
