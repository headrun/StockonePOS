package com.headrun.pos.ui.pages.products;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.headrun.pos.adapter.ViewPagerAdapter;
import com.headrun.pos.ui.fragment.categories_frag.CategoriesFragment;
import com.headrun.pos.ui.fragment.items_frag.ItemsFragment;
import com.headrun.pos.ui.fragment.stock_frag.StockFragment;
import com.headrun.pos.ui.pages.base.BasePresenter;

public class ProductsPresenter extends BasePresenter<ProductsView> {


    protected ProductsPresenter(ProductsView view) {
        super(view);
    }

    public void setupViewPager(ViewPager viewPager, AppCompatActivity appCompatActivity) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(appCompatActivity.getSupportFragmentManager());
        adapter.addFragment(new ItemsFragment(), "Items");
        adapter.addFragment(new StockFragment(), "Stock");
        adapter.addFragment(new CategoriesFragment(), "Categories");
        viewPager.setAdapter(adapter);
    }
}
