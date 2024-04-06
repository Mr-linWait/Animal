package com.hellen.enum_;

public enum MessageType {

    IMG(1),
    TEXT(0);
    private int code;
    private MessageType(int code){
        this.code=code;
    }
}
