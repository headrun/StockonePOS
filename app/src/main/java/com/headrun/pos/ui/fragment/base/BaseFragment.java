package com.headrun.pos.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public abstract class BaseFragment <Presenter extends FragPresenter> extends Fragment {

    protected Presenter presenter;

    @NonNull
    protected abstract Presenter createPresenter();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }
}

