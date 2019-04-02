// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.main;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.headrun.pos.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296473;

  private View view2131296533;

  private View view2131296432;

  private View view2131296495;

  private View view2131296591;

  private View view2131296383;

  private View view2131296603;

  private View view2131296285;

  private View view2131296284;

  private View view2131296514;

  private View view2131296364;

  private View view2131296325;

  private View view2131296293;

  private View view2131296292;

  private View view2131296595;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.rv_left = Utils.findRequiredViewAsType(source, R.id.rv_left, "field 'rv_left'", RecyclerView.class);
    target.rv_right = Utils.findRequiredViewAsType(source, R.id.rv_checkout_items, "field 'rv_right'", RecyclerView.class);
    target.rv_cat_left = Utils.findRequiredViewAsType(source, R.id.rv_cat_left, "field 'rv_cat_left'", RecyclerView.class);
    target.text_total_items = Utils.findRequiredViewAsType(source, R.id.text_total_items, "field 'text_total_items'", TextView.class);
    target.text_total_price = Utils.findRequiredViewAsType(source, R.id.text_total_price, "field 'text_total_price'", TextView.class);
    target.grand_total = Utils.findRequiredViewAsType(source, R.id.grand_total, "field 'grand_total'", TextView.class);
    target.cgst_text = Utils.findRequiredViewAsType(source, R.id.cgst_text, "field 'cgst_text'", TextView.class);
    target.sgst_text = Utils.findRequiredViewAsType(source, R.id.sgst_text, "field 'sgst_text'", TextView.class);
    target.bg = Utils.findRequiredViewAsType(source, R.id.bg, "field 'bg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.grid_img, "field 'grid_img' and method 'onGridImg'");
    target.grid_img = Utils.castView(view, R.id.grid_img, "field 'grid_img'", ImageView.class);
    view2131296473 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGridImg();
      }
    });
    view = Utils.findRequiredView(source, R.id.list_img, "field 'list_img' and method 'onListImg'");
    target.list_img = Utils.castView(view, R.id.list_img, "field 'list_img'", ImageView.class);
    view2131296533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onListImg();
      }
    });
    view = Utils.findRequiredView(source, R.id.et_order_taken, "field 'et_order_taken' and method 'OrderTakenBy'");
    target.et_order_taken = Utils.castView(view, R.id.et_order_taken, "field 'et_order_taken'", EditText.class);
    view2131296432 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OrderTakenBy();
      }
    });
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_checkout, "field 'avi'", AVLoadingIndicatorView.class);
    target.sliding_cat_name = Utils.findRequiredViewAsType(source, R.id.sliding_cat_name, "field 'sliding_cat_name'", TextView.class);
    target.slidingUpPanelLayout = Utils.findRequiredViewAsType(source, R.id.sliding_upload_layout, "field 'slidingUpPanelLayout'", SlidingUpPanelLayout.class);
    target.discount_card = Utils.findRequiredViewAsType(source, R.id.discount_card, "field 'discount_card'", CardView.class);
    view = Utils.findRequiredView(source, R.id.img_disc_cross, "field 'img_disc_cross' and method 'onDiscountClose'");
    target.img_disc_cross = Utils.castView(view, R.id.img_disc_cross, "field 'img_disc_cross'", ImageView.class);
    view2131296495 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDiscountClose();
      }
    });
    target.et_disc_on = Utils.findRequiredViewAsType(source, R.id.et_disc_on, "field 'et_disc_on'", EditText.class);
    target.et_disc_amt = Utils.findRequiredViewAsType(source, R.id.et_disc_amt, "field 'et_disc_amt'", EditText.class);
    target.et_disc_perc = Utils.findRequiredViewAsType(source, R.id.et_disc_perc, "field 'et_disc_perc'", EditText.class);
    target.et_total_disc = Utils.findRequiredViewAsType(source, R.id.et_total_disc, "field 'et_total_disc'", EditText.class);
    target.txt_disc_amt = Utils.findRequiredViewAsType(source, R.id.txt_disc_amt, "field 'txt_disc_amt'", TextView.class);
    target.coupon_code = Utils.findRequiredViewAsType(source, R.id.coupon_code, "field 'coupon_code'", TextView.class);
    target.session_time = Utils.findRequiredViewAsType(source, R.id.session_time, "field 'session_time'", TextView.class);
    view = Utils.findRequiredView(source, R.id.parent, "field 'parent' and method 'onParentClicked'");
    target.parent = Utils.castView(view, R.id.parent, "field 'parent'", RelativeLayout.class);
    view2131296591 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onParentClicked();
      }
    });
    target.lottie = Utils.findRequiredViewAsType(source, R.id.lottie_ble, "field 'lottie'", LottieAnimationView.class);
    target.bill_no = Utils.findRequiredViewAsType(source, R.id.bill_no, "field 'bill_no'", TextView.class);
    target.order_tkn_by = Utils.findRequiredViewAsType(source, R.id.order_taken_by, "field 'order_tkn_by'", TextView.class);
    target.order_date = Utils.findRequiredViewAsType(source, R.id.order_date, "field 'order_date'", TextView.class);
    target.receipt_rv = Utils.findRequiredViewAsType(source, R.id.receipt_rv, "field 'receipt_rv'", RecyclerView.class);
    target.total_items = Utils.findRequiredViewAsType(source, R.id.total_items, "field 'total_items'", TextView.class);
    target.total_amt = Utils.findRequiredViewAsType(source, R.id.total_amt, "field 'total_amt'", TextView.class);
    target.disct_amt = Utils.findRequiredViewAsType(source, R.id.disct_amt, "field 'disct_amt'", TextView.class);
    target.grand_amt = Utils.findRequiredViewAsType(source, R.id.grand_amt, "field 'grand_amt'", TextView.class);
    target.sgst_txt = Utils.findRequiredViewAsType(source, R.id.sgst_txt, "field 'sgst_txt'", TextView.class);
    target.sgst_amt = Utils.findRequiredViewAsType(source, R.id.sgst_amt, "field 'sgst_amt'", TextView.class);
    target.cgst_txt = Utils.findRequiredViewAsType(source, R.id.cgst_txt, "field 'cgst_txt'", TextView.class);
    target.cgst_amt = Utils.findRequiredViewAsType(source, R.id.cgst_amt, "field 'cgst_amt'", TextView.class);
    target.receipt_form = Utils.findRequiredView(source, R.id.receipt_form, "field 'receipt_form'");
    view = Utils.findRequiredView(source, R.id.delete_order, "method 'onDeleteOrder'");
    view2131296383 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDeleteOrder();
      }
    });
    view = Utils.findRequiredView(source, R.id.print_receipt, "method 'onPrintReceipt'");
    view2131296603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPrintReceipt();
      }
    });
    view = Utils.findRequiredView(source, R.id.add_user_img, "method 'onAddUserClick'");
    view2131296285 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddUserClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.add_product, "method 'onAddProduct'");
    view2131296284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddProduct();
      }
    });
    view = Utils.findRequiredView(source, R.id.items_add_scan, "method 'onItemScanned'");
    view2131296514 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onItemScanned();
      }
    });
    view = Utils.findRequiredView(source, R.id.close_product, "method 'onClosingProduct'");
    view2131296364 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClosingProduct();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_disc_confirm, "method 'onDiscountConfirmed'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDiscountConfirmed();
      }
    });
    view = Utils.findRequiredView(source, R.id.apply_disc_rl, "method 'onApplyDiscount'");
    view2131296293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onApplyDiscount();
      }
    });
    view = Utils.findRequiredView(source, R.id.apply_coupon_rl, "method 'onApplyCoupon'");
    view2131296292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onApplyCoupon();
      }
    });
    view = Utils.findRequiredView(source, R.id.payment_button, "method 'onPaymentBtnClicked'");
    view2131296595 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPaymentBtnClicked();
      }
    });

    Context context = source.getContext();
    target.color = ContextCompat.getColor(context, R.color.colorAccent);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rv_left = null;
    target.rv_right = null;
    target.rv_cat_left = null;
    target.text_total_items = null;
    target.text_total_price = null;
    target.grand_total = null;
    target.cgst_text = null;
    target.sgst_text = null;
    target.bg = null;
    target.grid_img = null;
    target.list_img = null;
    target.et_order_taken = null;
    target.avi = null;
    target.sliding_cat_name = null;
    target.slidingUpPanelLayout = null;
    target.discount_card = null;
    target.img_disc_cross = null;
    target.et_disc_on = null;
    target.et_disc_amt = null;
    target.et_disc_perc = null;
    target.et_total_disc = null;
    target.txt_disc_amt = null;
    target.coupon_code = null;
    target.session_time = null;
    target.parent = null;
    target.lottie = null;
    target.bill_no = null;
    target.order_tkn_by = null;
    target.order_date = null;
    target.receipt_rv = null;
    target.total_items = null;
    target.total_amt = null;
    target.disct_amt = null;
    target.grand_amt = null;
    target.sgst_txt = null;
    target.sgst_amt = null;
    target.cgst_txt = null;
    target.cgst_amt = null;
    target.receipt_form = null;

    view2131296473.setOnClickListener(null);
    view2131296473 = null;
    view2131296533.setOnClickListener(null);
    view2131296533 = null;
    view2131296432.setOnClickListener(null);
    view2131296432 = null;
    view2131296495.setOnClickListener(null);
    view2131296495 = null;
    view2131296591.setOnClickListener(null);
    view2131296591 = null;
    view2131296383.setOnClickListener(null);
    view2131296383 = null;
    view2131296603.setOnClickListener(null);
    view2131296603 = null;
    view2131296285.setOnClickListener(null);
    view2131296285 = null;
    view2131296284.setOnClickListener(null);
    view2131296284 = null;
    view2131296514.setOnClickListener(null);
    view2131296514 = null;
    view2131296364.setOnClickListener(null);
    view2131296364 = null;
    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296293.setOnClickListener(null);
    view2131296293 = null;
    view2131296292.setOnClickListener(null);
    view2131296292 = null;
    view2131296595.setOnClickListener(null);
    view2131296595 = null;
  }
}
