package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Area;
import org.example.sistemaemprestimo.models.Autor;
import org.example.sistemaemprestimo.models.Titulo;
import org.example.sistemaemprestimo.repository.dao.TituloDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.List;

public class TituloService {
    private TituloDAO tituloDAO;
    private AutorService autorService;
    private AreaService areaService;

    public TituloService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.tituloDAO = daoFactory.getTituloDAO();
        this.autorService = new AutorService();
        this.areaService = new AreaService();
    }

    public Titulo getTitulo(String isbn) {
        Titulo titulo = tituloDAO.findByPrimaryKey(isbn);
        List<Area> areas = areaService.getAreasByIsbn(titulo.getIsbn());
        List<Autor> autores = autorService.getAutoresByIsbn(titulo.getIsbn());

        titulo.setAreas(areas);
        titulo.setAutores(autores);

        return titulo;
    }

    public void criarTitulo(Titulo titulo) {
        tituloDAO.insert(titulo);
        for (Area area : titulo.getAreas()) {
            areaService.vincularTitulo(titulo.getIsbn(), area);
        }

        for (Autor autor : titulo.getAutores()) {
            autorService.vincularTitulo(titulo.getIsbn(), autor);
        }
    }
}
