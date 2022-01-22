package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Quarto> quartos = new ArrayList<>();

    public static Quarto getQuartoByCPF(String cpf) {
        for (Quarto q : quartos) {
            return q;
        }
        return null;
    }

    public static boolean verificarCPF(String cpf) { // adaptado do código disponível em: https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
        if (cpf.length() != 11) return false;
        char dig10 = cpf.charAt(9);
        char dig11 = cpf.charAt(10);
        int num, soma, peso, resto;
        int zeroAscii = 48;
        char digv1, digv2;

        // calcular o primeiro digito verificador
        soma = 0;
        peso = 10;

        for (int i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - zeroAscii); // subtrair por 48 conforme a tabela ASCII
            soma += (num * peso);
            peso--;
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            digv1 = '0';
        } else digv1 = (char) (resto + zeroAscii);

        // calcular o segundo digito verificador
        soma = 0;
        peso = 11;
        for (int j = 0; j < 10; j++) {
            num = (int) (cpf.charAt(j) - zeroAscii);
            soma += (num * peso);
            peso--;
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            digv2 = '0';
        } else digv2 = (char) (resto + zeroAscii);

        return (digv1 == dig10 && digv2 == dig11);
    }

    public static void reserva() throws DateTimeParseException {
        boolean repetir = true;
        LocalDate dataNasc = null, dataInic = null, dataFinal = null;
        int opcao = 0, numAcompanhantes = 0;
        Quarto quarto = null;
        String nome;

        do {
            System.out.println("Digite o nome do hóspede: ");
            nome = input.nextLine();
        } while (nome.isBlank() || nome.isEmpty());

        System.out.println("Digite o CPF: ");
        String cpf = input.nextLine();
        while (!verificarCPF(cpf)) {
            System.out.println("CPF inválido.");
            System.out.println("Digite o CPF: ");
            cpf = input.nextLine();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        do {
            try {
                System.out.println("Digite a data de nascimento (dd-MM-yyyy): ");
                String dataNascimento = input.nextLine();
                dataNasc = LocalDate.parse(dataNascimento, formatter);
                repetir = false;
            } catch (DateTimeParseException d) {
                System.out.println("Data inválida.");
            }
        } while (repetir);

        Hospede hospede = new Hospede(nome, cpf, dataNasc);

        repetir = true;
        do {
            try {
                System.out.println("Qual tipo de quarto você deseja reservar?");
                System.out.println("1 - Quarto simples");
                System.out.println("2 - Quarto de luxo");
                System.out.println("3 - Suíte suprema");
                opcao = input.nextInt();
                if (opcao > 3 || opcao < 1) {
                    System.out.println("Opção inválida.");
                } else {
                    repetir = false;
                }
            } catch (InputMismatchException i) {
                    System.out.println("Favor digitar um número inteiro.");
            }
        } while (repetir);

        repetir = true;
        do {
            try {
                System.out.println("Quantos acompanhantes?");
                numAcompanhantes = input.nextInt();
                if (numAcompanhantes < 0) {
                    System.out.println("Digite um número não-negativo.");
                } else {
                    repetir = false;
                }
            } catch (InputMismatchException i) {
                System.out.println("Favor digitar um número inteiro.");
            }
        } while (repetir);

        input.nextLine();
        repetir = true;
        do {
            try {
                System.out.println("Digite a data do início da estadia: ");
                String data1 = input.nextLine();
                dataInic = LocalDate.parse(data1, formatter);
                if (ChronoUnit.DAYS.between(LocalDate.now(), dataInic) > 60) {
                    System.out.println("Somente os próximos 60 dias estão disponíveis para reserva.");
                } else repetir = false;
            } catch (DateTimeParseException d) {
                System.out.println("Data inválida.");
            }
        } while (repetir);

        repetir = true;
        do {
            try {
                System.out.println("Digite a data do fim da estadia: ");
                String data2 = input.nextLine();
                dataFinal = LocalDate.parse(data2, formatter);
                if (dataFinal.isBefore(dataInic)) {
                    System.out.println("A data do fim não pode anteceder a do início.");
                } else repetir = false;
            } catch (DateTimeParseException d) {
                System.out.println("Data inválida.");
            }
        } while (repetir);

        switch (opcao) {
            case 1:
                quarto = new Quarto(TipoQuarto.SIMPLES, hospede, numAcompanhantes, dataInic, dataFinal);
                quartos.add(quarto);
                break;
            case 2:
                quarto = new Quarto(TipoQuarto.LUXO, hospede, numAcompanhantes, dataInic, dataFinal);
                quartos.add(quarto);
                break;
            case 3:
                quarto = new Quarto(TipoQuarto.SUPREMA, hospede, numAcompanhantes, dataInic, dataFinal);
                quartos.add(quarto);
                break;
        }

    }

    public static void main(String[] args) {

        System.out.println("Escolha uma opção:");
        System.out.println("1 - Fazer uma reserva");
        System.out.println("2 - Conferir reserva");
        System.out.println("3 - Cancelar reserva");
        int i = input.nextInt();

        switch (i) {
            case 1:
                reserva();
                main(null);
                break;
            case 2:
                System.out.println("Digite o CPF do hóspede: ");
                String cpf = input.nextLine();
                input.nextLine();
                System.out.println(getQuartoByCPF(cpf));
                main(null);
                break;
            case 3:
                cpf = input.nextLine();
                quartos.remove(getQuartoByCPF(cpf));
                main(null);
                break;
            default:
                System.out.println("Encerrando.");
        }



    }
}
