# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Aplicação em Java que gerencia funcionários de diferentes categorias e calcula a
folha de pagamento da empresa. Cada categoria possui sua própria regra de
remuneração. O projeto foi desenvolvido seguindo arquitetura em camadas, fundamentos de POO (Programação Orientada a Objetos) 
e a abordagem TDD (Test Driven Development) com JUnit 5.

## Funcionalidades

- Cadastrar funcionários (CLT e PJ).
- Listar todos os funcionários cadastrados.
- Buscar funcionário por código, CPF ou nome.
- Exibir os dados completos de um funcionário.
- Remover um funcionário cadastrado.
- Calcular a remuneração de cada funcionário.
- Calcular o total da folha de pagamento da empresa.
- Gerar relatório com todos os funcionários e seus respectivos salários.

## Categorias e regras de remuneração

| Categoria        | Remuneração                            | Tributos/descontos      |
|------------------|----------------------------------------|-------------------------|
| `FuncionarioCLT` | `salárioBase + adicionalBenefícios`    | 11% sobre a remuneração |
| `FuncionarioPJ`  | `valorPorProjeto × quantidadeProjetos` | 6% sobre a remuneração  |

## Conceitos de POO aplicados

- **Encapsulamento:** todos os atributos são `private`, com getters/setters e
  validações nos setters e construtores.
- **Herança e abstração:** classe abstrata `Funcionario` com o método abstrato
  `calcularRemuneracao()`, especializada em duas subclasses concretas.
- **Interfaces:** `Identificavel` (retorna o identificador) e `Tributavel`
  (calcula tributos/descontos), implementada apenas onde faz sentido.
- **Polimorfismo por sobreposição (override):** `calcularRemuneracao()` tem
  comportamento próprio em cada subclasse.
- **Polimorfismo por sobrecarga (overload):** `FuncionarioService.buscarFuncionario`
  com três assinaturas: `(int codigo)`, `(String cpf)` e `(String nome, boolean buscaExata)`.
- **Tratamento de exceções:** validações lançam `IllegalArgumentException` e há
  duas exceções personalizadas: `FuncionarioNaoEncontradoException` e
  `FuncionarioJaCadastradoException`.

## Estrutura do projeto

```
src/
├── main/java/com/ulbra/folha/
│   ├── Main.java
│   ├── model/
│   │   ├── Identificavel.java
│   │   ├── Tributavel.java
│   │   ├── Funcionario.java            (classe abstrata)
│   │   ├── FuncionarioCLT.java
│   │   └── FuncionarioPJ.java
│   ├── repository/
│   │   └── FuncionarioRepository.java  (armazenamento em memória)
│   ├── service/
│   │   └── FuncionarioService.java     (regras de negócio)
│   ├── exception/
│   │   ├── FuncionarioNaoEncontradoException.java
│   │   └── FuncionarioJaCadastradoException.java
│   └── view/
│       └── FuncionarioView.java        (menu interativo via console)
└── test/java/com/ulbra/folha/          (uma classe de teste por classe de produção)
```

O armazenamento é feito em memória, com `LinkedHashMap` no repositório.

## Requisitos

- Java 21 ou superior (desenvolvido e testado com o JDK 24).
- Maven 3.9+ — **não é obrigatório instalar**: o projeto inclui o Maven Wrapper
  (`mvnw` / `mvnw.cmd`), que baixa o Maven automaticamente na primeira execução.

## Como executar o projeto

A aplicação abre um menu interativo no console.

Windows (PowerShell):

```
.\mvnw.cmd compile
.\mvnw.cmd exec:java "-Dexec.mainClass=com.ulbra.folha.Main"
```

> No PowerShell, o argumento `-D` precisa estar entre aspas; sem elas o
> PowerShell quebra o parâmetro e o Maven acusa "Unknown lifecycle phase".

Windows (CMD):

```
.\mvnw.cmd compile
.\mvnw.cmd exec:java -Dexec.mainClass=com.ulbra.folha.Main
```

Linux / macOS:

```
./mvnw compile
./mvnw exec:java -Dexec.mainClass=com.ulbra.folha.Main
```

> Observação: o plugin `exec` é resolvido pelo Maven automaticamente. Como
> alternativa, após `mvnw package` você pode rodar a classe `Main` diretamente
> pela sua IDE.

## Como executar os testes

Windows:

```
.\mvnw.cmd test
```

Linux / macOS:

```
./mvnw test
```

São 40 testes automatizados cobrindo cálculos de remuneração de todas as
categorias, comportamento polimórfico, validações, cenários de sucesso e de
falha, e o lançamento das exceções personalizadas.
