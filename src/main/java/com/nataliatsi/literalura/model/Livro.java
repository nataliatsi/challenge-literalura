package com.nataliatsi.literalura.model;

import java.util.List;

public class Livro {
    private Long id;
    private String titulo;
    private List<Autor> autorList;
    private Idioma idiomaEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    public Idioma getIdiomaEnum() {
        return idiomaEnum;
    }

    public void setIdiomaEnum(Idioma idiomaEnum) {
        this.idiomaEnum = idiomaEnum;
    }

    @Override
    public String toString() {
        return "Livro {" +
                " Id: " + id +
                ", Título: '" + titulo + '\'' +
                ", Autor(es): " + (autorList != null ? autorList.toString() : "Nenhum") +
                ", Idioma(s): " + (idiomaEnum != null ? idiomaEnum.toString() : "Desconhecido") +
                '}';
    }
}
