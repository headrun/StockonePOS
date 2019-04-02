package com.headrun.pos.ui.pages.new_product;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.pages.base.BasePresenter;
import com.headrun.pos.utils.Constants;
import java.io.ByteArrayOutputStream;

public class NewProPresenter extends BasePresenter<NewProView> {

    private StorageReference storageReference;
    boolean is_update;
    private Context context;

    protected NewProPresenter(NewProView view, Context context) {
        super(view);
        storageReference = FirebaseStorage.getInstance().getReference();
        this.context = context;
    }

    public void createProducts(Bitmap bmp){

        if (view.validateText()) {

            if (!is_update) {
                final ProductModel model = new ProductModel();

                final StorageReference product_ref = storageReference.child("product_images/" + view.name());

                if (bmp != null) {

                    view.changeBtnText();

                    product_ref.putFile(getImageUri(bmp)).addOnSuccessListener(taskSnapshot -> product_ref.getDownloadUrl().addOnSuccessListener(uri -> {
                        model.setProduct_img(uri.toString());
                        model.setCategory(view.category());
                        model.setProduct_name(view.name());
                        model.setProduct_price(Double.parseDouble(view.price()));
                        model.setStock(view.stock());
                        model.setSell_by(view.sell_by());
                        model.setDescription(view.description());
                        model.setProduct_id(view.barcode());
                        model.setCreated_at(System.currentTimeMillis());

                        MyApplication.get().getmFirebaseFirestore().collection(Constants.PRODUCTS)
                                .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                                .collection(Constants.CREATED_PRODUCTS).document().set(model)
                                .addOnSuccessListener(aVoid -> {

                                    view.changeViews();

                                });
                    }));

                } else {
                    ToastUtils.showLong("Please select image");
                }

            } else {

                MyApplication.get().getmFirebaseFirestore().collection(Constants.PRODUCTS)
                        .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                        .collection(Constants.CREATED_PRODUCTS).whereEqualTo("product_id", view.getProductId())
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                view.changeBtnText();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    MyApplication.get().getmFirebaseFirestore().collection(Constants.PRODUCTS)
                                            .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                                            .collection(Constants.CREATED_PRODUCTS).document(document.getId())
                                            .update(
                                                    "category", view.category(),
                                                    "description", view.description(),
                                                    "product_name", view.name(),
                                                    "product_price", Double.parseDouble(view.price()),
                                                    "stock", view.stock(),
                                                    "sell_by", view.sell_by()
                                            ).addOnSuccessListener(aVoid -> view.setButtonToUpdateProduct());
                                }
                            } else {
                                LogUtils.e("Error getting documents: ", task.getException());
                            }
                        });
            }
        }


    }

    private Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void isUpdated(){

        if (is_update) {
            view.updateViews();
        }
    }
}
