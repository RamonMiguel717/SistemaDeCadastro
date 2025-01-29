package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente {
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private String dataNascimento;

    private final List<String> errors = new ArrayList<>();

    public Cliente(String nome, String senha, String email, String cpf, String dataNascimento) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
    public Cliente(String nome, String email, String cpf, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
