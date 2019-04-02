package com.headrun.pos.ui.pages.new_product;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.asksira.bsimagepicker.Utils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.headrun.pos.R;
import com.headrun.pos.ui.pages.scan_barcode.ScanBarcodeActivity;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.categories.Categories;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;
import com.mindorks.paracamera.Camera;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewProduct extends BaseActivity<NewProPresenter> implements NewProView, BSImagePicker.OnSingleImageSelectedListener{

    @BindView(R.id.toolbar_new_product) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbar_title;
    @BindView(R.id.et_sell_by) EditText et_sell_by;
    @BindView(R.id.et_category) EditText et_category;
    @BindView(R.id.et_product_name) EditText et_product_name;
    @BindView(R.id.et_pro_price) EditText et_pro_price;
    @BindView(R.id.et_stock) EditText et_stock;
    @BindView(R.id.et_description) EditText et_description;
    @BindView(R.id.product_image) ImageView product_image;
    @BindView(R.id.img_gallery) ImageView img_gallery;
    @BindView(R.id.product_name) TextView pro_name;
    @BindView(R.id.product_price) TextView pro_price;
    @BindView(R.id.btn_product_created) Button btn_product_created;
    @BindView(R.id.et_barcode_ids) EditText et_barcode_ids;
    @BindView(R.id.new_product_scan) ImageView new_product_scan;
    BottomSheetListener bottomSheetListener, galleryBottomSheet;
    Camera camera;
    BSImagePicker singleSelectionPicker;
    Bitmap bmp;

    String product_img, product_name, product_category, product_stock, product_sell_by,
            product_id, product_desc;
    Boolean is_update;

    double product_price;

    Bundle bundle;

    @NonNull
    @Override
    protected NewProPresenter createPresenter() {
        return new NewProPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        init();

    }

    private void init(){

        Intent extras = getIntent();
        bundle = extras.getExtras();
        if(bundle == null){
            ToastUtils.showLong("Some problem occurs.");
            finish();
            return;
        }

        is_update = bundle.getBoolean("is_update");
        presenter.is_update = is_update;

        initializeBottomSheetListener();
        initializeGalleryBottomSheet();
        initializeImagePicker();
        addNameListener();
        addPriceListener();

        presenter.isUpdated();
    }

    @OnClick(R.id.btn_product_created)
    void onCreateProduct(){

       presenter.createProducts(bmp);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.et_sell_by)
    void onSellBy(){

        new BottomSheet.Builder(this)
                .setSheet(R.menu.sell_by_menu)
                .setListener(bottomSheetListener)
                .show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (camera != null) {
            bmp = camera.getCameraBitmap();
            if (bmp != null) {
                product_image.setImageBitmap(bmp);
            } else {
                Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == 0){
            if (data != null) {
                String result = data.getStringExtra("cat_name");
                et_category.setText(result);
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == 1){
            if (data != null) {
                String result = data.getStringExtra("barcode");
                et_barcode_ids.setText(result);
            }
        }
    }


    @OnClick(R.id.et_category)
    void onCategorySelected(){

        Intent intent = new Intent(this, Categories.class);
        startActivityForResult(intent, 0);
    }

    private void initializeBottomSheetListener(){
        bottomSheetListener = new BottomSheetListener() {
            @Override
            public void onSheetShown(@NonNull BottomSheet bottomSheet, @Nullable Object o) {

            }

            @Override
            public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem, @Nullable Object o) {

                et_sell_by.setText(menuItem.getTitle());

            }

            @Override
            public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @Nullable Object o, int i) {

            }
        };
    }

    private void initializeGalleryBottomSheet(){
        galleryBottomSheet = new BottomSheetListener() {
            @Override
            public void onSheetShown(@NonNull BottomSheet bottomSheet, @Nullable Object o) {

            }

            @Override
            public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem, @Nullable Object o) {

                if (menuItem.getTitle().equals("Camera")){

                    initializeCamera();
                    try {
                        camera.takePicture();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else {

                    singleSelectionPicker.show(getSupportFragmentManager(), "picker");
                }

            }

            @Override
            public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @Nullable Object o, int i) {

            }
        };
    }

    @OnClick(R.id.img_gallery)
    void onProductImage(){

        requestPermission();

    }

    private void initializeCamera(){

        // Build the camera
        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(1)
                .setDirectory("POD")
                .setName("pod_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(this);

    }

    private void initializeImagePicker(){

        singleSelectionPicker = new BSImagePicker.Builder("com.headrun.pos.view.pages")
                .setMaximumDisplayingImages(24)
                .setSpanCount(3)
                .setGridSpacing(Utils.dp2px(2))
                .setPeekHeight(Utils.dp2px(360))
                .hideCameraTile()
                .hideGalleryTile()
                .setTag("A request ID")
                .build();
    }

    private void requestPermission(){

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){

                            new BottomSheet.Builder(NewProduct.this)
                                    .setSheet(R.menu.image_menu)
                                    .setListener(galleryBottomSheet)
                                    .show();

                        }
                        if(report.isAnyPermissionPermanentlyDenied()){
                            ToastUtils.showLong("Please Tap on Permission and allow us required permission.");
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        product_image.setImageURI(uri);
        try {
            bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNameListener(){

        et_product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0){
                    pro_name.setText("Product name");
                }else {
                    pro_name.setText(s);
                }
            }
        });
    }

    private void addPriceListener(){

        et_pro_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0){
                    pro_price.setText("₹ 0.00");
                }else {
                    pro_price.setText("₹ "+s);
                }
            }
        });
    }

    @OnClick(R.id.new_product_scan)
    void onProductScanned(){

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            Intent intent = new Intent(NewProduct.this, ScanBarcodeActivity.class);
                            intent.putExtra("from_new_product", true);
                            startActivityForResult(intent, 1);
                        }else {
                        }
                        if(report.isAnyPermissionPermanentlyDenied()){
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
//                                ActivityManager.PERMISSION_TAB(PermissionActivity.this);
                    }
                }).check();

    }

    @Override
    public boolean validateText() {

        General.hideKeyboard(this);

        final String category = et_category.getText().toString();
        final String name = et_product_name.getText().toString();
        final String price = et_pro_price.getText().toString();
        final String stock = et_stock.getText().toString();
        final String sell_by = et_sell_by.getText().toString();
        final String description = et_description.getText().toString();
        final String barcode = et_barcode_ids.getText().toString();

        if (TextUtils.isEmpty(category)){
            ToastUtils.showLong("Please select category");
            return false;
        }
        if (TextUtils.isEmpty(name)){
            ToastUtils.showLong("Product name is empty");
            return false;
        }
        if (TextUtils.isEmpty(price)){
            ToastUtils.showLong("Product price is empty");
            return false;
        }
        if (TextUtils.isEmpty(stock)){
            ToastUtils.showLong("Please enter stock");
            return false;
        }
        if (TextUtils.isEmpty(sell_by)){
            ToastUtils.showLong("Sell by is empty.");
            return false;
        }
        if (TextUtils.isEmpty(barcode)){
            ToastUtils.showLong("Please scan barcode id.");
            return false;
        }

        return true;
    }

    @Override
    public String category() {
        return et_category.getText().toString();
    }

    @Override
    public String name() {
        return et_product_name.getText().toString();
    }

    @Override
    public String price() {
        return et_pro_price.getText().toString();
    }

    @Override
    public String stock() {
        return et_stock.getText().toString();
    }

    @Override
    public String sell_by() {
        return et_sell_by.getText().toString();
    }

    @Override
    public String description() {
        return et_description.getText().toString();
    }

    @Override
    public String barcode() {
        return et_barcode_ids.getText().toString();
    }

    @Override
    public String getProductId() {
        return product_id;
    }

    @Override
    public void changeBtnText() {

        btn_product_created.setText("Please wait...");
    }

    @Override
    public void changeViews() {

        btn_product_created.setText("Create Product");
        ToastUtils.showLong("Product created");

        et_category.getText().clear();
        et_product_name.getText().clear();
        et_pro_price.getText().clear();
        et_stock.getText().clear();
        et_sell_by.getText().clear();
        et_description.getText().clear();
        et_barcode_ids.getText().clear();
        product_image.setImageDrawable(null);

    }

    @Override
    public void setButtonToUpdateProduct() {

        btn_product_created.setText("Update Product");
        ToastUtils.showLong("Product updated.");

    }

    @Override
    public void updateViews() {

        product_img = bundle.getString("product_img");
        product_name = bundle.getString("product_name");
        product_category = bundle.getString("product_category");
        product_stock = bundle.getString("product_stock");
        product_sell_by = bundle.getString("product_sell_by");
        product_id = bundle.getString("product_id");
        product_desc = bundle.getString("product_desc");
        product_price = bundle.getDouble("product_price");

        Glide.with(this).load(product_img).into(product_image);
        et_product_name.setText(product_name);
        pro_name.setText(product_name);
        et_pro_price.setText(""+product_price);
        pro_price.setText("₹ "+product_price);
        et_category.setText(product_category);
        et_stock.setText(product_stock);
        et_sell_by.setText(product_sell_by);
        et_barcode_ids.setText(product_id);
        et_description.setText(product_desc);

        toolbar_title.setText("Update Product");
        btn_product_created.setText("Update Product");

        new_product_scan.setEnabled(false);
        img_gallery.setEnabled(false);

    }
}
