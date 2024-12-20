package org.example.sistemaemprestimo.models;

import java.util.List;

public class Titulo {
    private String isbn;
    private String nome;
    private int edicao;
    private int ano;
    private int prazo;

    private List<Area> areas;
    private List<Autor> autores;

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getEdicao() { return edicao; }
    public void setEdicao(int edicao) { this.edicao = edicao; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getPrazo() { return prazo; }
    public void setPrazo(int prazo) { this.prazo = prazo; }

    public List<Area> getAreas() { return areas; }
    public void setAreas(List<Area> areas) { this.areas = areas; }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }
}