package com.munhak.api.enums;

public enum MberSttType {

    USE("사용자");

    final private String name;

    public String getName() {
        return name;
    }

    private MberSttType(String name){
        this.name = name;
    }

}
