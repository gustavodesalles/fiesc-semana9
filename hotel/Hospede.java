package hotel;

import java.time.LocalDate;

public class Hospede {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Hospede(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
