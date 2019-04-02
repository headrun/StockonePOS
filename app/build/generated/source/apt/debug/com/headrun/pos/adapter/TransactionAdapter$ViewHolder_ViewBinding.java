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

public class TransactionAdapter$ViewHolder_ViewBinding implements Unbinder {
  private TransactionAdapter.ViewHolder target;

  @UiThread
  public TransactionAdapter$ViewHolder_ViewBinding(TransactionAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.date = Utils.findRequiredViewAsType(source, R.id.transaction_single_date, "field 'date'", TextView.class);
    target.sales = Utils.findRequiredViewAsType(source, R.id.transaction_total_sales, "field 'sales'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransactionAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.date = null;
    target.sales = null;
  }
}
