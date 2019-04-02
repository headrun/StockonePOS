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

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> list = new ArrayList<>();

    public ItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final ProductModel model = list.get(i);

        Glide.with(context).load(model.getProduct_img()).into(viewHolder.items_image);
        viewHolder.image_name.setText(model.getProduct_name());
        viewHolder.items_name.setText(model.getProduct_name());
        viewHolder.items_category.setText(model.getCategory());
        viewHolder.item_text_price.setText("₹"+model.getProduct_price());

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

        @BindView(R.id.items_image) ImageView items_image;
        @BindView(R.id.image_name) TextView image_name;
        @BindView(R.id.items_name) TextView items_name;
        @BindView(R.id.items_stock) TextView items_category;
        @BindView(R.id.item_text_price) TextView item_text_price;

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