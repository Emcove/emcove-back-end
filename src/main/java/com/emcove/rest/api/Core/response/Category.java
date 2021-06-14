package com.emcove.rest.api.Core.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Category {
    BELLEZA("Belleza", "BELLEZA"),
    ARTESANAL("Artesanal", "ARTESANAL"),
    COCINA("Cocina", "COCINA"),
    SERVICIOS("Servicios", "SERVICIOS"),
    HERRAMIENTAS("Herramientas", "HERRAMIENTAS"),
    DECO("Deco", "DECO");

    private String description;
    private String code;

    public String getDescription() {
        return description;
    }
    @JsonValue
    public String getCode() {
        return code;
    }

    Category(String description, String code ) {
        this.description = description;
        this.code = code;
    }

    @JsonCreator
    public static Category getCetegoryFromCode(String code){
        return Stream.of(Category.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

}
