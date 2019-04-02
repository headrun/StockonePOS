package com.headrun.pos.ui.pages.show_cards;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.headrun.pos.R;
import com.headrun.pos.adapter.ScratchCardAdapter;
import com.headrun.pos.application.MyApplication;
import com.headrun.pos.model.ScratchModel;
import com.headrun.pos.ui.pages.base.BaseActivity;
import com.headrun.pos.utils.Constants;
import com.headrun.pos.utils.General;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import spencerstudios.com.bungeelib.Bungee;

public class ShowCards extends BaseActivity<ShowCardsPresenter> implements ShowCardsView {

    @BindView(R.id.rv_scratch_cards) RecyclerView recyclerView;
    @BindView(R.id.toolbar_scratch_card) Toolbar toolbar;
    @BindView(R.id.avi_cards) AVLoadingIndicatorView avi;
    ScratchCardAdapter adapter;

    @NonNull
    @Override
    protected ShowCardsPresenter createPresenter() {
        return new ShowCardsPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);
        ButterKnife.bind(this);
        General.setToolbar(this, toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScratchCardAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter.getCouponCodes();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Bungee.slideRight(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideProgressBar() {

        avi.hide();
    }

    @Override
    public void showCards(List<ScratchModel> list) {

        adapter.setList(list);
    }


}
