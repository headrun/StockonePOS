// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StockAdapter$ViewHolder_ViewBinding implements Unbinder {
  private StockAdapter.ViewHolder target;

  @UiThread
  public StockAdapter$ViewHolder_ViewBinding(StockAdapter.ViewHolder target, View source) {
    this.target = target;

    target.stock_img = Utils.findRequiredViewAsType(source, R.id.stock_img, "field 'stock_img'", ImageView.class);
    target.img_stock_name = Utils.findRequiredViewAsType(source, R.id.img_stock_name, "field 'img_stock_name'", TextView.class);
    target.stock_product_name = Utils.findRequiredViewAsType(source, R.id.stock_product_name, "field 'stock_product_name'", TextView.class);
    target.stock_no = Utils.findRequiredViewAsType(source, R.id.stock_no, "field 'stock_no'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StockAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stock_img = null;
    target.img_stock_name = null;
    target.stock_product_name = null;
    target.stock_no = null;
  }
}
