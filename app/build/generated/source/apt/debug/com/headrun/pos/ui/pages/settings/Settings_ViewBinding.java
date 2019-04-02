// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Settings_ViewBinding implements Unbinder {
  private Settings target;

  private View view2131296476;

  @UiThread
  public Settings_ViewBinding(Settings target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Settings_ViewBinding(final Settings target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_settings, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.groups_rl, "method 'onGropusClicked'");
    view2131296476 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGropusClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Settings target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;

    view2131296476.setOnClickListener(null);
    view2131296476 = null;
  }
}
