package com.hellen.enum_;

public enum MessageState {
    Delivery("Delivery"),//送达
    Read("Read");//已读

    private String code;
    private MessageState(String code){
        this.code=code;
    }
}
