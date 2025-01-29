package repositorio;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BancoDeDados {
    
    private Connection connection;
    
    public void conectar() throws SQLException {
        this.connection = DataBaseConnection.getConnection();
        System.out.println("Conectado ao banco de dados!");
    }

    public void desconectar() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Desconectado do banco de dados!");
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }
    }

    public void salvarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (cpf, nome, email, data_nascimento, senha) VALUES (?,?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getDataNascimento());
            statement.setString(5, cliente.getSenha()); // Adicionando a senha
            statement.executeUpdate();
            System.out.println("Cliente salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente " + e.getMessage());
        }
    }
    public void apagarCliente(String cpf) throws SQLException {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            String sql = "DELETE FROM clientes WHERE cpf = ?";
            try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, cpf);
                int resultado = statement.executeUpdate();

                if (resultado > 0) {
                    System.out.println("Cliente apagado: " + cliente.getNome());
                }
            } catch (SQLException e) {
                System.out.println("Erro ao apagar cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado com o CPF: " + cpf);
        }
    }

        public Cliente buscarClientePorCpf(String cpf) throws SQLException {

            String sql = "SELECT * FROM clientes WHERE cpf = ?";
            try {

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try(Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,cpf);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {
                    return new Cliente(
                            resultado.getString("cpf"),
                            resultado.getString("nome"),
                            resultado.getString("email"),
                            resultado.getString("data_nascimento"),
                            resultado.getString("senha"));
                };
            }catch (SQLException e) {
                System.out.println("Erro ao buscar cliente " + e.getMessage());
            }
            return null;
        }

    /*
   NOTE: comando dentro do try em resumo realiza os seguintes passos:

- Realiza a conexão com o banco de dados e armazena na variavel "connection"
- PreparedStatement é uma maneira de tratar o tipo de comando que será enviado para o MySQL, ele faz a conexão do meu codigo
com o banco de dados.
 */

    private String retornarSenha(String cpf) throws SQLException {
        String sql = "SELECT senha FROM clientes WHERE cpf = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                return resultado.getString("senha");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao retornar senha " + e.getMessage());
        }
        return null;
    }

}


// TODO criar um método privado para buscar a senha do cliente ou modifica-la no banco de dados
