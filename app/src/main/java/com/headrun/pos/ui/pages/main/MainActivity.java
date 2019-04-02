package com.headrun.pos.ui.pages.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.headrun.pos.R;
import com.headrun.pos.adapter.CategoryAdapter;
import com.headrun.pos.adapter.CheckoutAdapter;
import com.headrun.pos.adapter.SingleCheckoutAdapter;
import com.headrun.pos.callback.OnCategorySelected;
import com.headrun.pos.callback.OnCheckoutItemClicked;
import com.headrun.pos.callback.OnCouponClicked;
import com.headrun.pos.callback.OnItemClicked;
import com.headrun.pos.callback.OnReceipt;
import com.headrun.pos.callback.RecyclerItemClickListener;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.model.CheckoutModel;
import com.headrun.pos.model.Checksum;
import com.headrun.pos.model.Paytm;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.ReceiptModel;
import com.headrun.pos.networking.Api;
import com.headrun.pos.ui.pages.scan_barcode.ScanBarcodeActivity;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.ActivityManager;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.DbUtils;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.dialog.CouponDialog;
import com.headrun.pos.ui.dialog.CustomerDialog;
import com.headrun.pos.ui.dialog.ReceiptDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.Printable;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.pepperonas.materialdialog.MaterialDialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.wang.avi.AVLoadingIndicatorView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import io.palaima.smoothbluetooth.Device;
import io.palaima.smoothbluetooth.SmoothBluetooth;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClicked,
        OnCheckoutItemClicked, OnCategorySelected, OnCouponClicked, OnReceipt,
        PaytmPaymentTransactionCallback, MainView {

    @BindView(R.id.rv_left) RecyclerView rv_left;
    @BindView(R.id.rv_checkout_items) RecyclerView rv_right;
    @BindView(R.id.rv_cat_left) RecyclerView rv_cat_left;
    @BindView(R.id.text_total_items) TextView text_total_items;
    @BindView(R.id.text_total_price) TextView text_total_price;
    @BindView(R.id.grand_total) TextView grand_total;
    @BindView(R.id.cgst_text) TextView cgst_text;
    @BindView(R.id.sgst_text) TextView sgst_text;
    @BindView(R.id.bg) ImageView bg;
    @BindView(R.id.grid_img) ImageView grid_img;
    @BindView(R.id.list_img) ImageView list_img;
    @BindView(R.id.et_order_taken) EditText et_order_taken;
    @BindView(R.id.avi_checkout) AVLoadingIndicatorView avi;
    @BindView(R.id.sliding_cat_name) TextView sliding_cat_name;
    @BindView(R.id.sliding_upload_layout) SlidingUpPanelLayout slidingUpPanelLayout;
    @BindView(R.id.discount_card) CardView discount_card;
    @BindView(R.id.img_disc_cross) ImageView img_disc_cross;
    @BindView(R.id.et_disc_on) EditText et_disc_on;
    @BindView(R.id.et_disc_amt) EditText et_disc_amt;
    @BindView(R.id.et_disc_perc) EditText et_disc_perc;
    @BindView(R.id.et_total_disc) EditText et_total_disc;
    @BindView(R.id.txt_disc_amt) TextView txt_disc_amt;
    @BindView(R.id.coupon_code) TextView coupon_code;
    @BindView(R.id.session_time) TextView session_time;
    @BindView(R.id.parent) RelativeLayout parent;
    @BindView(R.id.lottie_ble) LottieAnimationView lottie;
    @BindColor(R.color.colorAccent) int color;

    //Receipt views
    @BindView(R.id.bill_no) TextView bill_no;
    @BindView(R.id.order_taken_by) TextView order_tkn_by;
    @BindView(R.id.order_date) TextView order_date;
    @BindView(R.id.receipt_rv) RecyclerView receipt_rv;
    @BindView(R.id.total_items) TextView total_items;
    @BindView(R.id.total_amt) TextView total_amt;
    @BindView(R.id.disct_amt) TextView disct_amt;
    @BindView(R.id.grand_amt) TextView grand_amt;
    @BindView(R.id.sgst_txt) TextView sgst_txt;
    @BindView(R.id.sgst_amt) TextView sgst_amt;
    @BindView(R.id.cgst_txt) TextView cgst_txt;
    @BindView(R.id.cgst_amt) TextView cgst_amt;
    @BindView(R.id.receipt_form) View receipt_form;


    Map<String, Object> map = new LinkedHashMap<>();
    Map<String, Object> transactionMap = new LinkedHashMap<>();

    List<ProductModel> checkoutModelList;
    List<ProductModel> productModelList = new ArrayList<>();
    List<Device> mBTDevices = new ArrayList<>();

    ArrayList<String> list = new ArrayList<>();

    CheckoutModel checkoutModel = new CheckoutModel();
    ReceiptModel receiptModel = new ReceiptModel();

    // android built in classes for bluetooth operations
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mmDevice;

    SpinnerDialog orderDialog;
    ReceiptDialog receiptDialog;
    CheckoutAdapter checkoutAdapter;
    CategoryAdapter categoryAdapter;
    com.headrun.pos.adapter.BluetoothAdapter bluetoothAdapter;
    SingleCheckoutAdapter singleCheckoutAdapter;
    RecyclerView rv_bluetooth;
    MaterialDialog dialog;
    Thread thread;

    public static String order_taken_by;
    public static String dialog_receipt_amt;
    public static String dialog_disc_amt;
    public static final int ENABLE_BT__REQUEST = 1;
    private SmoothBluetooth mSmoothBluetooth;

    int mSum;
    int item;
    boolean from_amt = false;
    double sum = 0.0f;
    double toatl;
    double discnt_on = 0.0f;
    double receipt_price = 0.0f;
    double receipt_cgst, receipt_sgst = 0.0f;

    String category_name;
    String owner_name;
    String device_name, device_address;

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        receiptDialog = new ReceiptDialog(this);

        init();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // get menu from navigationView
        Menu menu = navigationView.getMenu();

        // find MenuItem you want to change
        MenuItem nav_settings = menu.findItem(R.id.nav_settings);

        categoryAdapter = new CategoryAdapter(this, this);
        rv_cat_left.setLayoutManager(new GridLayoutManager(this, 4));
        rv_cat_left.setAdapter(categoryAdapter);

        singleCheckoutAdapter = new SingleCheckoutAdapter(this,this, this);

        mSmoothBluetooth = new SmoothBluetooth(this);

        mSmoothBluetooth.setListener(mListener);


        bluetoothAdapter = new com.headrun.pos.adapter.BluetoothAdapter(MainActivity.this);


    }


    private void init(){

        presenter.getUser();

        lottie.setAnimation("ble.json");
        lottie.loop(true);
        lottie.playAnimation();

        list.add("JOXO");
        list.add("LOLO");
        list.add("POLO");
        list.add("SOLO");

        orderDialog = new SpinnerDialog(this, list, "Select or Search user", R.style.DialogAnimations_SmileWindow);

        orderDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {

                et_order_taken.setText(s);
                order_taken_by = s;

            }
        });

        //BG Image processing
        Glide.with(this).load(R.drawable.bg).into(bg);

        checkoutAdapter = new CheckoutAdapter(this,this);

        // Sliding up panel

        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, final SlidingUpPanelLayout.PanelState newState) {

                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {


                }
                else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {


                }
            }
        });

        // Listener for discount amt
        et_disc_amt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (from_amt) {

                    double amt;
                    double discount_on;
                    String disc_ = General.removeChar(grand_total.getText().toString().trim());

                    try {

                        discount_on = discnt_on;
                        amt = Double.parseDouble(s.toString());
                        double total_disc = discount_on - amt;
                        double percentage = amt * 100 / discount_on;
                        et_disc_perc.setText(String.format("%.2f", percentage));
                        et_total_disc.setText(String.format("%.2f", total_disc));


                    } catch (NumberFormatException e) {

                        LogUtils.e(e.getMessage());
                    }
                }

            }
        });

        // Listener for percentage
        et_disc_perc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!from_amt) {

                    double percentage;
                    double discount_on;

                    try {

                        discount_on = discnt_on;
                        percentage = Double.parseDouble(s.toString());
                        double amt = percentage * discount_on / 100;
                        double total_disc = discount_on - amt;
                        et_disc_amt.setText(String.format("%.2f", amt));
                        et_total_disc.setText(String.format("%.2f", total_disc));


                    } catch (NumberFormatException e) {

                        LogUtils.e(e.getMessage());
                    }
                }
            }
        });

        et_disc_amt.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                from_amt = false;
            }else {
                from_amt = true;
            }
        });

    }

    private SmoothBluetooth.Listener mListener = new SmoothBluetooth.Listener() {
        @Override
        public void onBluetoothNotSupported() {
            Toast.makeText(MainActivity.this, "Bluetooth not found", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBluetoothNotEnabled() {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, ENABLE_BT__REQUEST);
        }

        @Override
        public void onConnecting(Device device) {
            LogUtils.e(device.getName());
            device_name = device.getName();
            device_address = device.getAddress();
            mSmoothBluetooth.tryConnection();
            dialog.dismiss();

            if (device.isPaired()){
                mSmoothBluetooth.tryConnection();
                dialog.dismiss();
            }
        }

        @Override
        public void onConnected(Device device) {

                DbUtils.storeDeviceName(device_name);
                DbUtils.storeDeviceAddress(device_address);
                LogUtils.e("Connected");

                new Handler().postDelayed(() -> {
                    try {
                        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                        final Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                        for (BluetoothDevice device1 : pairedDevices) {

                            LogUtils.e(DbUtils.getDeviceName());
                            // RPP300 is the name of the bluetooth printer device
                            // we got this name from the list of paired devices
                            if (device1.getName().equals(DbUtils.getDeviceName())) {
                                mmDevice = device1;
                                Printooth.INSTANCE.setPrinter(DbUtils.getDeviceName(), DbUtils.getDeviceAddress());
                                sendData();
                                break;
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 2000);
            }

        @Override
        public void onDisconnected() {

            lottie.setVisibility(View.GONE);
        }

        @Override
        public void onConnectionFailed(Device device) {

            if (device != null) {
                if (device.isPaired()) {
                    mSmoothBluetooth.doDiscovery();
                }
            }
        }

        @Override
        public void onDiscoveryStarted() {
            Toast.makeText(MainActivity.this, "Searching", Toast.LENGTH_SHORT).show();
            lottie.setVisibility(View.VISIBLE);
        }

        @Override
        public void onDiscoveryFinished() {

            lottie.setVisibility(View.GONE);
        }

        @Override
        public void onNoDevicesFound() {
            Toast.makeText(MainActivity.this, "No device found", Toast.LENGTH_SHORT).show();

            lottie.setVisibility(View.GONE);
        }

        @Override
        public void onDevicesFound(final List<Device> deviceList,
                                   final SmoothBluetooth.ConnectionCallback connectionCallback) {

            if (deviceList != null){

                LogUtils.e(deviceList.size());

                bluetoothAdapter.setList(deviceList);
                presenter.getSearchableDevices(MainActivity.this);

                rv_bluetooth.addOnItemTouchListener(
                        new RecyclerItemClickListener(MainActivity.this, rv_bluetooth ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                connectionCallback.connectTo(deviceList.get(position));
                                dialog.dismiss();
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

                if (deviceList.get(0).isPaired()) {
                    mSmoothBluetooth.doDiscovery();
                    dialog.dismiss();
                }else {
                    dialog.show();
                }
            }

        }

        @Override
        public void onDataReceived(int data) {

            LogUtils.e(data);

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        thread.interrupt();

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getAllCategories();

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(() -> {
                            if (DbUtils.getUserSessionTime() != 0){
                                long runningTime = System.currentTimeMillis() - DbUtils.getUserSessionTime();
                                int hours = (int) (runningTime/(1000 * 60 * 60));
                                int mins = (int) ((runningTime/(1000*60)) % 60);
                                long Secs = (runningTime / 1000) % 60;
                                String diff = hours + ":" + mins + ":" + Secs; // updated value every1 second
                                session_time.setText(diff);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSmoothBluetooth.stop();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_checkout) {

            ActivityManager.HOME(this);

        } else if (id == R.id.nav_products) {

            ActivityManager.PRODUCTS(this);

        } else if (id == R.id.nav_transaction) {

            ActivityManager.TRANSACTION(this);

        } else if (id == R.id.nav_logout) {

            FirebaseAuth.getInstance().signOut();
            ActivityManager.LOGIN(this);
            finish();

        } else if (id == R.id.nav_settings){


            ActivityManager.SETTINGS(this);
        } else if (id == R.id.nav_scratch_card){

            ActivityManager.SCRATCH_CARD(this);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemName(Map<String, Object> map) {

        this.map = map;

        checkoutModelList = new ArrayList<>();
        checkoutModelList.clear();
//        singleTransactionModelList = new ArrayList<>();
//        singleTransactionModelList.clear();
        item = 0;

        sum = 0.0f;

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            ProductModel productModel = (ProductModel) entry.getValue();

            sum += productModel.getProduct_price() * productModel.getQuantity();

            item +=productModel.getQuantity();

            checkoutModelList.add(new ProductModel(productModel.getProduct_name(),productModel.getProduct_price(), productModel.getQuantity()));
//            singleTransactionModelList.add(new ProductModel(productModel.getProduct_name(),productModel.getProduct_price(), productModel.getQuantity()));

        }

        toatl = sum;
        text_total_items.setText(""+item);
        mSum = 0;


        double cgst = 9 * sum / 100;
        double sgst = 9 * sum / 100;
        double total = sum + cgst + sgst;

        sgst_text.setText("₹ "+sgst);
        cgst_text.setText("₹ "+cgst);

        receipt_cgst = cgst;
        receipt_sgst = sgst;

        text_total_price.setText("₹ "+String.format("%.2f", sum));

        receipt_price = Double.parseDouble(String.format("%.2f", sum));

        grand_total.setText("Total: "+"₹ "+String.format("%.2f", total));

        dialog_receipt_amt = "₹ "+String.format("%.2f", total);

        discnt_on = Double.parseDouble(String.format("%.2f", total));

        rv_right.setLayoutManager(new LinearLayoutManager(this));
        singleCheckoutAdapter.setList(checkoutModelList);
        rv_right.setAdapter(singleCheckoutAdapter);
//        singleCheckoutAdapter.setModelList(singleTransactionModelList);

        receiptModel.setTotal_quant(map.size());
        receiptModel.setGrand_total(total);
        receiptModel.setTotal_price(sum);

    }

    @Override
    public void onProductIncDec(Map<String, Integer> map) {

        mSum = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            mSum += entry.getValue();
        }
        text_total_items.setText(""+mSum);
        DbUtils.storeItemTotal(mSum);
    }

    @Override
    public void onTotalPrice(double price) {

        toatl = sum + price;
        text_total_price.setText("₹ "+toatl);
        receipt_price = toatl;
        double cgst = 9 * toatl / 100;
        double sgst = 9 * toatl/ 100;
        double total = toatl + cgst + sgst;
        sgst_text.setText("₹ "+sgst);
        cgst_text.setText("₹ "+cgst);
        receipt_sgst = sgst;
        receipt_cgst = cgst;
        grand_total.setText("Total: "+"₹ "+String.format("%.2f", total));

        dialog_receipt_amt = "₹ "+String.format("%.2f", total);

        discnt_on = Double.parseDouble(String.format("%.2f", total));

        DbUtils.storeTotalPrice(toatl);
        DbUtils.storeGrandTotal(total);

    }

    @Override
    public void onDelete(double price, int quantity) {

        toatl -= price;
        text_total_price.setText("₹ "+toatl);
        receipt_price = toatl;
        if (mSum == 0){
            mSum = map.size() - quantity;
        }else {
            mSum -=quantity;
        }

        double cgst = 9 * toatl / 100;
        double sgst = 9 * toatl/ 100;
        sgst_text.setText("₹ "+sgst);
        cgst_text.setText("₹ "+cgst);
        receipt_cgst = cgst;
        receipt_sgst = sgst;
        double total = toatl + cgst + sgst;
        grand_total.setText("Total: "+"₹ "+String.format("%.2f", total));
        dialog_receipt_amt = "₹ "+String.format("%.2f", total);
        discnt_on = Double.parseDouble(String.format("%.2f", total));
        text_total_items.setText(""+mSum);
        checkoutModel.setQuantity(0);

        DbUtils.storeItemTotal(mSum);
        DbUtils.storeTotalPrice(toatl);
        DbUtils.storeGrandTotal(total);
    }

    @OnClick(R.id.delete_order)
    void onDeleteOrder(){

        this.map.clear();
        text_total_price.setText("₹ "+0.00);
        receipt_price = 0.0f;
        text_total_items.setText("0");
        sgst_text.setText("₹ "+0.00);
        cgst_text.setText("₹ "+0.00);
        receipt_sgst = 0.0f;
        receipt_cgst = 0.0f;
        grand_total.setText("");
        dialog_receipt_amt = "₹ "+0.00;
        discnt_on = 0.0f;
        txt_disc_amt.setText("₹ "+0.00);
        et_disc_amt.getText().clear();
        et_disc_perc.getText().clear();
        et_total_disc.getText().clear();
        coupon_code.setVisibility(View.GONE);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

        if (checkoutModelList != null) {
            checkoutModelList.clear();
            singleCheckoutAdapter.setList(null);

        }
    }

    @Override
    public void onItemTransaction(Map<String, Object> map) {

        this.transactionMap = map;
    }

    @OnClick(R.id.print_receipt)
    void onPrintReceipt(){

        if (grand_total.getText().toString().isEmpty()){

            ToastUtils.showLong("Please select product first to print.");
            return;
        }

        if (mSmoothBluetooth.isBluetoothEnabled()) {

            try {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                if (DbUtils.getDeviceName() == null){
                    try {
                        mSmoothBluetooth.doDiscovery();
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }
                    return;
                }

                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice device1 : pairedDevices) {

                        LogUtils.e(DbUtils.getDeviceName());

                        ToastUtils.showLong("Connected to: "+DbUtils.getDeviceName());

                        // RPP300 is the name of the bluetooth printer device
                        // we got this name from the list of paired devices
                        if (device1.getName().equals(DbUtils.getDeviceName())) {
                            mmDevice = device1;
                            Printooth.INSTANCE.setPrinter(DbUtils.getDeviceName(), DbUtils.getDeviceAddress());
                            sendData();
                            break;
                        }
                    }
                } else {

                    try {
                        mSmoothBluetooth.doDiscovery();
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

            try {
                mSmoothBluetooth.doDiscovery();
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }

    }

    // this will send text data to be printed by the bluetooth printer
    void sendData() throws IOException {

        String et_dis_amt = et_disc_amt.getText().toString();

        if (et_dis_amt.isEmpty()){
            et_dis_amt = "0.0";
        }

        double receipt_disc_price = discnt_on - Double.parseDouble(et_dis_amt);


        String BILL = "";

        BILL = "               MIEONE TECHNOLOGIES    \n"
                + "                 S.T Bed Layout     \n" +
                "               Kormangala, Bengaluru    \n" +
                "                Karnataka - 560034      \n" +
                "                  MMM 590019091      \n";
        BILL = BILL
                + "-----------------------------------------------\n";

        BILL = BILL + " Bill no: 5BEG74 \n" +
                " Order Taken By: "+owner_name+ "\n" +
                " Order Date: "  +General.getDateFormat(System.currentTimeMillis(), "dd/MM/yyyy")+    "\n";
        BILL = BILL
                + "-----------------------------------------------\n";

        BILL = BILL + String.format("%1$-10s %2$10s %3$13s %4$10s", "Item", "Qty", "Rate", "Total");
        BILL = BILL + "\n";
        BILL = BILL
                + "-----------------------------------------------";
        for (int i =0; i< SingleCheckoutAdapter.list.size(); i++){

            BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", SingleCheckoutAdapter.list.get(i).getProduct_name(), SingleCheckoutAdapter.list.get(i).getQuantity(), SingleCheckoutAdapter.list.get(i).getProduct_price(), SingleCheckoutAdapter.list.get(i).getQuantity() * SingleCheckoutAdapter.list.get(i).getProduct_price());
        }
//        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-002", "10", "5", "50.00");
//        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-003", "20", "10", "200.00");
//        BILL = BILL + "\n " + String.format("%1$-10s %2$10s %3$11s %4$10s", "item-004", "50", "10", "500.00");

        BILL = BILL
                + "\n-----------------------------------------------";
        BILL = BILL + "\n";

        BILL = BILL + " Total items: "+text_total_items.getText().toString()+"                    Amt:"+receipt_price+" \n" +
                " Discount: "+et_dis_amt+" \n";

        BILL = BILL + "                Total:"+receipt_disc_price+"                    \n";

        BILL = BILL
                + "-----------------------------------------------\n";

        BILL = BILL + "              SGST@9% of "+receipt_price+":" + "      " + receipt_sgst + "\n";
        BILL = BILL + "              CGST@9% of "+receipt_price+":" + "      " + receipt_cgst + "\n";

        BILL = BILL
                + "-----------------------------------------------\n";
        BILL = BILL + "            !!!THANK YOU VISIT AGAIN !!!       " + "\n";

        BILL = BILL + "\n\n ";

//        printText(BILL.getBytes());

        ArrayList <Printable> printables = new ArrayList<Printable>();
        Printable printable = new Printable.PrintableBuilder()
                .setText(BILL) //The text you want to print
                .setNewLinesAfter(1) // To provide n lines after sentence
                .build();
        printables.add(printable);
        Printooth.INSTANCE.printer().print(printables);

//            try {
//                LogUtils.e(ReceiptDialog.bitmap);
//                if(ReceiptDialog.bitmap !=null){
//                    Bitmap resized = Bitmap.createScaledBitmap(ReceiptDialog.bitmap,(int)(ReceiptDialog.bitmap.getWidth()*0.6),
//                            (int)(ReceiptDialog.bitmap.getHeight() * 0.8), true);
//                    byte[] command = General.decodeBitmap(resized);
//                    LogUtils.e(command);
//                    mmOutputStream.write(PrinterCommands.ESC_ALIGN_CENTER);
//                    mmOutputStream.write(PrinterCommands.SELECT_FONT_A);
//                    printText(command);
//                }else{
//                    LogUtils.e("Print Photo error", "the file isn't exists");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtils.e("PrintTools", "the file isn't exists");
//            }

//            LogUtils.e("Printing....");
//
//            for (int i=0; i<=20; i++){
//
//                String msg = "StockONe POS";
//                msg += "\n";
//
//                mmOutputStream.write(msg.getBytes());
//            }

            // tell the user data were sent

    }

    @OnClick(R.id.add_user_img)
    void onAddUserClick(){
        CustomerDialog customerDialog = new CustomerDialog(this);
        customerDialog.show();
    }

    @OnClick(R.id.et_order_taken)
    void OrderTakenBy(){

        orderDialog.showSpinerDialog();
    }

    @OnClick(R.id.add_product)
    void onAddProduct(){

        ActivityManager.NEW_PRODUCTS(this, null, null, 0, null,
                null,null, null, null, false);
    }

    @OnClick(R.id.items_add_scan)
    void onItemScanned(){

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            Intent intent = new Intent(MainActivity.this, ScanBarcodeActivity.class);
                            intent.putExtra("from_new_product", false);
                            startActivityForResult(intent, 121);
                        }else {
                        }
                        if(report.isAnyPermissionPermanentlyDenied()){
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 121){
            if (data != null) {
                String result = data.getStringExtra("barcode");
                presenter.isScanned = true;
                presenter.getProductsByScan(result);
            }
        }

        if(requestCode == ENABLE_BT__REQUEST) {
            if(resultCode == RESULT_OK) {
                mSmoothBluetooth.tryConnection();
            }
        }
    }

    @Override
    public void categoryName(String cat_name) {

        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        slidingUpPanelLayout.setTouchEnabled(false);
        presenter.getProductsCategories(cat_name, 0);
        LogUtils.e(cat_name);
        category_name = cat_name;
        sliding_cat_name.setText(cat_name);
    }

    @OnClick(R.id.close_product)
    void onClosingProduct(){

        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @OnClick(R.id.grid_img)
    void onGridImg(){

        avi.show();
        grid_img.setVisibility(View.GONE);
        list_img.setVisibility(View.VISIBLE);
        presenter.getProductsCategories(category_name, 0);
        sliding_cat_name.setText(category_name);

    }

    @OnClick(R.id.list_img)
    void onListImg(){

        avi.show();

        grid_img.setVisibility(View.VISIBLE);
        list_img.setVisibility(View.GONE);
        presenter.getProductsCategories(category_name, 1);
        sliding_cat_name.setText(category_name);

    }

    @OnClick(R.id.btn_disc_confirm)
    void onDiscountConfirmed(){

        String dis = et_disc_amt.getText().toString();

        if (TextUtils.isEmpty(dis)){
            ToastUtils.showLong("Please enter fields");

            return;
        }

        if (Double.parseDouble(et_disc_amt.getText().toString()) > Double.parseDouble(et_disc_on.getText().toString())){

            ToastUtils.showLong("Discount amount should be less then total amount");

            return;

        }

        txt_disc_amt.setText("₹ "+et_disc_amt.getText().toString());
        grand_total.setText("Total: ₹ "+et_total_disc.getText().toString());
        dialog_receipt_amt = "₹ "+et_total_disc.getText().toString();
        dialog_disc_amt = "₹ "+et_disc_amt.getText().toString();
        discount_card.setVisibility(View.GONE);

        LogUtils.e(discnt_on);

//        discnt_on = Double.parseDouble(et_total_disc.getText().toString());

    }

    @OnClick(R.id.img_disc_cross)
    void onDiscountClose(){

        discount_card.setVisibility(View.GONE);
    }

    @OnClick(R.id.apply_disc_rl)
    void onApplyDiscount(){

        if (grand_total.getText().toString().isEmpty()){

            ToastUtils.showLong("Please select product first");
            return;
        }

        discount_card.setVisibility(View.VISIBLE);

        et_disc_on.setText(""+discnt_on);

    }

    @OnClick(R.id.apply_coupon_rl)
    void onApplyCoupon(){

        if (discnt_on < 5000){

            ToastUtils.showLong("You have to make minimum purchase of ₹5000 to avail the coupon code.");

            return;
        }

        CouponDialog couponDialog = new CouponDialog(this, this);
        couponDialog.show();

    }

    @Override
    public void onCouponPrice(double price, String coupon_id) {

        String et_dis_amt = et_disc_amt.getText().toString();

        if (et_dis_amt.isEmpty()){
            et_dis_amt = "0.0";
        }

        double prc = discnt_on - Double.parseDouble(et_dis_amt);

        double after_coupon = prc - price;

        LogUtils.e("Discount On ->"+discnt_on);
        LogUtils.e("Price ->"+ price);
        LogUtils.e("After coupon ->"+after_coupon);

        double d_price = discnt_on - price;

        discnt_on = d_price;

        grand_total.setText("Total: "+"₹ "+String.format("%.2f", after_coupon));

        dialog_receipt_amt = "₹ "+String.format("%.2f", after_coupon);

        coupon_code.setVisibility(View.VISIBLE);

        coupon_code.setText(coupon_id);
        coupon_code.setPaintFlags(coupon_code.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    public void onTotalItemsPrice(List<Double> list) {

    }

    @OnClick(R.id.parent)
    void onParentClicked(){

        receipt_form.setVisibility(View.GONE);
        parent.setVisibility(View.GONE);
    }


    @OnClick(R.id.payment_button)
    void onPaymentBtnClicked(){

        //creating a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the retrofit api service
        Api apiService = retrofit.create(Api.class);

        //creating paytm object
        //containing all the values required
        final Paytm paytm = new Paytm(
                Constants.M_ID,
                Constants.CHANNEL_ID,
                "100.12",
                Constants.WEBSITE,
                "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=",
                Constants.INDUSTRY_TYPE_ID
        );

        //creating a call object from the apiService
        Call<Checksum> call = apiService.getChecksum(
                paytm.getmId(),
                paytm.getOrderId(),
                paytm.getCustId(),
                paytm.getChannelId(),
                paytm.getTxnAmount(),
                paytm.getWebsite(),
                paytm.getCallBackUrl()+paytm.getOrderId(),
                paytm.getIndustryTypeId()
        );

        //making the call to generate checksum
        call.enqueue(new Callback<Checksum>() {
            @Override
            public void onResponse(Call<Checksum> call, Response<Checksum> response) {

                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                initializePaytmPayment(response.body().getChecksumHash(), paytm);
            }

            @Override
            public void onFailure(Call<Checksum> call, Throwable t) {

            }
        });
    }


    private void initializePaytmPayment(String checksumHash, Paytm paytm) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getStagingService();

        //use this when using for production
        //PaytmPGService Service = PaytmPGService.getProductionService();

        //creating a hashmap and adding all the values required
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", Constants.M_ID);
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCustId());
        paramMap.put("CHANNEL_ID", paytm.getChannelId());
        paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
        paramMap.put("WEBSITE", paytm.getWebsite());
        paramMap.put("CALLBACK_URL", paytm.getCallBackUrl()+paytm.getOrderId());
        paramMap.put("CHECKSUMHASH", checksumHash);
        paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());

        LogUtils.e("Callback URL -> "+paytm.getCallBackUrl()+paytm.getOrderId());


        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {

        LogUtils.e(inResponse.toString());
    }

    @Override
    public void networkNotAvailable() {

        LogUtils.e("Network not available");
    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {

        LogUtils.e(inErrorMessage);

    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {

        LogUtils.e(inErrorMessage);
    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

        LogUtils.e(iniErrorCode+" "+inErrorMessage);

    }

    @Override
    public void onBackPressedCancelTransaction() {

    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

        LogUtils.e(inErrorMessage);
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
    }

    @Override
    public void showCategories(List<CategoryModel> list) {

        categoryAdapter.setList(list);

    }

    @Override
    public void showProducts(List<ProductModel> list) {

        checkoutAdapter.setList(list);

    }

    @Override
    public void showProductsInGrid() {

        rv_left.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        rv_left.setAdapter(checkoutAdapter);
    }

    @Override
    public void showProductsInLinear() {

        rv_left.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_left.setAdapter(checkoutAdapter);
    }

    @Override
    public void setViewForBle(View view) {

        rv_bluetooth = view.findViewById(R.id.rv_bluetooth);
        rv_bluetooth.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_bluetooth.setAdapter(bluetoothAdapter);

        dialog = new MaterialDialog.Builder(this)
                .title("Select Device")
                .customView(view)
                .showListener(new MaterialDialog.ShowListener() {
                    @Override
                    public void onShow(AlertDialog dialog) {
                        super.onShow(dialog);
                    }
                }).build();
    }

    @Override
    public Map<String, Object> transactionMap() {
        return transactionMap;
    }

    @Override
    public void ownerName(String ownerName) {

        owner_name = ownerName;
    }

    @Override
    public void orderTakenBy(String orderTakenBy) {


        order_tkn_by.setText(orderTakenBy);
    }


}
