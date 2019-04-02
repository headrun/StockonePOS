package com.headrun.pos.callback;

import java.util.List;
import java.util.Map;

public interface OnItemClicked {

    void onItemName(Map<String, Object> map);

    void onItemTransaction(Map<String, Object> map);
}
