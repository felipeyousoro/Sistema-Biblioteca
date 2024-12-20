package org.example.sistemaemprestimo.repository.dao;

import org.example.sistemaemprestimo.models.Autor;

import java.util.List;

public interface AutorDAO extends GenericDAO<Autor> {
    List<Autor> findAutoresIsbn(String isbn);

    void insertTituloAutor(String isbn, String autor);
}
