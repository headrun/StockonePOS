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

public class ReceiptAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ReceiptAdapter.ViewHolder target;

  @UiThread
  public ReceiptAdapter$ViewHolder_ViewBinding(ReceiptAdapter.ViewHolder target, View source) {
    this.target = target;

    target.single_item = Utils.findRequiredViewAsType(source, R.id.single_item, "field 'single_item'", TextView.class);
    target.single_qty = Utils.findRequiredViewAsType(source, R.id.single_qty, "field 'single_qty'", TextView.class);
    target.single_rate = Utils.findRequiredViewAsType(source, R.id.single_rate, "field 'single_rate'", TextView.class);
    target.single_amt = Utils.findRequiredViewAsType(source, R.id.single_amt, "field 'single_amt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReceiptAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.single_item = null;
    target.single_qty = null;
    target.single_rate = null;
    target.single_amt = null;
  }
}
