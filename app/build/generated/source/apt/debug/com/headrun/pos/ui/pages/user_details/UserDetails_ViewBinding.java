// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.user_details;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserDetails_ViewBinding implements Unbinder {
  private UserDetails target;

  private View view2131296326;

  private View view2131296323;

  @UiThread
  public UserDetails_ViewBinding(UserDetails target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserDetails_ViewBinding(final UserDetails target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_user_details, "field 'toolbar'", Toolbar.class);
    target.et_company_name = Utils.findRequiredViewAsType(source, R.id.et_company_name, "field 'et_company_name'", EditText.class);
    target.et_address_one = Utils.findRequiredViewAsType(source, R.id.et_address_one, "field 'et_address_one'", EditText.class);
    target.et_gst_no = Utils.findRequiredViewAsType(source, R.id.et_gst_no, "field 'et_gst_no'", EditText.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_user_details, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.btn_enter_store, "method 'onEnterStore'");
    view2131296326 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEnterStore();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_company_users, "method 'onCompanyUsers'");
    view2131296323 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCompanyUsers();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UserDetails target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.et_company_name = null;
    target.et_address_one = null;
    target.et_gst_no = null;
    target.avi = null;

    view2131296326.setOnClickListener(null);
    view2131296326 = null;
    view2131296323.setOnClickListener(null);
    view2131296323 = null;
  }
}
