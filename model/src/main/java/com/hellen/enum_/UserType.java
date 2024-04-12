package com.hellen.enum_;

public enum UserType {

    Admin("Admin"),

    Animal("Animal"),
    User("User");

    private String code;
    private UserType (String code){
        this.code=code;
    }

    public String getCode() {
        return code;
    }
}
