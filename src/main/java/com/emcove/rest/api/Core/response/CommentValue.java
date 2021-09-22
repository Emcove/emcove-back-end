package com.emcove.rest.api.Core.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

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

    private final String name;
    private final Integer value;
    @JsonValue
    public int getValue() {
        return value;
    }
    public String getName() { return name; }

    @JsonCreator
    public static CommentValue getCommentValueFromName(Integer value){
        return Stream.of(CommentValue.values()).filter(targetCommentValue -> targetCommentValue.value.equals(value)).findFirst().orElse(null);
    }
}
