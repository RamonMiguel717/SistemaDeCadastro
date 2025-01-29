package main;

import model.Cliente;
import repositorio.BancoDeDados;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BancoDeDados repositorio = new BancoDeDados();

        // Salvar cliente
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "12345678901", "1990-01-01");
        repositorio.salvarCliente(cliente);

        // Buscar cliente
        Cliente clienteBuscado = repositorio.buscarClientePorCpf("12345678901");
        if (clienteBuscado != null) {
            System.out.println("Cliente encontrado: " + clienteBuscado.getNome());
        }

        // Apagar cliente
        repositorio.apagarCliente("12345678901");
    }
}
