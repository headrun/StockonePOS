package com.headrun.pos.ui.pages.scan_barcode;

import com.headrun.pos.ui.pages.base.BaseView;

public interface ScanBarcodeView extends BaseView {

    void ifTaskEmpty();
    void ifTaskNotEmpty(String product_id);
    void initializeBarcode();
}
