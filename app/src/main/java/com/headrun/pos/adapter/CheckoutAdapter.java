package com.headrun.pos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.headrun.pos.R;
import com.headrun.pos.callback.OnItemClicked;
import com.headrun.pos.eventbus.GeneralData;
import com.headrun.pos.model.ItemModel;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.SingleTransactionModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import libs.mjn.scaletouchlistener.ScaleTouchListener;

public class CheckoutAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ProductModel> list = new ArrayList<>();
    private List<ProductModel> modelList = new ArrayList<>();
    public static LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    public static LinkedHashMap<String, Object> transactionMap = new LinkedHashMap<>();
    List<String> nameList = new ArrayList<>();
    private OnItemClicked mCallback;

    public CheckoutAdapter(Context context,OnItemClicked mCallback) {
        this.context = context;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;

        switch (i){
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_checkout_items, viewGroup, false);
                return new CheckoutAdapter.ViewHolder(view);

            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_items, viewGroup, false);
                return new CheckoutAdapter.ListViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

        final ProductModel model = list.get(i);
//        final ProductModel singleTransactionModel = modelList.get(i);

        ScaleTouchListener.Config config = new ScaleTouchListener.Config(
                300,    // Duration
                0.75f,  // ScaleDown
                0.75f);

        switch (model.getType()){
            case 0:
                Glide.with(context).load(model.getProduct_img()).into(((ViewHolder)viewHolder).product_img);
                ((CheckoutAdapter.ViewHolder) viewHolder).product_name.setText(model.getProduct_name());
                ((CheckoutAdapter.ViewHolder) viewHolder).blw_product_name.setText(model.getProduct_name());
                ((CheckoutAdapter.ViewHolder) viewHolder).product_price.setText("₹ "+model.getProduct_price());
                ((CheckoutAdapter.ViewHolder) viewHolder).product_card.setOnTouchListener(new ScaleTouchListener(config) {     // <--- pass config object
                    @Override
                    public void onClick(View v) {
                        //OnClickListener
                        map.put(model.getProduct_name(), model);
                        nameList.add(model.getProduct_name());
                        mCallback.onItemName(map);
//                transactionMap.put(singleTransactionModel.getProduct_name(), singleTransactionModel);
                        mCallback.onItemTransaction(transactionMap);

                    }
                });
                break;
            case 1:
                ((CheckoutAdapter.ListViewHolder) viewHolder).image_name.setText(model.getProduct_name());
                ((ListViewHolder) viewHolder).items_stock.setText(model.getStock());
                ((ListViewHolder) viewHolder).item_text_price.setText("₹ "+model.getProduct_price());
                ((ListViewHolder) viewHolder).items_name.setText(model.getProduct_name());
                Glide.with(context).load(model.getProduct_img()).into(((ListViewHolder)viewHolder).items_image);

                ((ListViewHolder) viewHolder).itemView.setOnTouchListener(new ScaleTouchListener(config) {
                    @Override
                    public void onClick(View v) {
                        //OnClickListener
                        map.put(model.getProduct_name(), model);
                        nameList.add(model.getProduct_name());
                        mCallback.onItemName(map);
                        mCallback.onItemTransaction(transactionMap);

                    }
                });
                break;

        }


    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()){
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return 0;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_card) CardView product_card;
        @BindView(R.id.product_img) ImageView product_img;
        @BindView(R.id.product_name) TextView product_name;
        @BindView(R.id.blw_product_name) TextView blw_product_name;
        @BindView(R.id.product_price) TextView product_price;
        @BindView(R.id.selected_item) RelativeLayout selected_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.items_image) ImageView items_image;
        @BindView(R.id.image_name) TextView image_name;
        @BindView(R.id.items_name) TextView items_name;
        @BindView(R.id.items_stock) TextView items_stock;
        @BindView(R.id.item_text_price) TextView item_text_price;

        public ListViewHolder(@NonNull View itemView) {
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
}
