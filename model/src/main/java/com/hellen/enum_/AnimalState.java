package com.hellen.enum_;


public enum AnimalState {
    send("send"),
    search("search");

    private String code;

    private AnimalState(String code){
        this.code=code;
    }
}
