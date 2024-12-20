package org.example.sistemaemprestimo.repository.factory;

import org.example.sistemaemprestimo.repository.dao.*;
import org.example.sistemaemprestimo.repository.postgres.*;

import java.sql.Connection;

public class PostgresDAOFactory extends DAOFactory {
    public LivroDAO getLivroDAO() {
        return new PostgresLivroDAO();
    };

    public AutorDAO getAutorDAO() {
        return new PostgresAutorDAO();
    };

    public TituloDAO getTituloDAO() {
        return new PostgresTituloDAO();
    };

    public AreaDAO getAreaDAO() {
        return new PostgresAreaDAO();
    };

    public EmprestimoDAO getEmprestimoDAO() {
        return new PostgresEmprestimoDAO();
    }

    public ItemEmprestimoDAO getItemEmprestimoDAO() {
        return new PostgresItemEmprestimoDAO();
    }

    public AlunoDAO getAlunoDAO() {
        return new PostgresAlunoDAO();
    }

    public DebitoDAO getDebitoDAO() {
        return new PostgresDebitoDAO();
    }


}
