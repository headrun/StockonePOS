package com.headrun.pos.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headrun.pos.R;
import com.headrun.pos.callback.OnCategorySelected;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import libs.mjn.scaletouchlistener.ScaleTouchListener;

public class CategoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CategoryModel> list = new ArrayList<>();
    private OnCategorySelected onCategorySelected;

    public CategoryAdapter(Context context, OnCategorySelected onCategorySelected) {
        this.context = context;
        this.onCategorySelected = onCategorySelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;

        switch (i){
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_main_category, viewGroup, false);
                return new CategoryAdapter.MainViewHolder(view);

            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_category, viewGroup, false);
                return new CategoryAdapter.ViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final CategoryModel model = list.get(i);

        ScaleTouchListener.Config config = new ScaleTouchListener.Config(
                300,    // Duration
                0.75f,  // ScaleDown
                0.75f);

        switch (model.getType()){
            case 0:
                ((MainViewHolder) viewHolder).category_name.setText(model.getCat_name());
                ((MainViewHolder) viewHolder).category_card.setCardBackgroundColor(getRandomMaterialColor("400"));

                ((MainViewHolder) viewHolder).category_card.setOnTouchListener(new ScaleTouchListener(config) {     // <--- pass config object
                    @Override
                    public void onClick(View v) {
                        onCategorySelected.categoryName(model.getCat_name());
                    }
                });
                break;
            case 1:
                ((ViewHolder) viewHolder).cat_name.setText(model.getCat_name());
                ((ViewHolder) viewHolder).itemView.setOnClickListener(v -> onCategorySelected.categoryName(model.getCat_name()));
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

        @BindView(R.id.cat_name) TextView cat_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.category_name) TextView category_name;
        @BindView(R.id.category_card) CardView category_card;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<CategoryModel> getList() {
        return list;
    }

    public void setList(List<CategoryModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void filterList(List<CategoryModel> filterdNames) {
        this.list = filterdNames;
        notifyDataSetChanged();
    }

    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }
}