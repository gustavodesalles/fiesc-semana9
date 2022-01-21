package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

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

    public static void reserva() {
        boolean repetir = true;
        LocalDate dataNasc = null;
        int opcao, numAcompanhantes;

        System.out.println("Digite o nome do hóspede: ");
        String nome = input.nextLine();

        System.out.println("Digite o CPF: ");
        String cpf = input.nextLine();
        while (!verificarCPF(cpf)) {
            System.out.println("CPF inválido.");
            System.out.println("Digite o CPF: ");
            cpf = input.next();
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
                repetir = false;
            } catch (InputMismatchException i) {
                System.out.println("Favor digitar um número inteiro.");
            }
        } while (repetir);




    }

    public static void main(String[] args) {
        ArrayList<Quarto> quartos = new ArrayList<>();


    }
}
