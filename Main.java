import java.util.ArrayList;
import java.util.Scanner;


class Produto{
    String nomeProduto;
    int quantidadeProduto;
    int consumoMedioDiario;

    // Construtor
    public Produto(String nome, int quantidade, int consumoMedioDia){
        this.nomeProduto = nome;
        this.quantidadeProduto = quantidade;
        this.consumoMedioDiario = consumoMedioDia;
    }
}

public class Main {
    public static int calcularEstoque(ArrayList<Produto> estoque, String nome, int quantidadeVendida, int quantidadeMediaDiaria){
        int estoqueAtual = 0;
        boolean produtoExiste = false;
        for (Produto p : estoque){
            if(p.nomeProduto.equalsIgnoreCase(nome)){
                produtoExiste = true;
                System.out.println("Produto: " + nome + " Encontrado!");

                estoqueAtual = p.quantidadeProduto -= quantidadeVendida;


                System.out.println("Novo estoque de " + p.nomeProduto + ": " + estoqueAtual);
                break;

            } 
        }
        if(!produtoExiste){
            System.out.println("Produto não encontrado.");
        }
        
        return estoqueAtual;
    }


    public static void exibirAlerta(String nome, int estoqueAtual, int quantidadeDiaria){
        int diasRestantes = (estoqueAtual / quantidadeDiaria);
        if(estoqueAtual < quantidadeDiaria*5){
            System.out.println("Aviso: " + nome + " tem estoque apenas para "+ diasRestantes + " dia(s)!");
        }
        
    }




    public static void main(String[] args) {


        ArrayList<Produto> estoque = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Digite a quantidade no estoque: ");
        int quantidadeEstoque = sc.nextInt();
        System.out.print("Digite a quantidade vendida: ");
        int quantidadeVenda = sc.nextInt();
        System.out.println("Digite o consumo médio por dia do produto: ");
        int quantidadeDiaria = sc.nextInt();

        
        while(quantidadeDiaria == 0){
            System.out.println("Consumo médio diário não pode ser 0, digite novamente: ");
            quantidadeDiaria = sc.nextInt();
        }
            
        

        estoque.add(new Produto(nome, quantidadeEstoque, quantidadeDiaria));
        
        int estoqueAtual =  calcularEstoque(estoque,nome, quantidadeVenda,quantidadeDiaria);
        

        exibirAlerta(nome,estoqueAtual, quantidadeDiaria);
        
    }
}
