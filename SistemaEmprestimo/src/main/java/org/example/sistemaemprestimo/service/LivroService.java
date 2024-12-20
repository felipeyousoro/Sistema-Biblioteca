package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.models.Titulo;
import org.example.sistemaemprestimo.repository.dao.LivroDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.List;

public class LivroService {
    private LivroDAO livroDAO;
    private TituloService tituloService;

    public LivroService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.livroDAO = daoFactory.getLivroDAO();
        this.tituloService = new TituloService();
    }

    public List<Livro> getAllLivros() {
        List<Livro> livros = livroDAO.findAll();

        for (Livro livro : livros) {
            Titulo titulo = tituloService.getTitulo(livro.getIsbn());
            livro.setTitulo(titulo);
        }

        return livros;
    }

    public Livro getLivroById(Integer id) {
        Livro livro = livroDAO.findByPrimaryKey(id);
        livro.setTitulo(tituloService.getTitulo(livro.getIsbn()));
        return livro;
    }

    public void criarLivro(Livro livro) {
        livro.setIsbn(livro.getTitulo().getIsbn());
        tituloService.criarTitulo(livro.getTitulo());
        livroDAO.insert(livro);
    }

    public void atualizarLivro(Livro livro) {
        livroDAO.update(livro);
    }

}
