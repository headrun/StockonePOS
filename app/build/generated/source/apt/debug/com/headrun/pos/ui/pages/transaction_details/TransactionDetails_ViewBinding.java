// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.transaction_details;

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

public class TransactionDetails_ViewBinding implements Unbinder {
  private TransactionDetails target;

  @UiThread
  public TransactionDetails_ViewBinding(TransactionDetails target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TransactionDetails_ViewBinding(TransactionDetails target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_transaction_details, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_transaction_details, "field 'recyclerView'", RecyclerView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_transaction_details, "field 'avi'", AVLoadingIndicatorView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransactionDetails target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.avi = null;
    target.title = null;
  }
}
