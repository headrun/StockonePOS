package com.headrun.pos.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.headrun.pos.R;
import com.headrun.pos.model.BluetoothModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.palaima.smoothbluetooth.Device;

public class BluetoothAdapter extends RecyclerView.Adapter<BluetoothAdapter.ViewHolder> {

    private Context context;
    List<Device> list = new ArrayList<>();

    public BluetoothAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BluetoothAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_bluetooth_device, viewGroup, false);
        return new BluetoothAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BluetoothAdapter.ViewHolder viewHolder, final int i) {

        final Device model = list.get(i);

        viewHolder.bluetooth_name.setText(model.getName());
        viewHolder.bluetooth_address.setText(model.getAddress());

    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ble_name) TextView bluetooth_name;
        @BindView(R.id.tv_ble_address) TextView bluetooth_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<Device> getList() {
        return list;
    }

    public void setList(List<Device> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}