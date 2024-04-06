package com.hellen.enum_;

public enum UserType {

    Admin(1),

    Animal(3),
    User(2);

    private int code;
    private UserType (int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
