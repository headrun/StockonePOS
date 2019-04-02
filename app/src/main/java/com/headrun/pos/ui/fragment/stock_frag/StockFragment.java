package com.headrun.pos.ui.fragment.stock_frag;


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
import com.headrun.pos.adapter.StockAdapter;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.ui.fragment.base.BaseFragment;
import com.headrun.pos.ui.fragment.base.FragPresenter;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockFragment extends BaseFragment<StockFragPresenter> implements StockFragView {

    @BindView(R.id.rv_stock) RecyclerView recyclerView;
    @BindView(R.id.avi_stock) AVLoadingIndicatorView avi;
    @BindView(R.id.et_stock_search) EditText et_stock_search;
    @BindView(R.id.items_search) ImageView items_search;
    @BindView(R.id.img_stock_cross) ImageView img_stock_cross;
    StockAdapter adapter;

    public StockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StockAdapter(getContext());
        recyclerView.setAdapter(adapter);

        searchableEdittext();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getStocks();
    }

    private void searchableEdittext(){

        et_stock_search.addTextChangedListener(new TextWatcher() {
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

    @OnClick(R.id.et_stock_search)
    void onSearchClicked() {

        presenter.onSearchPressed();

    }

    @OnClick(R.id.img_stock_cross)
    void onImageeCross(){

        presenter.onCrossPressed();
    }

    @Override
    public void showCategories(List<ProductModel> list) {

        adapter.setList(list);
    }

    @Override
    public void onSearchPressed() {

        et_stock_search.requestFocus();
        items_search.setVisibility(View.INVISIBLE);
        img_stock_cross.setVisibility(View.VISIBLE);
        et_stock_search.setFocusable(true);
    }

    @Override
    public void onCrossPressed() {

        items_search.setVisibility(View.VISIBLE);
        img_stock_cross.setVisibility(View.GONE);
        et_stock_search.setText("");
        et_stock_search.clearFocus();
        presenter.getStocks();
        General.hideKeyboard(getActivity());
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
    }

    @NonNull
    @Override
    protected StockFragPresenter createPresenter() {
        return new StockFragPresenter(this);
    }
}
