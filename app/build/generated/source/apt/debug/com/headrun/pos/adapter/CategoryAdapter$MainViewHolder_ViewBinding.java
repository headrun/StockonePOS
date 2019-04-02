// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryAdapter$MainViewHolder_ViewBinding implements Unbinder {
  private CategoryAdapter.MainViewHolder target;

  @UiThread
  public CategoryAdapter$MainViewHolder_ViewBinding(CategoryAdapter.MainViewHolder target,
      View source) {
    this.target = target;

    target.category_name = Utils.findRequiredViewAsType(source, R.id.category_name, "field 'category_name'", TextView.class);
    target.category_card = Utils.findRequiredViewAsType(source, R.id.category_card, "field 'category_card'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryAdapter.MainViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.category_name = null;
    target.category_card = null;
  }
}
