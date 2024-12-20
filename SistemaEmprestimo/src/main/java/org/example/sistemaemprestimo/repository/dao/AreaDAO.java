package org.example.sistemaemprestimo.repository.dao;

import org.example.sistemaemprestimo.models.Area;

import java.util.List;

public interface AreaDAO extends GenericDAO<Area> {
    List<Area> findAreasIsbn(String isbn);

    void insertTituloArea(String isbn, String nomeArea);
}
