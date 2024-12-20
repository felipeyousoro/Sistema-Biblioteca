package org.example.sistemaemprestimo.repository.postgres;

import org.example.sistemaemprestimo.models.Area;
import org.example.sistemaemprestimo.repository.dao.AreaDAO;
import org.example.sistemaemprestimo.repository.factory.PostgresConnection;
import org.example.sistemaemprestimo.repository.factory.PostgresDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgresAreaDAO implements AreaDAO {

    @Override
    public void insert(Area entity) {
        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO area (nome, descricao) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getDescricao());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Area entity) {
    }

    @Override
    public boolean delete(Area entity) {
        return false;
    }

    @Override
    public List<Area> findAll() {
        return List.of();
    }

    @Override
    public Area findByPrimaryKey(Object key) {
        Area area = null;

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT * FROM area WHERE nome = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, (String) key);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                area = new Area();
                area.setNome(resultSet.getString("nome"));
                area.setDescricao(resultSet.getString("descricao"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return area;
    }

    public List<Area> findAreasIsbn(String isbn) {
        List<Area> areas = new ArrayList<>();
        
        
        

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "SELECT a.nome, a.descricao from area a JOIN tituloareas ta ON a.nome = ta.area WHERE ta.isbn = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Area area = new Area();
                area.setNome(resultSet.getString("nome"));
                area.setDescricao(resultSet.getString("descricao"));
                areas.add(area);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return areas;
    }

    public void insertTituloArea(String isbn, String area) {
        
        

        try {
            Connection connection = PostgresConnection.getConnection();

            String sql = "INSERT INTO tituloareas (isbn, area) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, isbn);
            statement.setString(2, area);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
