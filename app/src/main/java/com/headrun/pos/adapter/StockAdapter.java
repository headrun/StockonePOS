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
import com.headrun.pos.utils.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private Context context;
    List<ProductModel> list = new ArrayList<>();

    public StockAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_stock, viewGroup, false);
        return new StockAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.ViewHolder viewHolder, final int i) {

        final ProductModel model = list.get(i);

        Glide.with(context).load(model.getProduct_img()).into(viewHolder.stock_img);
        viewHolder.img_stock_name.setText(model.getProduct_name());
        viewHolder.stock_product_name.setText(model.getProduct_name());
        viewHolder.stock_no.setText(model.getStock());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityManager.NEW_PRODUCTS(context, model.getProduct_img(), model.getProduct_name(),model.getProduct_price(),
                        model.getCategory(),model.getStock(), model.getSell_by(), model.getProduct_id(), model.getDescription(),
                        true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.stock_img) ImageView stock_img;
        @BindView(R.id.img_stock_name) TextView img_stock_name;
        @BindView(R.id.stock_product_name) TextView stock_product_name;
        @BindView(R.id.stock_no) TextView stock_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<ProductModel> getList() {
        return list;
    }

    public void setList(List<ProductModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void filterList(List<ProductModel> filterdNames) {
        this.list = filterdNames;
        notifyDataSetChanged();
    }
}
