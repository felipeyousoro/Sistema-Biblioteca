package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.Aluno;
import org.example.sistemaemprestimo.models.Emprestimo;
import org.example.sistemaemprestimo.models.ItemEmprestimo;
import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.repository.dao.EmprestimoDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO;
    private ItemEmprestimoService itemEmprestimoService;
    private AlunoService alunoService;

    public EmprestimoService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.emprestimoDAO = daoFactory.getEmprestimoDAO();
        this.itemEmprestimoService = new ItemEmprestimoService();
        this.alunoService = new AlunoService();
    }

    public Emprestimo getEmprestimo(int idEmprestimo) {
        Emprestimo e = emprestimoDAO.findByPrimaryKey(idEmprestimo);
//        e.setItens(itemEmprestimoService.getByIdEmprestimo(idEmprestimo));
        return e;
    }

    public Emprestimo emprestar(Aluno aluno, List<Livro> livros) {
        Emprestimo e = new Emprestimo();
        e.setDataEmprestimo(new Date());
        e.setAlunoMatricula(aluno.getMatricula());

        List<ItemEmprestimo> itens = new ArrayList<>();
        for (Livro livro : livros) {
            ItemEmprestimo item = new ItemEmprestimo();
            item.setLivro(livro);
            itens.add(item);
        }
        e.setItens(itens);

        Date dataDevolucao = calcularDataDevolucao(e);

        e.setDataPrevistaEntrega(dataDevolucao);
        emprestimoDAO.insert(e);

        // Salva os itens
        for (ItemEmprestimo item : e.getItens()) {
            item.setDataPrevistaEntrega(dataDevolucao);
            item.setIdEmprestimo(e.getId());
            itemEmprestimoService.emprestar(item);
        }

        return e;
    }

    public void devolver(List<Livro> livros, Date dataDevolucao) {
        for (Livro livro : livros) {
            ItemEmprestimo item = itemEmprestimoService.getByLivroNaoDevolvido(livro.getId());
            item.setLivro(livro);
            if (item != null) {
                itemEmprestimoService.devolver(item, dataDevolucao);
                if (item.isAtrasado()) {
                    int diferenca = item.getDiferencaDevolucao();
                    Emprestimo emprestimo = getEmprestimo(item.getIdEmprestimo());
                    multar(emprestimo, diferenca);
                }
            }
        }
    }

    private void multar(Emprestimo emprestimo, int dias) {
        Aluno aluno = alunoService.getByMatricula(emprestimo.getAlunoMatricula());
        int valor = 5 * dias;
        alunoService.criarDebito(aluno, valor);
        emprestimo.setMulta(emprestimo.getMulta() + valor);
        emprestimoDAO.update(emprestimo);
    }

    private Date adicionarDias(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);

        return calendar.getTime();
    }

    private Date calcularDataDevolucao(Emprestimo e) {
        Date dataPrevistaEntrega = new Date();

        List<ItemEmprestimo> itens = e.getItens();

        for (ItemEmprestimo itemEmprestimo : itens) {
            Date dataAux = itemEmprestimoService.calcularDataPrevistaEntrega(itemEmprestimo, new Date());
            if (dataPrevistaEntrega.compareTo(dataAux) < 0) {
                dataPrevistaEntrega = dataAux;
            }
        }

        if (itens.size() > 2) {
            int tam = 2 * (itens.size() - 2);
            Date dataAux = adicionarDias(dataPrevistaEntrega, tam);
            dataPrevistaEntrega = dataAux;
        }

        return dataPrevistaEntrega;
//        for (ItemEmprestimo itemEmprestimo : itens) {
//            itemEmprestimo.setDataPrevistaEntrega(dataPrevistaEntrega);
//        }
//
//        e.setDataPrevistaEntrega(dataPrevistaEntrega);
//        e.setItens(itens);
    }

}
