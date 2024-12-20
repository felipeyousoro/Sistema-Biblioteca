package org.example.sistemaemprestimo.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {
    private int id;
    private int alunoMatricula;
    private int multa;
    private Date dataEmprestimo;
    private Date dataPrevistaEntrega;

    private Aluno aluno;
    private List<ItemEmprestimo> itens;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoMatricula() { return alunoMatricula; }
    public void setAlunoMatricula(int alunoMatricula) { this.alunoMatricula = alunoMatricula; }

    public int getMulta() { return multa; }
    public void setMulta(int multa) { this.multa = multa; }

    public Date getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(Date dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public Date getDataPrevistaEntrega() { return dataPrevistaEntrega; }
    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) { this.dataPrevistaEntrega = dataPrevistaEntrega; }

    public List<ItemEmprestimo> getItens() { return itens; }
    public void setItens(List<ItemEmprestimo> itens) { this.itens = itens; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}