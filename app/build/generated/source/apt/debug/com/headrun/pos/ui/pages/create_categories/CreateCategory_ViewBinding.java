// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.ui.pages.create_categories;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateCategory_ViewBinding implements Unbinder {
  private CreateCategory target;

  private View view2131296324;

  @UiThread
  public CreateCategory_ViewBinding(CreateCategory target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreateCategory_ViewBinding(final CreateCategory target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_create_category, "field 'toolbar'", Toolbar.class);
    target.create_category = Utils.findRequiredViewAsType(source, R.id.et_create_category, "field 'create_category'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_create_category, "field 'btn_create_category' and method 'onCategoryCreated'");
    target.btn_create_category = Utils.castView(view, R.id.btn_create_category, "field 'btn_create_category'", Button.class);
    view2131296324 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCategoryCreated();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateCategory target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.create_category = null;
    target.btn_create_category = null;

    view2131296324.setOnClickListener(null);
    view2131296324 = null;
  }
}
