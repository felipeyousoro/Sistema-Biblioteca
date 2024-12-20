package org.example.sistemaemprestimo.repository.dao;

import org.example.sistemaemprestimo.models.ItemEmprestimo;

import java.util.List;

public interface ItemEmprestimoDAO extends GenericDAO<ItemEmprestimo> {
//    List<ItemEmprestimo> findByIdEmprestimo(int idEmprestimo);

    ItemEmprestimo findByIdLivroNaoDevolvido(int idLivro);
}
