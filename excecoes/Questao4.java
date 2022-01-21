package excecoes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Questao4 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int num1, num2;
        double res;
        boolean repetir = true;

        System.out.println("Eu sei dividir.");

        do {
            try {
                System.out.println("Informe o primeiro valor: ");
                num1 = teclado.nextInt();
                System.out.println("Informe o segundo valor: ");
                num2 = teclado.nextInt();

                res = num1 / num2;
                System.out.println("Resultado da divisão: "+ res);
                repetir = false;
            } catch (ArithmeticException a) {
                System.out.println("O denominador não pode ser igual a zero.");
            } catch (InputMismatchException i) {
                System.out.println("Por favor, informe um número inteiro.");
            }
        } while (repetir);

    }
}
