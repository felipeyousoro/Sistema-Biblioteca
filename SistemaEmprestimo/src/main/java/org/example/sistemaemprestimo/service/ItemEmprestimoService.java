package org.example.sistemaemprestimo.service;

import org.example.sistemaemprestimo.models.ItemEmprestimo;
import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.repository.dao.ItemEmprestimoDAO;
import org.example.sistemaemprestimo.repository.factory.DAOFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ItemEmprestimoService {
    private ItemEmprestimoDAO itemEmprestimoDAO;
    private LivroService livroService;

    public ItemEmprestimoService() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRES);
        this.itemEmprestimoDAO = daoFactory.getItemEmprestimoDAO();
        this.livroService = new LivroService();
    }

//    public List<ItemEmprestimo> getByIdEmprestimo(int idEmprestimo) {
//        return itemEmprestimoDAO.findByIdEmprestimo(idEmprestimo);
//    }

    public ItemEmprestimo getByLivroNaoDevolvido(int idLivro) {
        return itemEmprestimoDAO.findByIdLivroNaoDevolvido(idLivro);
    }

    // Função para salvar nested o item de emprestimo
    public void emprestar(ItemEmprestimo item) {
        item.getLivro().setDisponivel(false);
        livroService.atualizarLivro(item.getLivro());

        itemEmprestimoDAO.insert(item);
    }

    public void devolver(ItemEmprestimo item, Date dataDevolucao) {
        item.getLivro().setDisponivel(true);
        livroService.atualizarLivro(item.getLivro());

        item.setDataDevolucao(dataDevolucao);
        itemEmprestimoDAO.update(item);
    }

    public Date calcularDataPrevistaEntrega(ItemEmprestimo ie, Date data) {
        Livro livro = ie.getLivro();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, livro.verPrazo());
        Date dataPrevistaEntrega = calendar.getTime();

        ie.setDataPrevistaEntrega(dataPrevistaEntrega);

        return dataPrevistaEntrega;
    }

}
