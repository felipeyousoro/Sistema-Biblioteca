package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Autor;
import org.example.sistemaemprestimo.repository.dao.AutorDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.List;

public class AutorService {
    private AutorDAO autorDAO;

    public AutorService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.autorDAO = daoFactory.getAutorDAO();
    }

    public Autor getAutor(String nome) {
        return autorDAO.findByPrimaryKey(nome);
    }

    public List<Autor> getAutoresByIsbn(String isbn) {
        return autorDAO.findAutoresIsbn(isbn);
    }

    public void vincularTitulo(String isbn, Autor autor) {
        if (autorDAO.findByPrimaryKey(autor.getNome()) == null) {
            autorDAO.insert(autor);
        }

        autorDAO.insertTituloAutor(isbn, autor.getNome());
    }
}
