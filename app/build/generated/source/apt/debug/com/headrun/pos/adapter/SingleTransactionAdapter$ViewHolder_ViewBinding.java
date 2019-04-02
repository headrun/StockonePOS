// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SingleTransactionAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SingleTransactionAdapter.ViewHolder target;

  @UiThread
  public SingleTransactionAdapter$ViewHolder_ViewBinding(SingleTransactionAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.no_items = Utils.findRequiredViewAsType(source, R.id.no_items, "field 'no_items'", TextView.class);
    target.item_name = Utils.findRequiredViewAsType(source, R.id.item_name, "field 'item_name'", TextView.class);
    target.text_price = Utils.findRequiredViewAsType(source, R.id.text_price, "field 'text_price'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SingleTransactionAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.no_items = null;
    target.item_name = null;
    target.text_price = null;
  }
}
