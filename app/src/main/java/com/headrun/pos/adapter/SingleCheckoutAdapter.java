package com.headrun.pos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.headrun.pos.R;
import com.headrun.pos.callback.OnCheckoutItemClicked;
import com.headrun.pos.callback.OnItemClicked;
import com.headrun.pos.eventbus.GeneralData;
import com.headrun.pos.model.CheckoutModel;
import com.headrun.pos.model.ItemModel;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.SingleTransactionModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleCheckoutAdapter extends RecyclerView.Adapter<SingleCheckoutAdapter.ViewHolder> {

    private Context context;
    public  static List<ProductModel> list = new ArrayList<>();
    private OnCheckoutItemClicked onCheckoutItemClicked;
    private OnItemClicked mCallback;
    private List<ProductModel> modelList = new ArrayList<>();
    double single_price = 0;
    Map<String, Integer> newMap = new HashMap<>();
    public static LinkedHashMap<String, Object> transactionMap = new LinkedHashMap<>();
    private onSelectedClick onSelectedClick;

    public SingleCheckoutAdapter(Context context, OnCheckoutItemClicked onCheckoutItemClicked, OnItemClicked mCallback) {
        this.context = context;
        this.onCheckoutItemClicked = onCheckoutItemClicked;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public SingleCheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_items_checkout, viewGroup, false);
        return new SingleCheckoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleCheckoutAdapter.ViewHolder viewHolder, final int i) {

        final ProductModel model = list.get(i);
//        final ProductModel singleTransactionModel = modelList.get(i);

        viewHolder.setIsRecyclable(false);

        viewHolder.no_items.setText(model.getQuantity()+" x ");
        viewHolder.product_name.setText(model.getProduct_name());
        double price = model.getProduct_price() * model.getQuantity();
        viewHolder.text_price.setText("₹ "+price);
        viewHolder.count.setText(""+model.getQuantity());

        newMap.put(model.getProduct_name(), model.getQuantity());

        if (model.getQuantity() <= 1){
            viewHolder.img_decrement.setEnabled(false);
        }

        viewHolder.img_decrement.setOnClickListener(new View.OnClickListener() {
            double price;
            @Override
            public void onClick(View v) {
                model.setQuantity(model.getQuantity()-1);
//                singleTransactionModel.setQuantity(singleTransactionModel.getQuantity()-1);
                viewHolder.no_items.setText(model.getQuantity()+" x ");
                viewHolder.count.setText(""+model.getQuantity());
                price = model.getQuantity() * model.getProduct_price();
//                singleTransactionModel.setProduct_price(price);
                viewHolder.text_price.setText("₹ "+price);
                CheckoutAdapter.map.put(model.getProduct_name(), model);
//                CheckoutAdapter.transactionMap.put(singleTransactionModel.getProduct_name(), singleTransactionModel);
//                mCallback.onItemName(map);

                single_price = single_price - model.getProduct_price();
                onCheckoutItemClicked.onTotalPrice(single_price);
                newMap.put(model.getProduct_name(), model.getQuantity());
                onCheckoutItemClicked.onProductIncDec(newMap);

                if (model.getQuantity() <= 1){
                    viewHolder.img_decrement.setEnabled(false);
                }

            }
        });

        viewHolder.img_increment.setOnClickListener(new View.OnClickListener() {
            double price;
            @Override
            public void onClick(View v) {
                model.setQuantity(model.getQuantity()+1);
                viewHolder.no_items.setText(model.getQuantity()+" x ");
                viewHolder.count.setText(""+model.getQuantity());
                price = model.getQuantity() * model.getProduct_price();
                viewHolder.text_price.setText("₹ "+price);
                CheckoutAdapter.map.put(model.getProduct_name(), model);

                single_price = single_price + model.getProduct_price();
                onCheckoutItemClicked.onTotalPrice(single_price);

                newMap.put(model.getProduct_name(), model.getQuantity());
                onCheckoutItemClicked.onProductIncDec(newMap);

                if (model.getQuantity() > 1){
                    viewHolder.img_decrement.setEnabled(true);
                }

            }
        });

        viewHolder.img_remove.setOnClickListener(new View.OnClickListener() {
            double price;
            @Override
            public void onClick(View v) {
                price = model.getQuantity() * model.getProduct_price();
                onCheckoutItemClicked.onDelete(price, model.getQuantity());
                removeAt(i);
                CheckoutAdapter.map.remove(model.getProduct_name());
//                CheckoutAdapter.map.remove(singleTransactionModel.getProduct_name());
            }
        });


    }

    @Override
    public int getItemCount() {

        return  list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.no_items) TextView no_items;
        @BindView(R.id.product_name) TextView product_name;
        @BindView(R.id.text_price) TextView text_price;
        @BindView(R.id.inc_dec_count) TextView count;
        @BindView(R.id.img_increment) ImageView img_increment;
        @BindView(R.id.img_decrement) ImageView img_decrement;
        @BindView(R.id.img_remove) ImageView img_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
        notifyDataSetChanged();
    }

    public List<ProductModel> getList() {
        return list;
    }

    public void setList(List<ProductModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setModelList(List<ProductModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public interface onSelectedClick{

        void onSelected();
    }
}