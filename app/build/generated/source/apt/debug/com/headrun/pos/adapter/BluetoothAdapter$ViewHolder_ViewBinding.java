// Generated code from Butter Knife. Do not modify!
package com.headrun.pos.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.headrun.pos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BluetoothAdapter$ViewHolder_ViewBinding implements Unbinder {
  private BluetoothAdapter.ViewHolder target;

  @UiThread
  public BluetoothAdapter$ViewHolder_ViewBinding(BluetoothAdapter.ViewHolder target, View source) {
    this.target = target;

    target.bluetooth_name = Utils.findRequiredViewAsType(source, R.id.tv_ble_name, "field 'bluetooth_name'", TextView.class);
    target.bluetooth_address = Utils.findRequiredViewAsType(source, R.id.tv_ble_address, "field 'bluetooth_address'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BluetoothAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bluetooth_name = null;
    target.bluetooth_address = null;
  }
}
