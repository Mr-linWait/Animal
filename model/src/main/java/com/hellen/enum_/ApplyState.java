package com.hellen.enum_;

public enum ApplyState {
    Applying("Applying"),
    Pass("Pass");

    private String code;

    private ApplyState (String code){
        this.code=code;
    }
}
