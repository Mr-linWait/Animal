package com.hellen.enum_;

public enum MessageState {
    Delivery(0),//送达
    Read(1);//已读

    private int code;
    private MessageState(int code){
        this.code=code;
    }
}
