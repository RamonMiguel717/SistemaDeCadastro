package repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/*
A classe abaixo tem a função de realizar a conexão com o banco de dados local do meu computador.

a função getConnection tem exatamente a função do nome dela, envia por meio do comando "DriverManager" as informações
reunidas.
 */

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemaClientes";
    private static final String USER = "root";
    private static final String PASSWORD = "R@mon0604";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}