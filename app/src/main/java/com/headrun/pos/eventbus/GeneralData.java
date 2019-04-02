package com.headrun.pos.eventbus;

public class GeneralData {

    public static enum GeneralEvent {
        ITEM_UPDATE
    }
    private String msg;
    private GeneralEvent generalEvent;

    public GeneralData(GeneralEvent playEvent, String msg) {
        this.msg = msg;
        this.generalEvent = playEvent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GeneralEvent getGeneralEvent() {
        return generalEvent;
    }

    public void setGeneralEvent(GeneralEvent generalEvent) {
        this.generalEvent = generalEvent;
    }
}
