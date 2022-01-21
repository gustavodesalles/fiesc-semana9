package banco;

import java.util.ArrayList;

public class Agencia {
    private static ArrayList<Conta> contas = new ArrayList<>();

    public static void adiciona(Conta c) {
        contas.add(c);
    }

    public static int getQuantidadeDeContas() {
        return contas.size();
    }

    public static void buscaPorTitular(String nomeDoTitular) {
        for (Conta c : contas) {
            if (c.getCliente().getNome().equals(nomeDoTitular)) {
                System.out.println(c.getCliente().getNome());
                System.out.println(c.getSaldo());
            }
        }
    }

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Alice");
        Cliente cliente2 = new Cliente("Bob");

        ContaCorrente contaCorrente = new ContaCorrente(cliente1);
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente2);

        adiciona(contaCorrente);
        System.out.println(getQuantidadeDeContas());

        adiciona(contaPoupanca);
        System.out.println(getQuantidadeDeContas());

        buscaPorTitular("Bob");
    }
}
