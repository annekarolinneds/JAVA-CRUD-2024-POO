package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String status = "Não conectou...";

    private static Connection connection = null;

    public static final String URL;

    private static final String USER;

    private static final String PASSWORD;

    static {
        URL = "jdbc:mysql://localhost:3306/" + Dao.DB;
        USER = "root";
        PASSWORD = "1234567";
    }

    private DbConnection() {
    }

    public static String getStatus() {
        return status;
    }

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            status = connection != null ? "STATUS ---> Conectado com sucesso!" : "STATUS ---> Não foi possível realizar conexão";
        } catch (SQLException e) {
            status = "STATUS ---> Falha na conexão com o banco de dados";
            System.err.println(status);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
