package com.hellen.enum_;

public enum ApplyState {
    Applying("Applying"),
    Pass("Pass"),
    OTHER_PASS("OtherPass"),
    REJECT("Reject");

    private String code;

    private ApplyState(String code) {
        this.code = code;
    }
}
