// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.show_cards;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowCards_ViewBinding implements Unbinder {
  private ShowCards target;

  @UiThread
  public ShowCards_ViewBinding(ShowCards target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShowCards_ViewBinding(ShowCards target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_scratch_cards, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_scratch_card, "field 'toolbar'", Toolbar.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.avi_cards, "field 'avi'", AVLoadingIndicatorView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShowCards target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.toolbar = null;
    target.avi = null;
  }
}
