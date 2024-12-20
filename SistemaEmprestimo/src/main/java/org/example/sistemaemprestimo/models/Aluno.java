package org.example.sistemaemprestimo.models;

import java.util.List;

public class Aluno {
    private int matricula;
    private String nome;
    private String cpf;
    private String endereco;

    private List<Debito> debitos;

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Debito> getDebitos() { return debitos; }
    public void setDebitos(List<Debito> debitos) { this.debitos = debitos; }
}
