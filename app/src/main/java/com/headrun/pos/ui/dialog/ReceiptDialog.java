package com.headrun.pos.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.headrun.pos.R;
import com.headrun.pos.adapter.ReceiptAdapter;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.callback.OnReceipt;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.DbUtils;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.main.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptDialog implements OnReceipt{

    private Context context;
    private Dialog dialog;
    private TextView total_amt;
    private TextView grand_amt;
    private TextView total_items;
    private TextView cgst_txt;
    private TextView sgst_txt;
    private TextView cgst_amt;
    private TextView sgst_amt;
    private TextView bill_no;
    private TextView order_by;
    private TextView order_date;
    private TextView disct_amt;
    private LinearLayout receipt_img;
    private Uri FilePathUri;
    private StorageReference storageReference;
    public static Bitmap bitmap;


    public ReceiptDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.receipt_dialog);
        dialog.setCancelable(false);

        RecyclerView recyclerView = dialog.findViewById(R.id.receipt_rv);
        total_amt = dialog.findViewById(R.id.total_amt);
        grand_amt = dialog.findViewById(R.id.grand_amt);
        total_items = dialog.findViewById(R.id.total_items);
        cgst_txt = dialog.findViewById(R.id.cgst_txt);
        sgst_txt = dialog.findViewById(R.id.sgst_txt);
        cgst_amt = dialog.findViewById(R.id.cgst_amt);
        sgst_amt = dialog.findViewById(R.id.sgst_amt);
        bill_no = dialog.findViewById(R.id.bill_no);
        order_date = dialog.findViewById(R.id.order_date);
        order_by = dialog.findViewById(R.id.order_taken_by);
        receipt_img = dialog.findViewById(R.id.receipt_img);
        disct_amt = dialog.findViewById(R.id.disct_amt);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ReceiptAdapter receiptAdapter = new ReceiptAdapter(context, this);
        recyclerView.setAdapter(receiptAdapter);

        storageReference = FirebaseStorage.getInstance().getReference();

        dialog.setCanceledOnTouchOutside(true);

        if(dialog.getWindow() != null) {
//            dialog.getWindow().getAttributes().windowAnimations = R.style.Slide_Up_Down;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        long time = System.currentTimeMillis();
        order_date.setText(General.getDateFormat(time, "dd/MM/yyyy"));


    }


    public void show(){
        try {
            dialog.show();
            getUser();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateBitmap();
                }
            }, 2000);
        } catch (Exception e){

        }
    }

    public void close(){
        dialog.hide();
    }

    @Override
    public void onTotalItemsPrice(List<Double> list) {

        int i;
        double sum = 0;
        for(i = 0; i < list.size(); i++){
            sum += list.get(i);
        }

        total_items.setText(""+list.size());

        total_amt.setText("₹ "+String.format("%.2f", sum));

        sgst_txt.setText("SGST@9% of "+ sum);
        cgst_txt.setText("CGST@9% of "+ sum);

        double cgst = 9 * sum / 100;
        double sgst = 9 * sum / 100;

        cgst_amt.setText("₹ "+cgst);
        sgst_amt.setText("₹ "+sgst);

        disct_amt.setText(MainActivity.dialog_disc_amt);
        grand_amt.setText(MainActivity.dialog_receipt_amt);
    }


    private void generateBitmap(){

        receipt_img.setDrawingCacheEnabled(true);
        receipt_img.buildDrawingCache();
        Bitmap bm = receipt_img.getDrawingCache().copy(Bitmap.Config.RGB_565, false);
        DbUtils.storeBitmap(bm);
        bitmap = bm;
        LogUtils.e(bm);
        receipt_img.setDrawingCacheEnabled(false);
        receipt_img.destroyDrawingCache();
        FilePathUri = getImageUri(bm);
        LogUtils.e(FilePathUri);
        uploadReceipt();
    }

    private Uri getImageUri(Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void getUploadedReceipt(){
        dialog.show();
        getUser();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                generateBitmap();
            }
        }, 2000);
    }

    private void uploadReceipt(){

        if (FilePathUri != null){

            final StorageReference receipt_rf = storageReference.child("receipt_images/"+"12345");
            receipt_rf.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    receipt_rf.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Map<String, Object> map = new HashMap<>();
                            map.put("receipt", uri.toString());

                            MyApplication.get().getmFirebaseFirestore().collection(Constants.RECEIPT)
                                    .document("12345").set(map);
                        }
                    });

                }
            });

        }

    }

    private void getUser(){

        MyApplication.get().getmFirebaseFirestore().collection(Constants.USER_DETAILS)
                .document(MyApplication.get().getmAuth().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                        String name = document.getString("first_name")+ " "+ document.getString("last_name");
                        order_by.setText(name);
                }
                else {
                    LogUtils.e("Error getting documents: ", task.getException());
                }
            }
        });

    }
}
