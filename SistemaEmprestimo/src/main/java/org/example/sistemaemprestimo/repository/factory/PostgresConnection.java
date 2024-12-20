package org.example.sistemaemprestimo.repository.factory;

import java.sql.Connection;

public class PostgresConnection {
    private static final String DBURL = "jdbc:postgresql://localhost:5432/emprestimo";
    private static final String USER = "postgres";
    private static final String PASS = "makikotori";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = java.sql.DriverManager.getConnection(DBURL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
