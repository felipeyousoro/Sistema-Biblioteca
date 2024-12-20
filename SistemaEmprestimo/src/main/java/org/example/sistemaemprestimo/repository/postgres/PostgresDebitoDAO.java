package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Debito;
import org.example.sistemaemprestimo.repository.dao.DebitoDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresDebitoDAO implements DebitoDAO {

    @Override
    public void insert(Debito entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO DEBITO (alunoMatricula, valor, data) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getAlunoMatricula());
            statement.setInt(2, entity.getValor());
            statement.setDate(3, new java.sql.Date(entity.getData().getTime()));

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Debito entity) {
    }

    @Override
    public boolean delete(Debito entity) {
        return false;
    }

    @Override
    public List<Debito> findAll() {
        return List.of();
    }

    @Override
    public Debito findByPrimaryKey(Object key) {
        return null;
    }

    public List<Debito> findByAluno(int alunoMatricula) {
        List<Debito> debitos = new ArrayList<>();
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * from DEBITO WHERE alunoMatricula = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, alunoMatricula);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Debito Debito = new Debito();
                Debito.setData(resultSet.getDate("data"));
                Debito.setValor(resultSet.getInt("valor"));
                Debito.setAlunoMatricula(resultSet.getInt("alunoMatricula"));
                debitos.add(Debito);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return debitos;
    }



}
