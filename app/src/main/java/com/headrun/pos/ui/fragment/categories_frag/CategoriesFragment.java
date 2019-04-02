package com.headrun.pos.ui.fragment.categories_frag;


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
import com.headrun.pos.adapter.CategoryAdapter;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.callback.OnCategorySelected;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.fragment.base.BaseFragment;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.create_categories.CreateCategory;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends BaseFragment<CategoriesFragPresenter> implements CategoriesFragView, OnCategorySelected {

    @BindView(R.id.rv_categories) RecyclerView recyclerView;
    @BindView(R.id.avi_frag_cat) AVLoadingIndicatorView avi;
    @BindView(R.id.et_search_categories) EditText et_search_categories;
    @BindView(R.id.items_search) ImageView items_search;
    @BindView(R.id.img_cross) ImageView img_cross;
    CategoryAdapter adapter;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CategoryAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);

        searchableEdittext();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCategories();
    }

    @OnClick(R.id.items_add_category)
    void onItemCategoryAdded(){

        startActivity(new Intent(getContext(), CreateCategory.class));
    }

    @Override
    public void categoryName(String cat_name) {

    }

    private void searchableEdittext(){

        et_search_categories.addTextChangedListener(new TextWatcher() {
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

    @OnClick(R.id.et_search_categories)
    void onSearchClicked() {

        presenter.onSearchPressed();

    }

    @OnClick(R.id.img_cross)
    void onImageeCross(){

        presenter.onCrossPressed();
    }

    @NonNull
    @Override
    protected CategoriesFragPresenter createPresenter() {
        return new CategoriesFragPresenter(this);
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
    }

    @Override
    public void showCategories(List<CategoryModel> list) {

        adapter.setList(list);

    }

    @Override
    public void onSearchPressed() {

        et_search_categories.requestFocus();
        items_search.setVisibility(View.INVISIBLE);
        img_cross.setVisibility(View.VISIBLE);
        et_search_categories.setFocusable(true);
    }

    @Override
    public void onCrossPressed() {

        items_search.setVisibility(View.VISIBLE);
        img_cross.setVisibility(View.GONE);
        et_search_categories.setText("");
        et_search_categories.clearFocus();
        presenter.getCategories();
        General.hideKeyboard(getActivity());
    }
}
