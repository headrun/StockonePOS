// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.products;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Products_ViewBinding implements Unbinder {
  private Products target;

  @UiThread
  public Products_ViewBinding(Products target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Products_ViewBinding(Products target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_product, "field 'toolbar'", Toolbar.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.product_tab, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.product_viewpager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Products target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.tabLayout = null;
    target.viewPager = null;
  }
}
