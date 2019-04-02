// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.single_transactions;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SingleTransactionDetails_ViewBinding implements Unbinder {
  private SingleTransactionDetails target;

  private View view2131296622;

  @UiThread
  public SingleTransactionDetails_ViewBinding(SingleTransactionDetails target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SingleTransactionDetails_ViewBinding(final SingleTransactionDetails target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_single_trans, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_single_transaction, "field 'recyclerView'", RecyclerView.class);
    target.payment_tp = Utils.findRequiredViewAsType(source, R.id.payment_type, "field 'payment_tp'", TextView.class);
    target.receipt_number = Utils.findRequiredViewAsType(source, R.id.receipt_number, "field 'receipt_number'", TextView.class);
    target.single_trans_amt = Utils.findRequiredViewAsType(source, R.id.single_trans_amt, "field 'single_trans_amt'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi, "field 'avi'", AVLoadingIndicatorView.class);
    target.img_receipt = Utils.findRequiredViewAsType(source, R.id.img_receipt, "field 'img_receipt'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.receipt_btn, "method 'onTransReceiptBtn'");
    view2131296622 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onTransReceiptBtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SingleTransactionDetails target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.payment_tp = null;
    target.receipt_number = null;
    target.single_trans_amt = null;
    target.title = null;
    target.avi = null;
    target.img_receipt = null;

    view2131296622.setOnClickListener(null);
    view2131296622 = null;
  }
}
