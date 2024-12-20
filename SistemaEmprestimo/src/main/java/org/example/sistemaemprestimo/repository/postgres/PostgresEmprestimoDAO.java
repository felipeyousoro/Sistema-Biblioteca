package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Emprestimo;
import org.example.sistemaemprestimo.repository.dao.EmprestimoDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PostgresEmprestimoDAO implements EmprestimoDAO {

    @Override
    public void insert(Emprestimo entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO emprestimo (alunoMatricula, dataemprestimo, dataprevistaentrega) VALUES (?, ?, ?) RETURNING id";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getAlunoMatricula());
            statement.setDate(2, new java.sql.Date(entity.getDataEmprestimo().getTime()));
            statement.setDate(3, new java.sql.Date(entity.getDataPrevistaEntrega().getTime()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                entity.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Emprestimo entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "UPDATE emprestimo SET multa = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getMulta());
            statement.setInt(2, entity.getId());

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Emprestimo entity) {
        return false;
    }

    @Override
    public List<Emprestimo> findAll() {
        return List.of();
    }

    @Override
    public Emprestimo findByPrimaryKey(Object key) {
        Emprestimo emprestimo = null;
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * FROM emprestimo WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (int) key);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(resultSet.getInt("id"));
                emprestimo.setAlunoMatricula(resultSet.getInt("alunomatricula"));
                emprestimo.setDataEmprestimo(resultSet.getDate("dataemprestimo"));
                emprestimo.setDataPrevistaEntrega(resultSet.getDate("dataprevistaentrega"));
                emprestimo.setMulta(resultSet.getInt("multa"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emprestimo;
    }

}
