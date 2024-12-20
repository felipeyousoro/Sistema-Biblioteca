package org.example.sistemaemprestimo.models;

import java.util.Calendar;
import java.util.Date;

public class ItemEmprestimo {
    private int idEmprestimo;
    private int idLivro;
    private Date dataDevolucao;
    private Date dataPrevistaEntrega;

    private Livro livro;

    public ItemEmprestimo() {
    }

    public int getIdEmprestimo() { return idEmprestimo; }
    public void setIdEmprestimo(int idEmprestimo) { this.idEmprestimo = idEmprestimo; }

    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public Date getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Date dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public Date getDataPrevistaEntrega() { return dataPrevistaEntrega; }
    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) { this.dataPrevistaEntrega = dataPrevistaEntrega; }

    public boolean isAtrasado() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPrevistaEntrega);
        long dataPrevistaEntrega = calendar.getTimeInMillis();
        calendar.setTime(dataDevolucao);
        long dataDevolucao = calendar.getTimeInMillis();
        return dataDevolucao > dataPrevistaEntrega;
    }

    public int getDiferencaDevolucao() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPrevistaEntrega);
        long dataPrevistaEntrega = calendar.getTimeInMillis();
        calendar.setTime(dataDevolucao);
        long dataDevolucao = calendar.getTimeInMillis();
        long diferenca = dataDevolucao - dataPrevistaEntrega;
        return (int) (diferenca / (1000 * 60 * 60 * 24));
    }
}