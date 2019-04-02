package com.headrun.pos.ui.pages.create_categories;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.headrun.pos.R;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.CategoryModel;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCategory extends BaseActivity<CreateCatPresenter> implements CreateCatView {

    @BindView(R.id.toolbar_create_category) Toolbar toolbar;
    @BindView(R.id.et_create_category) EditText create_category;
    @BindView(R.id.btn_create_category) Button btn_create_category;

    @NonNull
    @Override
    protected CreateCatPresenter createPresenter() {
        return new CreateCatPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_create_category)
    void onCategoryCreated(){

        presenter.createCategory();

    }

    @Override
    public boolean validateText() {

        String category = create_category.getText().toString();

        if (TextUtils.isEmpty(category)){

            ToastUtils.showLong("Please enter category");
            return false;
        }

        return true;
    }

    @Override
    public void changeViews() {

        ToastUtils.showLong("Category added");
        btn_create_category.setText("Save");
        create_category.getText().clear();
        create_category.requestFocus();

    }

    @Override
    public String categoryName() {
        return create_category.getText().toString();
    }

    @Override
    public void changeBtnText() {
        btn_create_category.setText("Please wait...");
    }

}
