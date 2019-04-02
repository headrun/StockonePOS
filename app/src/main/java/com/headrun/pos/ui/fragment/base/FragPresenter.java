package com.headrun.pos.ui.fragment.base;


public abstract class FragPresenter <View extends FragView> {

    protected View view;

    protected FragPresenter(View view) {
        this.view = view;
    }

    void destroyView() {
        //avoid memory leak
        view = null;
    }

}
