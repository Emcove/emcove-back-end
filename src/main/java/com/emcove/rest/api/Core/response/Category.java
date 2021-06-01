package com.emcove.rest.api.Core.response;

public enum Category {
    BELLEZA("Belleza"),
    ARTESANAL("Artesanal"),
    COCINA("Cocina"),
    SERVICIOS("Servicios"),
    HERRAMIENTAS("Herramientas"),
    DECO("Deco");

    private String description;

    Category(String description) {
        this.description = description;
    }
}
