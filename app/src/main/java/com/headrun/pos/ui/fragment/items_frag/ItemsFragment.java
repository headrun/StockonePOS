package com.headrun.pos.ui.fragment.items_frag;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.R;
import com.headrun.pos.adapter.ItemsAdapter;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.fragment.base.BaseFragment;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.new_product.NewProduct;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends BaseFragment<ItemsFragPresenter> implements ItemsFragView {

    @BindView(R.id.items_add) ImageView items_add;
    @BindView(R.id.img_search) ImageView img_search;
    @BindView(R.id.img_cross) ImageView img_cross;
    @BindView(R.id.rv_items) RecyclerView recyclerView;
    @BindView(R.id.avi_items) AVLoadingIndicatorView avi;
    @BindView(R.id.et_search_items) EditText et_search_items;
    ItemsAdapter adapter;

    public ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        searchableEdittext();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getItems();
    }

    @OnClick(R.id.items_add)
    void onItemsAddClick(){

        Intent intent = new Intent(getContext(), NewProduct.class);
        intent.putExtra("is_update", false);
        startActivity(intent);
    }

    private void searchableEdittext(){

        et_search_items.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                presenter.filter(editable.toString());
            }
        });
    }

    @OnClick(R.id.et_search_items)
    void onSearchClicked() {

        presenter.onSearchPressed();

    }

    @OnClick(R.id.img_cross)
    void onImageCross(){

        presenter.onCrossPressed();
    }

    @NonNull
    @Override
    protected ItemsFragPresenter createPresenter() {
        return new ItemsFragPresenter(this);
    }

    @Override
    public void showCategories(List<ProductModel> list) {

        adapter.setList(list);
    }

    @Override
    public void onSearchPressed() {

        et_search_items.requestFocus();
        img_search.setVisibility(View.INVISIBLE);
        img_cross.setVisibility(View.VISIBLE);
        et_search_items.setFocusable(true);
    }

    @Override
    public void onCrossPressed() {

        img_search.setVisibility(View.VISIBLE);
        img_cross.setVisibility(View.GONE);
        et_search_items.setText("");
        et_search_items.clearFocus();
        presenter.getItems();
        General.hideKeyboard(getActivity());
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
    }
}
