// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckoutAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CheckoutAdapter.ViewHolder target;

  @UiThread
  public CheckoutAdapter$ViewHolder_ViewBinding(CheckoutAdapter.ViewHolder target, View source) {
    this.target = target;

    target.product_card = Utils.findRequiredViewAsType(source, R.id.product_card, "field 'product_card'", CardView.class);
    target.product_img = Utils.findRequiredViewAsType(source, R.id.product_img, "field 'product_img'", ImageView.class);
    target.product_name = Utils.findRequiredViewAsType(source, R.id.product_name, "field 'product_name'", TextView.class);
    target.blw_product_name = Utils.findRequiredViewAsType(source, R.id.blw_product_name, "field 'blw_product_name'", TextView.class);
    target.product_price = Utils.findRequiredViewAsType(source, R.id.product_price, "field 'product_price'", TextView.class);
    target.selected_item = Utils.findRequiredViewAsType(source, R.id.selected_item, "field 'selected_item'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckoutAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.product_card = null;
    target.product_img = null;
    target.product_name = null;
    target.blw_product_name = null;
    target.product_price = null;
    target.selected_item = null;
  }
}
