// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.register;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Register_ViewBinding implements Unbinder {
  private Register target;

  private View view2131296329;

  private View view2131296600;

  @UiThread
  public Register_ViewBinding(Register target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Register_ViewBinding(final Register target, View source) {
    this.target = target;

    View view;
    target.bg = Utils.findRequiredViewAsType(source, R.id.signup_bg, "field 'bg'", ImageView.class);
    target.et_email = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'et_email'", EditText.class);
    target.et_password = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'et_password'", EditText.class);
    target.et_full_name = Utils.findRequiredViewAsType(source, R.id.et_full_name, "field 'et_full_name'", EditText.class);
    target.et_phone_no = Utils.findRequiredViewAsType(source, R.id.et_phone_no, "field 'et_phone_no'", EditText.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_register, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.btn_register, "field 'btn_register' and method 'onRegister'");
    target.btn_register = Utils.castView(view, R.id.btn_register, "field 'btn_register'", Button.class);
    view2131296329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRegister();
      }
    });
    view = Utils.findRequiredView(source, R.id.please_login, "method 'onLoginClick'");
    view2131296600 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Register target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bg = null;
    target.et_email = null;
    target.et_password = null;
    target.et_full_name = null;
    target.et_phone_no = null;
    target.avi = null;
    target.btn_register = null;

    view2131296329.setOnClickListener(null);
    view2131296329 = null;
    view2131296600.setOnClickListener(null);
    view2131296600 = null;
  }
}
