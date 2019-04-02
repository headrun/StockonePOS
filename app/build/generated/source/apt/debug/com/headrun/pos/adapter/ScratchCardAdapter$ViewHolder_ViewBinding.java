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

public class ScratchCardAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ScratchCardAdapter.ViewHolder target;

  @UiThread
  public ScratchCardAdapter$ViewHolder_ViewBinding(ScratchCardAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.text_coupon_code = Utils.findRequiredViewAsType(source, R.id.text_coupon_code, "field 'text_coupon_code'", TextView.class);
    target.text_disc_amt = Utils.findRequiredViewAsType(source, R.id.text_disc_amt, "field 'text_disc_amt'", TextView.class);
    target.txt_from_date = Utils.findRequiredViewAsType(source, R.id.txt_from_date, "field 'txt_from_date'", TextView.class);
    target.text_to_date = Utils.findRequiredViewAsType(source, R.id.text_to_date, "field 'text_to_date'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ScratchCardAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text_coupon_code = null;
    target.text_disc_amt = null;
    target.txt_from_date = null;
    target.text_to_date = null;
  }
}
