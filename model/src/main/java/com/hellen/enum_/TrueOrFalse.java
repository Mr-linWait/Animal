package com.hellen.enum_;

public enum TrueOrFalse {

    True("True"),
    False("False");

    private String code;

    private TrueOrFalse (String code){
        this.code=code;
    }
}
