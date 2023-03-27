package com.salesianostriana.pdam.inmoboscoapi.models;


public enum Type {

    CASA("Casa"), PISO("Piso"), CHALET("Chalet");

    private final String text;

    private Type(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
