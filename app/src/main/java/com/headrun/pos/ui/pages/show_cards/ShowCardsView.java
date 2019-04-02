package com.headrun.pos.ui.pages.show_cards;

import com.headrun.pos.model.ScratchModel;
import com.headrun.pos.ui.pages.base.BaseView;

import java.util.List;

public interface ShowCardsView extends BaseView {

    void hideProgressBar();
    void showCards(List<ScratchModel> list);
}
