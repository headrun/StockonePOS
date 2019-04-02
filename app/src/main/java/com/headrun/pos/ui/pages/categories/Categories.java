package com.headrun.pos.ui.pages.categories;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.headrun.pos.R;
import com.headrun.pos.adapter.CategoryAdapter;
import com.headrun.pos.callback.OnCategorySelected;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.utils.General;
import com.headrun.pos.ui.pages.create_categories.CreateCategory;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Categories extends BaseActivity<CategoriesPresenter> implements OnCategorySelected, CategoriesView {

    @BindView(R.id.toolbar_categories) Toolbar toolbar;
    @BindView(R.id.rv_cat) RecyclerView recyclerView;
    @BindView(R.id.avi_cat) AVLoadingIndicatorView avi;
    CategoryAdapter adapter;

    @NonNull
    @Override
    protected CategoriesPresenter createPresenter() {
        return new CategoriesPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllCategories();
    }

    @OnClick(R.id.items_add_category)
    void onItemAddCat(){

        startActivity(new Intent(this, CreateCategory.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void categoryName(String cat_name) {

        Intent intent = new Intent();
        intent.putExtra("cat_name",cat_name);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    public void onHideProgressBar() {
        avi.hide();
    }

    @Override
    public void showCategories(List<CategoryModel> list) {

        adapter.setList(list);
    }
}
