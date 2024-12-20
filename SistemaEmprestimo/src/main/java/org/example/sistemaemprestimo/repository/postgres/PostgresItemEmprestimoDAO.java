package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.ItemEmprestimo;
import org.example.sistemaemprestimo.repository.dao.ItemEmprestimoDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresItemEmprestimoDAO implements ItemEmprestimoDAO {

    @Override
    public void insert(ItemEmprestimo entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO ItemEmprestimo (idemprestimo, idlivro, dataprevistaentrega) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getIdEmprestimo());
            statement.setInt(2, entity.getLivro().getId());
            statement.setDate(3, new java.sql.Date(entity.getDataPrevistaEntrega().getTime()));
            
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ItemEmprestimo entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "UPDATE ItemEmprestimo SET datadevolucao = ? WHERE idemprestimo = ? AND idlivro = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, new java.sql.Date(entity.getDataDevolucao().getTime()));
            statement.setInt(2, entity.getIdEmprestimo());
            statement.setInt(3, entity.getLivro().getId());

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(ItemEmprestimo entity) {
        return false;
    }

    @Override
    public List<ItemEmprestimo> findAll() {
        return List.of();
    }

    @Override
    public ItemEmprestimo findByPrimaryKey(Object key) {
        return null;
    }

    public List<ItemEmprestimo> findByIdEmprestimo(int idEmprestimo) {
        List<ItemEmprestimo> itensEmprestimo = new ArrayList<>();
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * FROM ItemEmprestimo WHERE idemprestimo = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEmprestimo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
                itemEmprestimo.setIdEmprestimo(resultSet.getInt("idemprestimo"));
                itemEmprestimo.setIdLivro(resultSet.getInt("idlivro"));
                itemEmprestimo.setDataPrevistaEntrega(resultSet.getDate("dataprevistaentrega"));
                itemEmprestimo.setDataDevolucao(resultSet.getDate("datadevolucao"));

                itensEmprestimo.add(itemEmprestimo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itensEmprestimo;
    }

    public ItemEmprestimo findByIdLivroNaoDevolvido(int idLivro) {
        ItemEmprestimo itemEmprestimo = null;
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * FROM ItemEmprestimo WHERE idlivro = ? AND datadevolucao IS NULL";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idLivro);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                itemEmprestimo = new ItemEmprestimo();
                itemEmprestimo.setIdEmprestimo(resultSet.getInt("idemprestimo"));
                itemEmprestimo.setIdLivro(resultSet.getInt("idlivro"));
                itemEmprestimo.setDataPrevistaEntrega(resultSet.getDate("dataprevistaentrega"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemEmprestimo;
    }
}
