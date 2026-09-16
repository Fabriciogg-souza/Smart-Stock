import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void salvarProdutos(ArrayList<Produto> estoque) {
        Path caminho = Path.of("produtos.txt");

        try (BufferedWriter escritor = Files.newBufferedWriter(caminho)) {
            for (Produto p : estoque) {
                String linha = p.getNomeProduto() + ";"
                        + p.getQuantidadeProduto() + ";"
                        + p.getConsumoMedioDiario();

                escritor.write(linha);
                escritor.newLine();
            }
        } catch (IOException erro) {
            System.out.println(
                    "Não foi possível salvar os produtos: " + erro.getMessage());
        }
    }

    public static void carregarProdutos(ArrayList<Produto> estoque) {
        Path produtos = Path.of("produtos.txt");

        if (Files.exists(produtos)) {
            try (BufferedReader leitor = Files.newBufferedReader(produtos)) {
                String linha = leitor.readLine();

                while (linha != null) {
                    String[] campos = linha.split(";");

                    if (campos.length != 3) {
                        System.out.println("Esse produto está em um formato inválido.");
                    } else {
                        try {
                            estoque.add(new Produto(
                                    campos[0],
                                    Integer.parseInt(campos[1]),
                                    Integer.parseInt(campos[2])));
                        } catch (NumberFormatException erro) {
                            System.out.println(
                                    "Erro ao converter, letra não pode ser números");
                        }
                    }

                    linha = leitor.readLine();
                }
            } catch (IOException erro) {
                System.out.println(
                        "Erro ao abrir ou ler o arquivo: " + erro.getMessage());
            }
        } else {
            return;
        }
    }

    public static int calcularEstoque(
            ArrayList<Produto> estoque,
            String nome,
            int quantidadeVendida) {

        int estoqueAtual = 0;
        int novoEstoque = 0;
        boolean produtoExiste = false;

        for (Produto p : estoque) {
            if (p.getNomeProduto().equalsIgnoreCase(nome)) {
                produtoExiste = true;
                System.out.println("Produto: " + nome + " Encontrado!");

                estoqueAtual = p.getQuantidadeProduto();

                // Antes de alterar e salvar, validar a venda:
                if(quantidadeVendida <= 0 ){
                    System.out.println("A venda não pode ser feita sem produtos vendidos.");
                    break;
                }
                else if( quantidadeVendida > estoqueAtual){
                    System.out.println("A venda superou a quantidade em estoque, não pode ser finalizada.");
                    break;
                } else {
                    novoEstoque = estoqueAtual - quantidadeVendida;
                    p.setQuantidadeProduto(novoEstoque);
                    salvarProdutos(estoque);
                    System.out.println(
                        "Novo estoque de " + p.getNomeProduto() + ": " + novoEstoque);

                        break;
                }
                

                


                
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

                System.out.println(
                        "Aviso: " + p.getNomeProduto()
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

                    estoque.add(
                            new Produto(nome, quantidadeEstoque, quantidadeDiaria));

                    salvarProdutos(estoque);

                    break;
                }

                case 2: {
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a quantidade vendida: ");
                    int quantidadeVenda = sc.nextInt();

                    int novoEstoque = calcularEstoque(
                            estoque, nome, quantidadeVenda);

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
                    System.out.println("Digite uma opção válida!");
            }

        } while (opcao != 0);
    }

    public static void main(String[] args) {
        ArrayList<Produto> estoque = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        carregarProdutos(estoque);
        exibirMenu(estoque, sc);

        
    }
}