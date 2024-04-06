package com.hellen.enum_;

public enum ApplyState {
    Applying(1),
    Pass(2);

    private int code;

    private ApplyState (int code){
        this.code=code;
    }
}
