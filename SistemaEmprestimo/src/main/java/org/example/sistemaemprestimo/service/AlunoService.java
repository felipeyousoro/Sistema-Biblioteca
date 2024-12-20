package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Aluno;
import org.example.sistemaemprestimo.repository.dao.AlunoDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.List;

public class AlunoService {
    private AlunoDAO alunoDAO;
    private DebitoService debitoService;

    public AlunoService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.alunoDAO = daoFactory.getAlunoDAO();
        this.debitoService = new DebitoService();
    }

    public List<Aluno> getAll() {
        return alunoDAO.findAll();
    }

    public Aluno getByMatricula(int matricula) {
        Aluno aluno = alunoDAO.findByPrimaryKey(matricula);
        if (aluno != null) {
            aluno.setDebitos(debitoService.getDebitosAluno(matricula));
        }

        return aluno;
    }

    public void criarAluno(Aluno aluno) {
        alunoDAO.insert(aluno);
    }

    public void criarDebito(Aluno aluno, int valor) {
        debitoService.criarDebito(aluno.getMatricula(), valor);
    }

    public boolean verificarAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }

        if (!aluno.getDebitos().isEmpty()) {
            return false;
        }

        return true;
    }
}
