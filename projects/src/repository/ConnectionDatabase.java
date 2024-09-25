package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
    private static Connection connection;

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3060/text_interview";
        String root = "root";
        String password = "123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,root,password);
            return connection;
        }
        catch (Exception error) {
            System.out.println(error);
            System.out.println("Erro ao conectar ao banco de dados");
        }

        return null;
    }
}
