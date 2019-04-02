// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.new_product;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewProduct_ViewBinding implements Unbinder {
  private NewProduct target;

  private View view2131296440;

  private View view2131296413;

  private View view2131296496;

  private View view2131296328;

  private View view2131296573;

  @UiThread
  public NewProduct_ViewBinding(NewProduct target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewProduct_ViewBinding(final NewProduct target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_new_product, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.et_sell_by, "field 'et_sell_by' and method 'onSellBy'");
    target.et_sell_by = Utils.castView(view, R.id.et_sell_by, "field 'et_sell_by'", EditText.class);
    view2131296440 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSellBy();
      }
    });
    view = Utils.findRequiredView(source, R.id.et_category, "field 'et_category' and method 'onCategorySelected'");
    target.et_category = Utils.castView(view, R.id.et_category, "field 'et_category'", EditText.class);
    view2131296413 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCategorySelected();
      }
    });
    target.et_product_name = Utils.findRequiredViewAsType(source, R.id.et_product_name, "field 'et_product_name'", EditText.class);
    target.et_pro_price = Utils.findRequiredViewAsType(source, R.id.et_pro_price, "field 'et_pro_price'", EditText.class);
    target.et_stock = Utils.findRequiredViewAsType(source, R.id.et_stock, "field 'et_stock'", EditText.class);
    target.et_description = Utils.findRequiredViewAsType(source, R.id.et_description, "field 'et_description'", EditText.class);
    target.product_image = Utils.findRequiredViewAsType(source, R.id.product_image, "field 'product_image'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_gallery, "field 'img_gallery' and method 'onProductImage'");
    target.img_gallery = Utils.castView(view, R.id.img_gallery, "field 'img_gallery'", ImageView.class);
    view2131296496 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onProductImage();
      }
    });
    target.pro_name = Utils.findRequiredViewAsType(source, R.id.product_name, "field 'pro_name'", TextView.class);
    target.pro_price = Utils.findRequiredViewAsType(source, R.id.product_price, "field 'pro_price'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_product_created, "field 'btn_product_created' and method 'onCreateProduct'");
    target.btn_product_created = Utils.castView(view, R.id.btn_product_created, "field 'btn_product_created'", Button.class);
    view2131296328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateProduct();
      }
    });
    target.et_barcode_ids = Utils.findRequiredViewAsType(source, R.id.et_barcode_ids, "field 'et_barcode_ids'", EditText.class);
    view = Utils.findRequiredView(source, R.id.new_product_scan, "field 'new_product_scan' and method 'onProductScanned'");
    target.new_product_scan = Utils.castView(view, R.id.new_product_scan, "field 'new_product_scan'", ImageView.class);
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onProductScanned();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NewProduct target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.toolbar_title = null;
    target.et_sell_by = null;
    target.et_category = null;
    target.et_product_name = null;
    target.et_pro_price = null;
    target.et_stock = null;
    target.et_description = null;
    target.product_image = null;
    target.img_gallery = null;
    target.pro_name = null;
    target.pro_price = null;
    target.btn_product_created = null;
    target.et_barcode_ids = null;
    target.new_product_scan = null;

    view2131296440.setOnClickListener(null);
    view2131296440 = null;
    view2131296413.setOnClickListener(null);
    view2131296413 = null;
    view2131296496.setOnClickListener(null);
    view2131296496 = null;
    view2131296328.setOnClickListener(null);
    view2131296328 = null;
    view2131296573.setOnClickListener(null);
    view2131296573 = null;
  }
}
