package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Autor;
import org.example.sistemaemprestimo.repository.dao.AutorDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresAutorDAO implements AutorDAO {

    @Override
    public void insert(Autor entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO Autor (nome, sobrenome, titulacao) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getSobrenome());
            statement.setString(3, entity.getTitulacao());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Autor entity) {
    }

    @Override
    public boolean delete(Autor entity) {
        return false;
    }

    @Override
    public List<Autor> findAll() {
        return List.of();
    }

    @Override
    public Autor findByPrimaryKey(Object key) {
        Autor autor = null;

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * FROM Autor WHERE nome = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, (String) key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                autor = new Autor();
                autor.setNome(resultSet.getString("nome"));
                autor.setSobrenome(resultSet.getString("sobrenome"));
                autor.setTitulacao(resultSet.getString("titulacao"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autor;
    }

    public List<Autor> findAutoresIsbn(String isbn) {
        List<Autor> autores = new ArrayList<>();

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT a.nome, a.sobrenome, a.titulacao from Autor a JOIN tituloautores ta ON a.nome = ta.autor WHERE ta.isbn = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Autor autor = new Autor();
                autor.setNome(resultSet.getString("nome"));
                autor.setSobrenome(resultSet.getString("sobrenome"));
                autor.setTitulacao(resultSet.getString("titulacao"));
                autores.add(autor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autores;
    }

    public void insertTituloAutor(String isbn, String autor) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO tituloautores (isbn, autor) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, isbn);
            statement.setString(2, autor);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
