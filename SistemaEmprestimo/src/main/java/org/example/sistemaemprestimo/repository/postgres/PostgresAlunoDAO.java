package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Aluno;
import org.example.sistemaemprestimo.repository.dao.AlunoDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresAlunoDAO implements AlunoDAO {

    @Override
    public void insert(Aluno entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO Aluno (matricula, nome, cpf, endereco) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, entity.getMatricula());
            statement.setString(2, entity.getNome());
            statement.setString(3, entity.getCpf());
            statement.setString(4, entity.getEndereco());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Aluno entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "UPDATE Aluno SET nome = ?, cpf = ?, endereco = ? WHERE matricula = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getCpf());
            statement.setString(3, entity.getEndereco());
            statement.setInt(4, entity.getMatricula());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Aluno entity) {
        return false;
    }

    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();

        try {

            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT matricula, nome, cpf, endereco FROM Aluno";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(resultSet.getInt("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setEndereco(resultSet.getString("endereco"));
                alunos.add(aluno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public Aluno findByPrimaryKey(Object key) {
        Aluno aluno = null;

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT matricula, nome, cpf, endereco FROM Aluno WHERE matricula = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                aluno = new Aluno();
                aluno.setMatricula(resultSet.getInt("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setEndereco(resultSet.getString("endereco"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aluno;
    }
}
