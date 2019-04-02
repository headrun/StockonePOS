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

public class TransactionDetailsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private TransactionDetailsAdapter.ViewHolder target;

  @UiThread
  public TransactionDetailsAdapter$ViewHolder_ViewBinding(TransactionDetailsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.price = Utils.findRequiredViewAsType(source, R.id.trans_detail_price, "field 'price'", TextView.class);
    target.items = Utils.findRequiredViewAsType(source, R.id.trans_detail_items, "field 'items'", TextView.class);
    target.receipt_no = Utils.findRequiredViewAsType(source, R.id.trans_detail_receipt, "field 'receipt_no'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransactionDetailsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.price = null;
    target.items = null;
    target.receipt_no = null;
    target.time = null;
  }
}
