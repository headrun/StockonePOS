package com.headrun.pos.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.headrun.pos.R;
import com.headrun.pos.callback.OnReceipt;
import com.headrun.pos.model.ItemModel;
import com.headrun.pos.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder> {

    private Context context;
    private OnReceipt onReceipt;
    List<Double> list = new ArrayList<>();


    public ReceiptAdapter(Context context, OnReceipt onReceipt) {
        this.context = context;
        this.onReceipt = onReceipt;
    }

    @NonNull
    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_receipt_layout, viewGroup, false);
        return new ReceiptAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ViewHolder viewHolder, final int i) {

        ProductModel model = SingleCheckoutAdapter.list.get(i);

        double price = model.getQuantity() * model.getProduct_price();

        viewHolder.single_amt.setText("₹ "+price);
        viewHolder.single_rate.setText("₹ "+model.getProduct_price());
        viewHolder.single_qty.setText(""+model.getQuantity());
        viewHolder.single_item.setText(model.getProduct_name());


        list.add(model.getQuantity() * model.getProduct_price());
        onReceipt.onTotalItemsPrice(list);
//        LogUtils.e(list.get(i));
//        new Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        onReceipt.onTotalItemsPrice(list);
//                    }
//                }, 1000);


    }

    @Override
    public int getItemCount() {
        if(SingleCheckoutAdapter.list.size() == 0){
            return 0;
        }else {
            return SingleCheckoutAdapter.list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_item) TextView single_item;
        @BindView(R.id.single_qty) TextView single_qty;
        @BindView(R.id.single_rate) TextView single_rate;
        @BindView(R.id.single_amt) TextView single_amt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}