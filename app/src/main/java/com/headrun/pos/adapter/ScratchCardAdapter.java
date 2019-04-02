package com.headrun.pos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.headrun.pos.R;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.ScratchModel;
import com.headrun.pos.utils.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScratchCardAdapter extends RecyclerView.Adapter<ScratchCardAdapter.ViewHolder> {

    private Context context;
    private List<ScratchModel> list = new ArrayList<>();

    public ScratchCardAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ScratchCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_show_coupon, viewGroup, false);
        return new ScratchCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScratchCardAdapter.ViewHolder viewHolder, final int i) {

        final ScratchModel model = list.get(i);

        viewHolder.text_coupon_code.setText(model.getCoupon_code());
        viewHolder.text_disc_amt.setText("₹"+model.getDiscount_amt());
        viewHolder.txt_from_date.setText(model.getFrom_date());
        viewHolder.text_to_date.setText(model.getTo_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_coupon_code) TextView text_coupon_code;
        @BindView(R.id.text_disc_amt) TextView text_disc_amt;
        @BindView(R.id.txt_from_date) TextView txt_from_date;
        @BindView(R.id.text_to_date) TextView text_to_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<ScratchModel> getList() {
        return list;
    }

    public void setList(List<ScratchModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
