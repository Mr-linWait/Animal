package com.hellen.enum_;


public enum AnimalState {
    send(0),
    search(1);

    private int code;

    private AnimalState(int code){
        this.code=code;
    }
}
