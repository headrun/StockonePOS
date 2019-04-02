package com.headrun.pos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headrun.pos.R;
import com.headrun.pos.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleTransactionAdapter extends RecyclerView.Adapter<SingleTransactionAdapter.ViewHolder> {

    private Context context;
    private List<TransactionModel> list = new ArrayList<>();

    public SingleTransactionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SingleTransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_transaction_items, viewGroup, false);
        return new SingleTransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleTransactionAdapter.ViewHolder viewHolder, final int i) {

        TransactionModel model = list.get(i);

        viewHolder.no_items.setText(model.getItem_qty()+"X ");
        viewHolder.item_name.setText(model.getItem_name());
        viewHolder.text_price.setText("₹ "+model.getItem_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.no_items) TextView no_items;
        @BindView(R.id.item_name) TextView item_name;
        @BindView(R.id.text_price) TextView text_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setList(List<TransactionModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
