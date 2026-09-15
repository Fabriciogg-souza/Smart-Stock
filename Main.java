import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int calcularEstoque(ArrayList<Produto> estoque, String nome, int quantidadeVendida) {
        int estoqueAtual = 0;
        int novoEstoque = 0;
        boolean produtoExiste = false;
        for (Produto p : estoque) {
            if (p.getNomeProduto().equalsIgnoreCase(nome)) {
                produtoExiste = true;
                System.out.println("Produto: " + nome + " Encontrado!");

                estoqueAtual = p.getQuantidadeProduto();
                novoEstoque = estoqueAtual - quantidadeVendida;
                p.setQuantidadeProduto(novoEstoque);

                System.out.println("Novo estoque de " + p.getNomeProduto() + ": " + novoEstoque);
                break;

            }
        }
        if (!produtoExiste) {
            System.out.println("Produto não encontrado.");
        }

        return novoEstoque;
    }

    public static void exibirAlertas(ArrayList<Produto> estoque) {
        boolean encontrouAlerta = false;

        for (Produto p : estoque) {
            int estoqueAtual = p.getQuantidadeProduto();
            int consumoDiario = p.getConsumoMedioDiario();

            if (consumoDiario <= 0) {
                continue;
            }

            if (estoqueAtual < consumoDiario * 5) {
                int diasRestantes = estoqueAtual / consumoDiario;

                System.out.println("Aviso: " + p.getNomeProduto()
                        + " tem estoque para aproximadamente "
                        + diasRestantes + " dia(s)!");

                encontrouAlerta = true;
            }
        }

        if (!encontrouAlerta) {
            System.out.println("Nenhum produto precisa de alerta.");
        }
    }

    public static void exibirMenu(ArrayList<Produto> estoque, Scanner sc) {
        int opcao = 0;
        do {
            System.out.println("=== Smart Stock ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Registrar venda");
            System.out.println("3 - Ver alertas");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();
            

            switch (opcao) {
                case 1: {
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a quantidade no estoque: ");
                    int quantidadeEstoque = sc.nextInt();

                    System.out.println("Digite o consumo médio por dia do produto: ");
                    int quantidadeDiaria = sc.nextInt();

                    estoque.add(new Produto(nome, quantidadeEstoque, quantidadeDiaria));
                    break;
                }

                case 2: {

                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a quantidade vendida: ");
                    int quantidadeVenda = sc.nextInt();

                    int novoEstoque = calcularEstoque(estoque, nome, quantidadeVenda);
                    break;
                }

                case 3: {
                    exibirAlertas(estoque);
                    break;
                }

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    // o que acontece se digitar uma opção inválida?
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {

        ArrayList<Produto> estoque = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        exibirMenu(estoque, sc);
        //exibirAlerta(nome, novoEstoque, quantidadeDiaria);

    }
}
