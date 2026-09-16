# Smart Stock

Sistema de controle e monitoramento de estoque desenvolvido em Java, criado para aplicar conceitos de Programação Orientada a Objetos e lógica de programação em um problema prático.

Na V2, a aplicação permite cadastrar múltiplos produtos, registrar vendas pelo terminal e manter os dados entre execuções usando um arquivo de texto.

## Sobre o projeto

Pequenos comércios podem ter dificuldade para identificar quando um produto precisa ser reposto. Muitas vezes, a necessidade de reposição só é percebida quando o estoque já está próximo de acabar.

O Smart Stock utiliza a quantidade disponível e o consumo médio diário de cada produto para estimar a duração do estoque. Quando há menos de 5 dias de consumo disponíveis, o sistema exibe um alerta na opção de consulta do menu.

## Novidades da V2

- Menu interativo com `do-while` e `switch`, disponível até o usuário escolher sair.
- Cadastro e gerenciamento de múltiplos produtos em um `ArrayList`.
- Registro de várias vendas durante a mesma execução.
- Persistência em `produtos.txt`, com gravação após cadastros e vendas válidas.
- Carregamento dos produtos antes da abertura do menu.
- Bloqueio de vendas com quantidade zero, negativa ou superior ao estoque disponível.
- Tratamento de erros de leitura, escrita e conversão de números do arquivo.

## Funcionalidades

- Cadastro de produtos com nome, quantidade inicial e consumo médio diário.
- Busca por nome ao registrar uma venda, sem diferenciar maiúsculas e minúsculas.
- Atualização do estoque após uma venda válida.
- Consulta de alertas para todos os produtos cadastrados.
- Cálculo aproximado de dias restantes de estoque.
- Alerta de reposição quando o estoque está abaixo de 5 dias de consumo.
- Proteção contra divisão por zero: produtos com consumo diário menor ou igual a zero são ignorados na consulta de alertas.
- Recuperação dos produtos salvos ao reiniciar o programa.

## Como funciona

O sistema calcula o estoque após uma venda e estima sua duração:

```text
Estoque atual = Estoque anterior - Quantidade vendida
Dias restantes = Estoque atual / Consumo médio diário
```

Por exemplo:

```text
Estoque inicial:       80 unidades
Venda:                 20 unidades
Consumo médio diário:  15 unidades
Estoque atual:         60 unidades
Dias restantes:         4 dias
```

Ao selecionar a opção de alertas, o sistema informa a necessidade de reposição desse produto.

A divisão utiliza valores `int`, descartando a parte decimal. Por exemplo, 20 unidades com consumo de 6 por dia resultam em 3 dias na exibição. O alerta é definido pela condição `estoqueAtual < consumoDiario * 5`; um estoque para exatamente 5 dias não gera aviso.

## Como executar

### Pré-requisitos

- Java JDK 11 ou superior, pois o projeto utiliza `Path.of()`.
- Git instalado, caso o projeto seja clonado pelo terminal.

### Execução

Substitua `<URL_DO_REPOSITORIO>` pela URL do projeto:

```bash
git clone <URL_DO_REPOSITORIO> SmartStock
cd SmartStock
```

Compile as duas classes:

```bash
javac Main.java Produto.java
```

Execute a aplicação:

```bash
java Main
```

O projeto utiliza a biblioteca padrão do Java e não depende de bibliotecas externas.

## Exemplo de execução

O menu oferece as seguintes opções:

```text
=== Smart Stock ===
1 - Cadastrar produto
2 - Registrar venda
3 - Ver alertas
0 - Sair
```

Exemplo de cadastro pela opção `1`:

```text
Digite o nome do produto: Arroz
Digite a quantidade no estoque: 24
Digite o consumo médio por dia do produto:
6
```

Depois de encerrar e executar novamente, o produto é recuperado do arquivo. Pela opção `2`, é possível registrar uma venda:

```text
Digite o nome do produto: Arroz
Digite a quantidade vendida: 4
Produto: Arroz Encontrado!
Novo estoque de Arroz: 20
```

Ao selecionar a opção `3`, inclusive após outro reinício:

```text
Aviso: Arroz tem estoque para aproximadamente 3 dia(s)!
```

Uma tentativa de vender 21 unidades, com apenas 20 disponíveis, é recusada:

```text
A venda superou a quantidade em estoque, não pode ser finalizada.
```

Nesse caso, a quantidade não é alterada e a gravação da venda não é executada.

## Persistência dos dados

O arquivo `produtos.txt` guarda um produto por linha, no formato:

```text
nome;quantidade;consumoMedioDiario
```

Exemplo de conteúdo, sem linha de cabeçalho:

```text
Arroz;20;6
Feijão;18;3
```

- Ao iniciar, o programa carrega o arquivo para a lista antes de abrir o menu.
- Se o arquivo não existir,continua com a lista vazia.
- Após um cadastro ou uma venda válida, grava a lista inteira, substituindo o conteúdo anterior.
- O arquivo é criado na primeira gravação bem-sucedida.
- O caminho é relativo à pasta de trabalho de onde a aplicação é executada.
- Linhas com quantidade de campos diferente de três ou números que não podem ser convertidos para `int` são ignoradas com aviso.

O arquivo representa o estado atual do estoque, não um histórico de movimentações. Os dias restantes são recalculados a partir dos dados carregados.

## Estrutura do projeto

### Produto

A classe `Produto` representa um produto do estoque e possui os atributos privados:

- `nomeProduto`: nome do produto.
- `quantidadeProduto`: quantidade disponível em estoque.
- `consumoMedioDiario`: consumo médio diário informado no cadastro.

O construtor inicializa os dados. Getters permitem consultá-los e setters permitem atualizar quantidade e consumo, com validações nesses setters.

### Main

A classe `Main` organiza o fluxo da aplicação por meio dos seguintes métodos:

| Método | Responsabilidade |
| --- | --- |
| `main()` | Criar a lista e o Scanner, carregar os produtos e abrir o menu. |
| `exibirMenu()` | Receber a opção e executar cadastros, vendas e consultas. |
| `calcularEstoque()` | Localizar o produto, validar a venda, atualizar e salvar o estoque. |
| `exibirAlertas()` | Percorrer os produtos e exibir os alertas de reposição. |
| `salvarProdutos()` | Gravar a lista atual em `produtos.txt`. |
| `carregarProdutos()` | Ler o arquivo e reconstruir os objetos na lista. |

## Conceitos praticados

- Programação Orientada a Objetos: classes, objetos e construtores.
- Encapsulamento com atributos privados, getters e setters.
- Coleções com `ArrayList`.
- Laços `for-each`, `while` e `do-while`.
- Estruturas condicionais e seleção com `switch`.
- Controle de fluxo com `boolean`, `break` e `return`.
- Métodos, parâmetros e valores de retorno.
- Entrada de dados com `Scanner`.
- Leitura e escrita com `Path`, `Files`, `BufferedReader` e `BufferedWriter`.
- Fechamento automático de recursos com `try-with-resources`.
- Tratamento de `IOException` e `NumberFormatException`.
- Separação de texto com `split` e conversão com `Integer.parseInt`.

## Decisões de implementação

### Consumo médio informado manualmente

O consumo médio diário é informado no cadastro e armazenado em cada produto. Ele ainda não é calculado a partir das vendas, mantendo o escopo focado no controle e na persistência do estoque.

### Alerta de 5 dias

O limite busca ajudar o responsável a identificar a necessidade de reposição com antecedência. Os alertas são consultados pelo menu; não existem notificações automáticas.

### Arquivo de texto

A persistência em TXT permite praticar leitura, escrita e reconstrução de objetos antes da integração com um banco de dados. A lista inteira é gravada para manter o estado atual, sem acumular versões antigas de cada produto.

### Separação de responsabilidades

As operações são divididas em métodos. A classe `Produto` guarda os dados, enquanto `Main` concentra o menu, as regras de venda, os alertas e o acesso ao arquivo.

## Problemas encontrados e corrigidos

- Confusão entre quantidade em estoque e quantidade vendida.
- Mensagem de produto não encontrado exibida sem considerar o resultado da busca.
- Divisão por zero na consulta de alertas.
- Condição de alerta inicialmente invertida.
- Leitura do nome pulada ao misturar `nextInt()` e `nextLine()`, corrigida consumindo a quebra de linha após a opção do menu.
- Tentativa de acessar o consumo diário de uma variável local de outro `case`, resolvida recuperando o valor do próprio produto.
- Perda dos dados ao encerrar uma execução normal, resolvida com gravação e carregamento em arquivo.
- Vendas com quantidade inválida, bloqueadas antes da atualização e da gravação.

## Limitações atuais

- O consumo médio diário é informado manualmente.
- Não há histórico de vendas nem cálculo da data estimada de esgotamento.
- Produtos são identificados pelo nome; nomes duplicados ainda não são bloqueados e a venda afeta o primeiro encontrado.
- O cadastro ainda precisa validar nome vazio, uso de `;` no nome e valores numéricos fora das regras do produto. O construtor atual não aplica as validações dos setters.
- Entradas não numéricas nos campos do terminal ainda podem interromper o programa.
- A leitura valida a estrutura e a conversão dos campos, mas ainda permite valores como estoque negativo ou consumo não positivo no construtor.
- A gravação substitui diretamente o arquivo, sem backup ou troca por arquivo temporário. Uma falha pode deixar dados incompletos.
- Uma carga parcial ou com linhas ignoradas não bloqueia futuras gravações; salvar depois disso pode remover do arquivo os registros que não foram carregados.
- Não há banco de dados nem controle de acesso simultâneo por várias execuções.

## Próximos passos

- Melhorar a validação dos cadastros e das entradas do terminal.
- Criar identificadores únicos para os produtos.
- Implementar edição, reposição e exclusão de produtos.
- Criar um histórico de vendas.
- Calcular automaticamente o consumo médio e a data estimada de esgotamento.
- Melhorar a segurança da gravação e impedir sobrescritas após falhas no carregamento.
- Separar o acesso a arquivos, as regras de negócio e o menu em classes próprias.
- Implementar persistência em banco de dados.

## Objetivo

O Smart Stock foi desenvolvido como um projeto de estudo para aplicar conceitos de Java em um problema prático.

A proposta é evoluir gradualmente conforme novos conceitos forem aprendidos, transformando uma aplicação inicialmente simples em um sistema de controle de estoque mais completo.

## Status

**V2 — Funcional, com persistência em arquivo e melhorias em desenvolvimento.**

O fluxo de cadastro, reinício, venda, novo reinício e consulta de alertas foi testado manualmente. Também foi verificada a recusa de venda superior ao estoque disponível.

## Tecnologias

- Java.
- Java Collections Framework (`ArrayList`).
- Java `Scanner`.
- Java NIO (`Path` e `Files`).
- Java IO (`BufferedReader`, `BufferedWriter` e `IOException`).
