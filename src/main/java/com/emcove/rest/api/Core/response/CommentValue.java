package com.emcove.rest.api.Core.response;

public enum CommentValue {
    MUY_MALO("MUY_MALO", 1),
    MALO("MALO", 2),
    BUENO("BUENO", 3),
    MUY_BUENO("MUY_BUENO", 4),
    EXCELENTE("EXCELENTE", 5);


    CommentValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private int value;

    public int getValue() {
        return value;
    }
}
