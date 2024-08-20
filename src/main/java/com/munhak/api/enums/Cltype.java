package com.munhak.api.enums;

public enum Cltype {

    GENERAL("일반"),
    VIP("VIP");

    final private String name;

    public String getName() {
        return name;
    }

    private Cltype(String name){
        this.name = name;
    }

}
