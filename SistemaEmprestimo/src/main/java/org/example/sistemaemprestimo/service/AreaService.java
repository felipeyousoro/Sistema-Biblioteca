package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Area;
import org.example.sistemaemprestimo.repository.dao.AreaDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.List;

public class AreaService {
    private AreaDAO areaDAO;

    public AreaService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.areaDAO = daoFactory.getAreaDAO();
    }

    public Area getArea(String nome) {
        return areaDAO.findByPrimaryKey(nome);
    }

    public List<Area> getAreasByIsbn(String isbn) {
        return areaDAO.findAreasIsbn(isbn);
    }

    public void vincularTitulo(String isbn, Area area) {
        if (areaDAO.findByPrimaryKey(area.getNome()) == null) {
            areaDAO.insert(area);
        }

        areaDAO.insertTituloArea(isbn, area.getNome());
    }
}

