package com.nataliatsi.literalura.model;

public enum Idioma {
    EN("en"),
    ES("es"),
    FR("fr"),
    PT("pt"),
    OTHER("other");

    private final String code;

    Idioma(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Idioma fromString(String code) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.code.equalsIgnoreCase(code)) {
                return idioma;
            }
        }
        return OTHER;
    }
}
