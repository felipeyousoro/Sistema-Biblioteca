package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Titulo;
import org.example.sistemaemprestimo.repository.dao.TituloDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PostgresTituloDAO implements TituloDAO {

    @Override
    public void insert(Titulo entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO Titulo (isbn, nome, ano, edicao, prazo) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getIsbn());
            statement.setString(2, entity.getNome());
            statement.setInt(3, entity.getAno());
            statement.setInt(4, entity.getEdicao());
            statement.setInt(5, entity.getPrazo());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Titulo entity) {
    }

    @Override
    public boolean delete(Titulo entity) {
        return false;
    }

    @Override
    public List<Titulo> findAll() {
        return List.of();
    }

    @Override
    public Titulo findByPrimaryKey(Object key) {
        Titulo titulo = null;
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * from titulo WHERE isbn = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, (String) key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                titulo = new Titulo();
                titulo.setAno(resultSet.getInt("ano"));
                titulo.setEdicao(resultSet.getInt("edicao"));
                titulo.setIsbn(resultSet.getString("isbn"));
                titulo.setNome(resultSet.getString("nome"));
                titulo.setPrazo(resultSet.getInt("prazo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return titulo;
    }



}
