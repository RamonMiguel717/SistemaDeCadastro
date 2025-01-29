package controller;

import model.Cliente;
import repositorio.BancoDeDados;
import validation.FormatValidator;

import java.util.Scanner;

public class InterfaceCadastro {
    Scanner entrada = new Scanner(System.in);

    public void apresentacao() {
        System.out.println("Já tem cadastro? (Sim(S)/Não(N)");
        String resposta = entrada.nextLine();

        switch (resposta.toLowerCase()) {
            case "s":
                AcessoConta();
                break;
            case "n":
                CadastrarNovaConta();
                break;
            default:
                System.out.println("Opção inválida!");
                apresentacao();
                break;
        }
    }
    protected void AcessoConta() {
        try {
            System.out.println("Olá! Seja bem-vindo.");
            System.out.print("Digite seu CPF: ");
            String cpf = entrada.nextLine();
            FormatValidator.validarCPF(cpf);

            System.out.print("Digite sua senha: ");
            String senha = entrada.nextLine();

            // Consulta no banco de dados
            BancoDeDados banco = new BancoDeDados();
            banco.conectar();
            Cliente cliente = banco.buscarClientePorCpf(cpf);

            if (cliente != null && cliente.getSenha().equals(senha)) {
                System.out.println("Seja bem-vindo, " + cliente.getNome() + "!");
            } else {
                System.out.println("CPF ou senha inválidos!");
            }

            banco.desconectar();

        } catch (Exception e) {
            System.out.println("Erro ao entrar: " + e.getMessage());
        }
    }

    public void CadastrarNovaConta() {
        System.out.println("Insira as informações requisitadas:");
        try {
            System.out.println("Insira seu nome completo: ");
            String nome = entrada.nextLine();
            FormatValidator.validarNome(nome);

            System.out.println("Insira seu email:");
            String email = entrada.nextLine();
            FormatValidator.validarEmail(email);

            System.out.println("Insira sua data de nascimento");
            String data = entrada.nextLine();
            FormatValidator.validarIdade(data);

            System.out.println("Insira seu CPF: ");
            String cpf = entrada.nextLine();
            FormatValidator.validarCPF(cpf);

            System.out.println("Insira sua senha:");
            String senha = entrada.nextLine();
            FormatValidator.validarSenha(senha, nome, email);

            System.out.println("Confirme sua senha: ");
            String confirmacao = entrada.nextLine();
            while (!confirmacao.equals(senha)) { // Corrigindo a comparação de senhas
                System.out.println("Senha incorreta, tente novamente");
                confirmacao = entrada.nextLine();
            }

            // Cria o objeto cliente
            Cliente novoCliente = new Cliente(nome, senha, email, cpf, data);

            // Salva o cliente no banco de dados
            BancoDeDados banco = new BancoDeDados();
            banco.conectar();
            banco.salvarCliente(novoCliente);
            banco.desconectar();

            System.out.println("Cadastro realizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

}
