package org.example.sistemaemprestimo.models;

import java.util.Date;

public class Debito {
    private int alunoMatricula;
    private int valor;
    private Date data;

    public int getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(int alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
}