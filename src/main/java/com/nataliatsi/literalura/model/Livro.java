package com.nataliatsi.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autor = new ArrayList<>();

    @Enumerated(EnumType.STRING)
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
        return autor;
    }

    public void setAutorList(List<Autor> autor) {
        this.autor = autor;
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
                ", Autor(es): " + (autor != null ? autor.toString() : "Nenhum") +
                ", Idioma(s): " + (idiomaEnum != null ? idiomaEnum.toString() : "Desconhecido") +
                '}';
    }
}
