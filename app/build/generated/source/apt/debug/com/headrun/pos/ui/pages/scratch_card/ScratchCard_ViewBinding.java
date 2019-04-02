// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.scratch_card;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScratchCard_ViewBinding implements Unbinder {
  private ScratchCard target;

  private View view2131296425;

  private View view2131296449;

  private View view2131296651;

  private View view2131296681;

  @UiThread
  public ScratchCard_ViewBinding(ScratchCard target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScratchCard_ViewBinding(final ScratchCard target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_scratch_card, "field 'toolbar'", Toolbar.class);
    target.scratch_bg = Utils.findRequiredViewAsType(source, R.id.scratch_bg, "field 'scratch_bg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.et_from_date, "field 'et_from_date' and method 'onFromDate'");
    target.et_from_date = Utils.castView(view, R.id.et_from_date, "field 'et_from_date'", EditText.class);
    view2131296425 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFromDate();
      }
    });
    view = Utils.findRequiredView(source, R.id.et_to_date, "field 'et_to_date' and method 'onToDate'");
    target.et_to_date = Utils.castView(view, R.id.et_to_date, "field 'et_to_date'", EditText.class);
    view2131296449 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onToDate();
      }
    });
    target.et_disc_amt = Utils.findRequiredViewAsType(source, R.id.et_disc_amt, "field 'et_disc_amt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.scratch_btn, "field 'scratch_btn' and method 'onScratchBtnSubmit'");
    target.scratch_btn = Utils.castView(view, R.id.scratch_btn, "field 'scratch_btn'", Button.class);
    view2131296651 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onScratchBtnSubmit();
      }
    });
    target.hide_scratch_btn = Utils.findRequiredViewAsType(source, R.id.hide_scratch_btn, "field 'hide_scratch_btn'", Button.class);
    view = Utils.findRequiredView(source, R.id.show_coupon, "method 'onShowCoupon'");
    view2131296681 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onShowCoupon();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ScratchCard target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.scratch_bg = null;
    target.et_from_date = null;
    target.et_to_date = null;
    target.et_disc_amt = null;
    target.scratch_btn = null;
    target.hide_scratch_btn = null;

    view2131296425.setOnClickListener(null);
    view2131296425 = null;
    view2131296449.setOnClickListener(null);
    view2131296449 = null;
    view2131296651.setOnClickListener(null);
    view2131296651 = null;
    view2131296681.setOnClickListener(null);
    view2131296681 = null;
  }
}
