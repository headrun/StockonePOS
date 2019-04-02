// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.login;

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

public class Login_ViewBinding implements Unbinder {
  private Login target;

  private View view2131296327;

  private View view2131296601;

  @UiThread
  public Login_ViewBinding(Login target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Login_ViewBinding(final Login target, View source) {
    this.target = target;

    View view;
    target.bg = Utils.findRequiredViewAsType(source, R.id.login_bg, "field 'bg'", ImageView.class);
    target.et_email = Utils.findRequiredViewAsType(source, R.id.et_login_email, "field 'et_email'", EditText.class);
    target.et_password = Utils.findRequiredViewAsType(source, R.id.et_login_password, "field 'et_password'", EditText.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_login, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btn_login' and method 'onLoginClicked'");
    target.btn_login = Utils.castView(view, R.id.btn_login, "field 'btn_login'", Button.class);
    view2131296327 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.please_register, "method 'onRegister'");
    view2131296601 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Login target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bg = null;
    target.et_email = null;
    target.et_password = null;
    target.avi = null;
    target.btn_login = null;

    view2131296327.setOnClickListener(null);
    view2131296327 = null;
    view2131296601.setOnClickListener(null);
    view2131296601 = null;
  }
}
