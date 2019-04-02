package com.headrun.pos.ui.pages.scan_barcode;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import spencerstudios.com.bungeelib.Bungee;

public class ScanBarcodeActivity extends BaseActivity<ScanBarcodePresenter> implements ScanBarcodeView {

    @BindView(R.id.toolbar_scan) Toolbar toolbar;
    @BindView(R.id.camera_preview) SurfaceView cameraPreview;
    @BindView(R.id.lottie_barcode) LottieAnimationView lottie;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;

    int count = 0;
    Boolean from_new_product;

    @NonNull
    @Override
    protected ScanBarcodePresenter createPresenter() {
        return new ScanBarcodePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        init();

    }

    private void init(){

        Intent extras = getIntent();
        Bundle bundle = extras.getExtras();
        if(bundle == null){
            ToastUtils.showLong("Some problem occurs.");
            finish();
            return;
        }

        from_new_product = bundle.getBoolean("from_new_product");

        lottie.setAnimation("barcode_scanner.json");
        lottie.loop(true);
        lottie.playAnimation();

        presenter.initializeBarcode();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {
                    if (ActivityCompat.checkSelfPermission(ScanBarcodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0){
                    if (count == 0) {
                        LogUtils.e(barcodes.valueAt(0).rawValue);

                        String barcode_id = barcodes.valueAt(0).rawValue;

                        if (from_new_product) {
                            count++;
                            Intent intent = new Intent();
                            intent.putExtra("barcode", barcode_id);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                            Bungee.slideRight(ScanBarcodeActivity.this);
                        }else {
                            count++;
                            presenter.checkProducts(barcode_id);
                        }
                    }
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ifTaskEmpty() {
        ToastUtils.showLong("Product id does not match");
        finish();
    }

    @Override
    public void ifTaskNotEmpty(String product_id) {

        Intent intent = new Intent();
        intent.putExtra("barcode", product_id);
        setResult(Activity.RESULT_OK, intent);
        finish();
        Bungee.slideRight(ScanBarcodeActivity.this);

    }

    @Override
    public void initializeBarcode() {

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS | Barcode.EAN_13 | Barcode.EAN_8 | Barcode.UPC_A | Barcode.UPC_E | Barcode.CODE_39 | Barcode.CODE_93 |
                Barcode.CODE_128 | Barcode.ITF | Barcode.CODABAR | Barcode.ISBN | Barcode.QR_CODE | Barcode.DATA_MATRIX | Barcode.PDF417 | Barcode.AZTEC).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(30.0f) //30.0f
                .build();
    }
}
