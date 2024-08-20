package com.munhak.api.enums;

public enum OriginalType {

    email("이메일"),
    video("화상회의"),
    message("메시지")
    ;

    final private String name;

    public String getName() {
        return name;
    }

    private OriginalType(String name){
        this.name = name;
    }
}
