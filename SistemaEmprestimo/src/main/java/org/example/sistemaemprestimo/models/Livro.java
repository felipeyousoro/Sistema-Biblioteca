package org.example.sistemaemprestimo.models;

public class Livro {
    private int id;
    private String isbn;
    private boolean exemplarBiblioteca;
    private boolean disponivel;

    private Titulo titulo;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Titulo   getTitulo() { return titulo; }
    public void setTitulo(Titulo titulo) { this.titulo = titulo; }

    public boolean isExemplarBiblioteca() { return exemplarBiblioteca; }
    public void setExemplarBiblioteca(boolean exemplarBiblioteca) { this.exemplarBiblioteca = exemplarBiblioteca; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public int verPrazo() {
        return titulo.getPrazo();
    }

}