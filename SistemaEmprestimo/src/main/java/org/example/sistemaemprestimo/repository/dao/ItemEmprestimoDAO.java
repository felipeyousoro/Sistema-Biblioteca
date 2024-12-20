package org.example.sistemaemprestimo.repository.dao;

import org.example.sistemaemprestimo.models.ItemEmprestimo;

import java.util.List;

public interface ItemEmprestimoDAO extends GenericDAO<ItemEmprestimo> {
    ItemEmprestimo findByIdLivroNaoDevolvido(int idLivro);
}
