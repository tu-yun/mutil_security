package com.munhak.api.enums;

public enum FileType {

    voice("음성"),
    video("영상"),
    doc("문서")
    ;

    final private String name;

    public String getName() {
        return name;
    }

    private FileType(String name){
        this.name = name;
    }
}
