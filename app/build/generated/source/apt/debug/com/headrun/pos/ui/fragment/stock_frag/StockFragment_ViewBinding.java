// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.fragment.stock_frag;

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

public class StockFragment_ViewBinding implements Unbinder {
  private StockFragment target;

  private View view2131296442;

  private View view2131296502;

  @UiThread
  public StockFragment_ViewBinding(final StockFragment target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_stock, "field 'recyclerView'", RecyclerView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_stock, "field 'avi'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.et_stock_search, "field 'et_stock_search' and method 'onSearchClicked'");
    target.et_stock_search = Utils.castView(view, R.id.et_stock_search, "field 'et_stock_search'", EditText.class);
    view2131296442 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClicked();
      }
    });
    target.items_search = Utils.findRequiredViewAsType(source, R.id.items_search, "field 'items_search'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_stock_cross, "field 'img_stock_cross' and method 'onImageeCross'");
    target.img_stock_cross = Utils.castView(view, R.id.img_stock_cross, "field 'img_stock_cross'", ImageView.class);
    view2131296502 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onImageeCross();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    StockFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.avi = null;
    target.et_stock_search = null;
    target.items_search = null;
    target.img_stock_cross = null;

    view2131296442.setOnClickListener(null);
    view2131296442 = null;
    view2131296502.setOnClickListener(null);
    view2131296502 = null;
  }
}
