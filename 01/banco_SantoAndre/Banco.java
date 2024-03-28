import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Banco {
    private Map<String, Usuario> usuarios = new HashMap<>();

    private static class Usuario {
        private String nome;
        private int idade;
        private int saldo;

        public int getSaldo() {
            return saldo;
        }

        public void setSaldo(int saldo) {
            this.saldo = saldo;
        }

        public Usuario(String nome, int idade, int saldo) {
            this.nome = nome;
            this.idade = idade;
            this.saldo = saldo;
        }
    }

    private int gerarSaldoAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void inserirUsuario(String nome, int idade) {
        int saldoInicial = gerarSaldoAleatorio(10, 1000);
        Usuario usuario = new Usuario(nome, idade, saldoInicial);
        usuarios.put(nome, usuario);
    }

    public void depositar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Idade: ");
        int idadeUsuario = scanner.nextInt();
        System.out.print("Quantia a ser depositada: ");
        int valorDeposito = scanner.nextInt();

        try {
            Usuario usuario = usuarios.get(nomeUsuario);
            int saldoAtual = usuario.getSaldo();
            int novoSaldo = saldoAtual + valorDeposito;
            usuario.setSaldo(novoSaldo);

            System.out.println("Depósito realizado com sucesso!\n");
            System.out.println("Usuário " + nomeUsuario + " (" + idadeUsuario + " anos)" + ": ");
            System.out.println("Saldo inicial: " + saldoAtual);
            System.out.println("Saldo atual: " + novoSaldo + "\n");
        } catch (NullPointerException e) {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void sacar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Idade: ");
        int idadeUsuario = scanner.nextInt();
        System.out.print("Quantia a ser sacada: ");
        int valorSaque = scanner.nextInt();

        try {
            Usuario usuario = usuarios.get(nomeUsuario);
            int saldoAtual = usuario.getSaldo();
            int novoSaldo = saldoAtual - valorSaque;
            usuario.setSaldo(novoSaldo);
            System.out.println("Saque realizado com sucesso!\n");
            System.out.println("Usuário " + nomeUsuario + " (" + idadeUsuario + " anos)" + ": ");
            System.out.println("Saldo inicial: " + saldoAtual);
            System.out.println("Saldo atual:" + novoSaldo);
        } catch (NullPointerException e) {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.inserirUsuario("André", 20);
        banco.depositar();
        banco.sacar();
    }
}