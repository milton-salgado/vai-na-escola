package br.com.vainaweb.vainaescola.enums;

public enum Curso {
    INFORMATICA("Informática"),
    ENGENHARIA("Engenharia"),
    ADMINISTRACAO("Administração"),
    DIREITO("Direito"),
    MEDICINA("Medicina");

    private String curso;

    Curso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
}
