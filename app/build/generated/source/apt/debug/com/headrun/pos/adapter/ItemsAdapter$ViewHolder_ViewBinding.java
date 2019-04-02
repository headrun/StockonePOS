// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ItemsAdapter.ViewHolder target;

  @UiThread
  public ItemsAdapter$ViewHolder_ViewBinding(ItemsAdapter.ViewHolder target, View source) {
    this.target = target;

    target.items_image = Utils.findRequiredViewAsType(source, R.id.items_image, "field 'items_image'", ImageView.class);
    target.image_name = Utils.findRequiredViewAsType(source, R.id.image_name, "field 'image_name'", TextView.class);
    target.items_name = Utils.findRequiredViewAsType(source, R.id.items_name, "field 'items_name'", TextView.class);
    target.items_category = Utils.findRequiredViewAsType(source, R.id.items_stock, "field 'items_category'", TextView.class);
    target.item_text_price = Utils.findRequiredViewAsType(source, R.id.item_text_price, "field 'item_text_price'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ItemsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.items_image = null;
    target.image_name = null;
    target.items_name = null;
    target.items_category = null;
    target.item_text_price = null;
  }
}
