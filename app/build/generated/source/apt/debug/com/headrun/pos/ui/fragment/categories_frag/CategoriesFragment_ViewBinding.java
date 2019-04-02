// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.fragment.categories_frag;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoriesFragment_ViewBinding implements Unbinder {
  private CategoriesFragment target;

  private View view2131296438;

  private View view2131296493;

  private View view2131296513;

  @UiThread
  public CategoriesFragment_ViewBinding(final CategoriesFragment target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_categories, "field 'recyclerView'", RecyclerView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_frag_cat, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.et_search_categories, "field 'et_search_categories' and method 'onSearchClicked'");
    target.et_search_categories = Utils.castView(view, R.id.et_search_categories, "field 'et_search_categories'", EditText.class);
    view2131296438 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClicked();
      }
    });
    target.items_search = Utils.findRequiredViewAsType(source, R.id.items_search, "field 'items_search'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_cross, "field 'img_cross' and method 'onImageeCross'");
    target.img_cross = Utils.castView(view, R.id.img_cross, "field 'img_cross'", ImageView.class);
    view2131296493 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onImageeCross();
      }
    });
    view = Utils.findRequiredView(source, R.id.items_add_category, "method 'onItemCategoryAdded'");
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onItemCategoryAdded();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoriesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.avi = null;
    target.et_search_categories = null;
    target.items_search = null;
    target.img_cross = null;

    view2131296438.setOnClickListener(null);
    view2131296438 = null;
    view2131296493.setOnClickListener(null);
    view2131296493 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
  }
}
