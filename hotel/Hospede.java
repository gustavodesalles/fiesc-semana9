package hotel;

import java.time.LocalDate;

public class Hospede {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Quarto quarto;
    private double conta;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Hospede(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}
