package com.hellen.enum_;

public enum TrueOrFalse {

    TRUE(0),
    False(1);

    private int code;

    private TrueOrFalse (int code){
        this.code=code;
    }
}
