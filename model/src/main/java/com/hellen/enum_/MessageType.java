package com.hellen.enum_;

public enum MessageType {

    IMG("IMG"),
    TEXT("TEXT");
    private String code;
    private MessageType(String code){
        this.code=code;
    }
}
