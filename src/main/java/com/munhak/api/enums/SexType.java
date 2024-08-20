package com.munhak.api.enums;

public enum SexType {

    MALE("남자"),
    FEMALE("여자");

    final private String name;

    public String getName() {
        return name;
    }

    private SexType(String name){
        this.name = name;
    }


}
