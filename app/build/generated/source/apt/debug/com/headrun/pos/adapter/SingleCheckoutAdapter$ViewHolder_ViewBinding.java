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

public class SingleCheckoutAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SingleCheckoutAdapter.ViewHolder target;

  @UiThread
  public SingleCheckoutAdapter$ViewHolder_ViewBinding(SingleCheckoutAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.no_items = Utils.findRequiredViewAsType(source, R.id.no_items, "field 'no_items'", TextView.class);
    target.product_name = Utils.findRequiredViewAsType(source, R.id.product_name, "field 'product_name'", TextView.class);
    target.text_price = Utils.findRequiredViewAsType(source, R.id.text_price, "field 'text_price'", TextView.class);
    target.count = Utils.findRequiredViewAsType(source, R.id.inc_dec_count, "field 'count'", TextView.class);
    target.img_increment = Utils.findRequiredViewAsType(source, R.id.img_increment, "field 'img_increment'", ImageView.class);
    target.img_decrement = Utils.findRequiredViewAsType(source, R.id.img_decrement, "field 'img_decrement'", ImageView.class);
    target.img_remove = Utils.findRequiredViewAsType(source, R.id.img_remove, "field 'img_remove'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SingleCheckoutAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.no_items = null;
    target.product_name = null;
    target.text_price = null;
    target.count = null;
    target.img_increment = null;
    target.img_decrement = null;
    target.img_remove = null;
  }
}
