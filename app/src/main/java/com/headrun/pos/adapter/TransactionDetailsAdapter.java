package com.headrun.pos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headrun.pos.R;
import com.headrun.pos.model.TransactionModel;
import com.headrun.pos.ui.pages.single_transactions.SingleTransactionDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionDetailsAdapter extends RecyclerView.Adapter<TransactionDetailsAdapter.ViewHolder> {

    private Context context;
    private List<TransactionModel> list = new ArrayList<>();

    public TransactionDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_transaction_details, viewGroup, false);
        return new TransactionDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionDetailsAdapter.ViewHolder viewHolder, final int i) {

        final TransactionModel model = list.get(i);

        viewHolder.price.setText("₹ "+model.getTotal_price());
        viewHolder.items.setText(model.getTotal_items() + " items");
        viewHolder.receipt_no.setText("#"+model.getReceipt_no());
        Date date = new Date(Long.parseLong(model.getTime()));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        String getDate = format.format(date);
        viewHolder.time.setText(getDate);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SingleTransactionDetails.class);
                intent.putExtra("time", model.getTime());
                intent.putExtra("payment_type", model.getPayment_type());
                intent.putExtra("receipt_no", model.getReceipt_no());
                intent.putExtra("total_price", model.getTotal_price());
                intent.putExtra("date", model.getDate());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trans_detail_price) TextView price;
        @BindView(R.id.trans_detail_items) TextView items;
        @BindView(R.id.trans_detail_receipt) TextView receipt_no;
        @BindView(R.id.time) TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<TransactionModel> getList() {
        return list;
    }

    public void setList(List<TransactionModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}