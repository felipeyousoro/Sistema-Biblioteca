package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Debito;
import org.example.sistemaemprestimo.repository.dao.DebitoDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.Date;
import java.util.List;

public class DebitoService {
    private DebitoDAO debitoDAO;

    public DebitoService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.debitoDAO = daoFactory.getDebitoDAO();
    }

    public List<Debito> getDebitosAluno(int matricula) {
        return debitoDAO.findByAluno(matricula);
    }

    public void criarDebito(int alunoMatricula, int valor) {
        Debito debito = new Debito();
        debito.setValor(valor);
        debito.setAlunoMatricula(alunoMatricula);
        debito.setData(new Date());
        debitoDAO.insert(debito);
    }

}
