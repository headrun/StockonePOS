// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.transaction;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Transactions_ViewBinding implements Unbinder {
  private Transactions target;

  @UiThread
  public Transactions_ViewBinding(Transactions target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Transactions_ViewBinding(Transactions target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_transaction, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_transaction, "field 'recyclerView'", RecyclerView.class);
    target.sales_total = Utils.findRequiredViewAsType(source, R.id.transaction_sales_total, "field 'sales_total'", TextView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_transaction, "field 'avi'", AVLoadingIndicatorView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Transactions target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.sales_total = null;
    target.avi = null;
  }
}
