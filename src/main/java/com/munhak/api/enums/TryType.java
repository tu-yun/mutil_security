package com.munhak.api.enums;

public enum TryType {

    success("성공"),
    fail("실패")
    ;

    final private String name;

    public String getName() {
        return name;
    }

    private TryType(String name){
        this.name = name;
    }
}
