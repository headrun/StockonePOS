// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.fragment.items_frag;

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

public class ItemsFragment_ViewBinding implements Unbinder {
  private ItemsFragment target;

  private View view2131296512;

  private View view2131296493;

  private View view2131296439;

  @UiThread
  public ItemsFragment_ViewBinding(final ItemsFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.items_add, "field 'items_add' and method 'onItemsAddClick'");
    target.items_add = Utils.castView(view, R.id.items_add, "field 'items_add'", ImageView.class);
    view2131296512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onItemsAddClick();
      }
    });
    target.img_search = Utils.findRequiredViewAsType(source, R.id.img_search, "field 'img_search'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_cross, "field 'img_cross' and method 'onImageCross'");
    target.img_cross = Utils.castView(view, R.id.img_cross, "field 'img_cross'", ImageView.class);
    view2131296493 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onImageCross();
      }
    });
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_items, "field 'recyclerView'", RecyclerView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_items, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.et_search_items, "field 'et_search_items' and method 'onSearchClicked'");
    target.et_search_items = Utils.castView(view, R.id.et_search_items, "field 'et_search_items'", EditText.class);
    view2131296439 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ItemsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.items_add = null;
    target.img_search = null;
    target.img_cross = null;
    target.recyclerView = null;
    target.avi = null;
    target.et_search_items = null;

    view2131296512.setOnClickListener(null);
    view2131296512 = null;
    view2131296493.setOnClickListener(null);
    view2131296493 = null;
    view2131296439.setOnClickListener(null);
    view2131296439 = null;
  }
}
