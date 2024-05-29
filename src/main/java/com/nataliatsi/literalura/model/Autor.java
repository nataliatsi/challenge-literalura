package com.nataliatsi.literalura.model;

public class Autor {
    private Integer id;
    private String nome;
    private Integer anoNascimento, anoFalecimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    @Override
    public String toString() {
        return "Autor {" +
                " Id: " + id +
                ", Nome: '" + nome + '\'' +
                ", Ano de Nascimento: " + (anoNascimento != null ? anoNascimento : " Desconhecido ") +
                ", Ano Falecimento: " + (anoFalecimento != null ? anoFalecimento : " Desconhecido ") +
                '}';
    }

}
