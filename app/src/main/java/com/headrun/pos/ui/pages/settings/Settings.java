package com.headrun.pos.ui.pages.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.headrun.pos.R;
import com.headrun.pos.utils.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Settings extends AppCompatActivity {

    @BindView(R.id.toolbar_settings) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.groups_rl)
    void onGropusClicked(){


    }
}
