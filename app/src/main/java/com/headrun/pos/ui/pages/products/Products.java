package com.headrun.pos.ui.pages.products;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.headrun.pos.R;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.General;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Products extends BaseActivity<ProductsPresenter> implements ProductsView {

    @BindView(R.id.toolbar_product) Toolbar toolbar;
    @BindView(R.id.product_tab) TabLayout tabLayout;
    @BindView(R.id.product_viewpager) ViewPager viewPager;

    @NonNull
    @Override
    protected ProductsPresenter createPresenter() {
        return new ProductsPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);
        presenter.setupViewPager(viewPager, this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
