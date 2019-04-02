package com.headrun.pos.adapter;

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
import com.headrun.pos.ui.pages.transaction_details.TransactionDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private Context context;
    private List<TransactionModel> list = new ArrayList<>();

    public TransactionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_transactions, viewGroup, false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder viewHolder, final int i) {

        final TransactionModel model = list.get(i);

        viewHolder.date.setText(model.getDate());
        viewHolder.sales.setText("₹ "+model.getTransaction_total_price()+ " from "+ model.getTransaction_total_sales()+" sales");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransactionDetails.class);
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

        @BindView(R.id.transaction_single_date) TextView date;
        @BindView(R.id.transaction_total_sales) TextView sales;

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
