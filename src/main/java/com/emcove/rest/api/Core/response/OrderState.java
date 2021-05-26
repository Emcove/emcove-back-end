package com.emcove.rest.api.Core.response;

public enum OrderState {
    PENDIENTE("PENDIENTE", "Pendiente de aprobación"),
    EN_PREPARACION("EN_PREPARACION", "En preparación"),
    LISTO_PARA_ENTREGAR("LISTO_PARA_ENTREGAR", "Listo para engregar"),
    ENTREGADO("ENTREGADO", "Entregado"),
    CANCELADO("CANCELADO", "Cancelado");

    OrderState(String name, String description) {
        this.name = name;
        this.description = description;
    }
    private String name;
    private String description;

}