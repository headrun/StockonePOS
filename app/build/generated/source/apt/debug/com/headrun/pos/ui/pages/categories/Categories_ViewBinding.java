// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.categories;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Categories_ViewBinding implements Unbinder {
  private Categories target;

  private View view2131296513;

  @UiThread
  public Categories_ViewBinding(Categories target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Categories_ViewBinding(final Categories target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_categories, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_cat, "field 'recyclerView'", RecyclerView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_cat, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.items_add_category, "method 'onItemAddCat'");
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onItemAddCat();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Categories target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.avi = null;

    view2131296513.setOnClickListener(null);
    view2131296513 = null;
  }
}
