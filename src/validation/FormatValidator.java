package validation;

import services.MetodosCadastro;
import validation.Exceptions.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Pattern;

public class FormatValidator {

    public static void validarNome(String nome) throws CharacterValidationException {

        if(nome.contains(".*\\d.*")){
            throw new CharacterValidationException("O nome não pode conter numeros.");
        }
        if (nome.isBlank()) {
            throw new CharacterValidationException("O campo não pode ser vazio.");
        }
    }
    public static void validarIdade(String data_nascimento) throws DataValidationException{

       int idadeMinima = 18;
       int idade = MetodosCadastro.calcularIdade(data_nascimento);

        try {
            if (idade < idadeMinima){
                throw new DataValidationException("O usuário deve ser maior de idade.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido, utilize (dd/mm/yyyy)");
        }
    }

    public static void validarCPF(String cpf) throws CPFValidationException {
        if (cpf == null || cpf.isBlank()) {
            throw new CPFValidationException("O CPF não pode ser vazio.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new CPFValidationException("O CPF deve conter exatamente 11 números.");
        }
        if (cpf.matches(".*[a-zA-Z].*")) {
            throw new CPFValidationException("O CPF não pode conter letras.");
        }

    }

    public static void validarEmail(String email) throws EmailValidationException {
        if (email == null || email.isBlank()) {
            throw new EmailValidationException("O email não pode ser vazio.");
        }
        if (!Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches()) {
            throw new EmailValidationException("Formato de email inválido.");
        }
    }

    public static void validarSenha(String senha, String nome, String email) throws PasswordValidationException {
        if (senha == null || senha.isBlank()) {
            throw new PasswordValidationException("A senha não pode ser vazia.");
        }
        if (senha.length() < 8) {
            throw new PasswordValidationException("A senha deve conter pelo menos 8 caracteres.");
        }
        if (senha.contains(nome) || senha.contains(email)) {
            throw new PasswordValidationException("A senha não pode conter o nome ou email.");
        }
        if (!senha.matches(".*\\d.*") || !senha.matches(".*[a-zA-Z].*") || !senha.matches(".*[!@#$%^&*()].*")) {
            throw new PasswordValidationException("A senha deve conter letras, números e caracteres especiais.");
        }
    }
}
