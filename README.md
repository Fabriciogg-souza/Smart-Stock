# Smart Stock

Sistema de controle e monitoramento de estoque desenvolvido em Java, criado para aplicar conceitos de Programação Orientada a Objetos e lógica de programação em um problema prático.

## Sobre o projeto

Pequenos comércios podem ter dificuldade para identificar quando um produto precisa ser reposto. Muitas vezes, a necessidade de reposição só é percebida quando o estoque já está próximo de acabar.

O Smart Stock busca solucionar esse problema de forma simples. A partir da quantidade disponível, do consumo médio diário e de uma venda realizada, o sistema calcula o estoque restante e verifica se o produto está próximo de acabar.

Quando o estoque possui menos de 5 dias de consumo, o sistema emite um alerta de reposição.

## Funcionalidades

- Cadastro de um produto
- Definição da quantidade inicial em estoque
- Registro da quantidade vendida
- Definição do consumo médio diário
- Atualização do estoque após uma venda
- Cálculo aproximado de dias restantes de estoque
- Alerta de reposição quando o estoque está abaixo de 5 dias
- Validação do consumo médio diário para evitar divisão por zero

## Como funciona

O sistema utiliza o consumo médio diário para estimar por quantos dias o estoque atual será suficiente.

```text
Dias restantes = Estoque atual / Consumo médio diário
```

Por exemplo:

```text
Estoque inicial:        80 unidades
Venda:                  20 unidades
Consumo médio diário:   15 unidades

Estoque atual:           60 unidades
Dias restantes:           4 dias
```

Como o estoque possui menos de 5 dias de duração, o sistema apresenta um alerta de reposição.

## Como executar

### Pré-requisitos

- Java JDK instalado
- Git instalado, caso o projeto seja clonado do GitHub

### Execução

Clone o repositório e acesse a pasta do projeto:

```bash
git clone <URL_DO_REPOSITORIO>
cd Smart-Stock
```

Compile o projeto:

```bash
javac Main.java
```

Execute a aplicação:

```bash
java Main
```

> O projeto utiliza recursos básicos do Java e não depende de bibliotecas externas.

## Exemplo de execução

```text
Digite o nome do produto: Arroz
Digite a quantidade no estoque: 80
Digite a quantidade vendida: 20
Digite o consumo médio por dia do produto: 15

Produto: Arroz Encontrado!
Novo estoque de Arroz: 60
Aviso: Arroz tem estoque apenas para 4 dia(s)!
```

Neste exemplo, após a venda, o estoque passa a ter 60 unidades. Com um consumo médio de 15 unidades por dia, o estoque é suficiente para aproximadamente 4 dias, acionando o alerta de reposição.

## Estrutura do projeto

O projeto possui duas classes principais.

### Produto

A classe `Produto` representa um produto do estoque e possui os seguintes atributos:

- `nomeProduto`: nome do produto
- `quantidadeProduto`: quantidade disponível em estoque
- `consumoMedioDiario`: consumo médio diário do produto

A classe também possui um construtor responsável por inicializar essas informações.

### Main

A classe `Main` é responsável pelo fluxo principal da aplicação.

Entre suas responsabilidades estão:

- Receber os dados do usuário
- Criar o produto
- Armazenar o produto em um `ArrayList`
- Localizar o produto
- Atualizar o estoque após uma venda
- Calcular os dias restantes
- Exibir o alerta de reposição
- Validar entradas do usuário

## Conceitos praticados

Durante o desenvolvimento foram utilizados conceitos fundamentais de Java, como:

- Programação Orientada a Objetos
- Classes
- Construtores
- Objetos
- `ArrayList`
- Estrutura `for-each`
- Estruturas condicionais
- Variáveis `boolean` para controle de fluxo
- Métodos
- Parâmetros e valores de retorno
- Entrada de dados com `Scanner`
- Validação de entrada utilizando `while`

## Decisões de implementação

### Consumo médio informado manualmente

Nesta primeira versão, o consumo médio diário é informado manualmente pelo usuário.

Essa decisão foi tomada para manter o escopo da primeira versão simples e focado na lógica principal do sistema.

Em uma versão futura, o consumo médio poderá ser calculado automaticamente a partir de um histórico de vendas.

### Alerta de 5 dias

Foi definido um limite de 5 dias para o alerta de reposição.

A ideia é permitir que o responsável identifique antecipadamente que determinado produto está próximo de acabar, considerando o tempo necessário para realizar uma nova compra e receber a mercadoria.

### Separação de responsabilidades

O projeto possui métodos separados para diferentes responsabilidades.

O método `calcularEstoque()` é responsável por localizar o produto e atualizar sua quantidade após uma venda, enquanto `exibirAlerta()` verifica a situação do estoque e informa ao usuário quando a reposição é necessária.

## Problemas encontrados e corrigidos

Durante o desenvolvimento, alguns problemas de lógica foram identificados e corrigidos:

- Variável de quantidade sendo utilizada para representar tanto o estoque quanto a quantidade vendida
- Mensagem de produto não encontrado sendo exibida independentemente do resultado da busca
- Possibilidade de divisão por zero quando o consumo médio diário era `0`
- Condição do alerta inicialmente invertida

A correção desses problemas ajudou a reforçar conceitos de controle de fluxo, condições, variáveis de controle e tratamento de casos específicos.

## Limitações atuais

O projeto está atualmente em sua primeira versão e possui algumas limitações:

- Apenas um produto é cadastrado por execução
- Apenas uma venda é registrada por execução
- O consumo médio diário é informado manualmente
- Não existe histórico de vendas
- Não existe persistência dos dados
- Os dados são perdidos ao encerrar o programa
- O sistema calcula os dias restantes, mas ainda não calcula uma data estimada de esgotamento

## Próximos passos

Para versões futuras, estão planejadas as seguintes melhorias:

- Cadastro de múltiplos produtos
- Registro de várias vendas
- Criação de histórico de vendas
- Cálculo automático do consumo médio
- Cálculo da data estimada de esgotamento
- Encapsulamento dos atributos utilizando `private`, getters e setters
- Melhor separação das responsabilidades entre as classes
- Persistência dos dados em arquivos
- Implementação de banco de dados

## Objetivo

O Smart Stock foi desenvolvido como um projeto de estudo para aplicar conceitos de Java em um problema prático.

A proposta é evoluir o projeto gradualmente conforme novos conceitos de programação forem aprendidos, transformando uma aplicação inicialmente simples em um sistema de controle de estoque mais completo.

## Status

V1 — Em desenvolvimento

## Tecnologias

- Java
- Java Collections Framework (`ArrayList`)
- Java `Scanner`
