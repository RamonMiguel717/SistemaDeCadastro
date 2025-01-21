package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MetodosCadastro {

    public static int calcularIdade(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
        LocalDate hoje = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(dataNasc, hoje);
    }

}
