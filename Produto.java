class Produto{
    private String nomeProduto;
    private int quantidadeProduto;
    private int consumoMedioDiario;

    // Construtor
    public Produto(String nome, int quantidade, int consumoMedioDia){
        this.nomeProduto = nome;
        this.quantidadeProduto = quantidade;
        this.consumoMedioDiario = consumoMedioDia;
    }

    // Getters
    public String getNomeProduto(){
    return this.nomeProduto;
    }

    public int getQuantidadeProduto(){
        return this.quantidadeProduto;
    }

    public int getConsumoMedioDiario(){
        return this.consumoMedioDiario;
    }

    // Setters

    public void setQuantidadeProduto(int quantidadeProduto){
    if(quantidadeProduto < 0){
        System.out.println("Quantidade Produto não pode ser negativo.");
        return;
    }
    this.quantidadeProduto = quantidadeProduto;
}

    public void setConsumoMedioDiario(int consumoMedioDiario){
    if(consumoMedioDiario <= 0){
        System.out.println("Consumo médio diário não pode ser zero ou negativo.");
        return;
    }
    this.consumoMedioDiario = consumoMedioDiario;
}
}