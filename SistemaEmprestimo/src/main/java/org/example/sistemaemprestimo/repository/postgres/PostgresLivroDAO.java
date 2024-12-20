package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.repository.dao.LivroDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresLivroDAO implements LivroDAO {

    @Override
    public void insert(Livro entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO livro (exemplarBiblioteca, disponivel, isbn) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, entity.isExemplarBiblioteca());
            statement.setBoolean(2, true);
            statement.setString(3, entity.getIsbn());

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Livro entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "UPDATE livro SET exemplarBiblioteca = ?, disponivel = ?, isbn = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, entity.isExemplarBiblioteca());
            statement.setBoolean(2, entity.isDisponivel());
            statement.setString(3, entity.getIsbn());
            statement.setInt(4, entity.getId());

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Livro entity) {
        return false;
    }

    @Override
    public List<Livro> findAll() {
        List<Livro> livros = new ArrayList<>();
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * from livro";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Livro livro = new Livro();
                livro.setId(resultSet.getInt("id"));
                livro.setExemplarBiblioteca(resultSet.getBoolean("exemplarBiblioteca"));
                livro.setDisponivel(resultSet.getBoolean("disponivel"));
                livro.setIsbn(resultSet.getString("isbn"));

                livros.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return livros;
    }


    @Override
    public Livro findByPrimaryKey(Object key) {
        Livro livro = null;
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * from livro WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                livro = new Livro();
                livro.setId(resultSet.getInt("id"));
                livro.setExemplarBiblioteca(resultSet.getBoolean("exemplarBiblioteca"));
                livro.setDisponivel(resultSet.getBoolean("disponivel"));
                livro.setIsbn(resultSet.getString("isbn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return livro;
    }

}
