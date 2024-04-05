package br.com.vainaweb.vainaescola.enums;

public enum Cargo {
    INSTRUCTOR("Instrutor"),
    FACILITADOR("Facilitador"),
    COORDENADOR("Coordenador"),
    ADMINISTRADOR("Administrador");

    private String cargo;

    Cargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
