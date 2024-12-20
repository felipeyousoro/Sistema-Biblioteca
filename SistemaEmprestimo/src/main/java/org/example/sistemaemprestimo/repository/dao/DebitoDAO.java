package org.example.sistemaemprestimo.repository.dao;

import org.example.sistemaemprestimo.models.Debito;

import java.util.List;

public interface DebitoDAO extends GenericDAO<Debito> {
    List<Debito> findByAluno(int alunoMatricula);
}
