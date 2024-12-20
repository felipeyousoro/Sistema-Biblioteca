package org.example.sistemaemprestimo.repository.factory;

import org.example.sistemaemprestimo.repository.dao.*;

public abstract class DAOFactory {
    public static final int POSTGRES = 1;

    public static DAOFactory getDAOFactory(int type) {
        switch (type) {
            case POSTGRES:
                return new PostgresDAOFactory();
            default:
                throw new IllegalArgumentException("Invalid DAOFactory type");
        }
    }

    public abstract LivroDAO getLivroDAO();

    public abstract AreaDAO getAreaDAO();

    public abstract AutorDAO getAutorDAO();

    public abstract TituloDAO getTituloDAO();

    public abstract EmprestimoDAO getEmprestimoDAO();

    public abstract ItemEmprestimoDAO getItemEmprestimoDAO();

    public abstract AlunoDAO getAlunoDAO();

    public abstract DebitoDAO getDebitoDAO();
}
