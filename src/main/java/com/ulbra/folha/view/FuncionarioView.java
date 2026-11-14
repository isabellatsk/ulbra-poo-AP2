package com.ulbra.folha.view;

import com.ulbra.folha.model.Funcionario;
import com.ulbra.folha.model.FuncionarioCLT;
import com.ulbra.folha.model.FuncionarioPJ;
import com.ulbra.folha.model.Tributavel;
import com.ulbra.folha.service.FuncionarioService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FuncionarioView {

    private final FuncionarioService service;
    private final Scanner scanner;

    public FuncionarioView(FuncionarioService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            executarOpcao(opcao);
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("=== Folha de Pagamento ===");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Buscar funcionario");
        System.out.println("4 - Remover funcionario");
        System.out.println("5 - Total da folha");
        System.out.println("6 - Relatorio");
        System.out.println("0 - Sair");
    }

    private void executarOpcao(int opcao) {
        if (opcao == 0) {
            System.out.println("Encerrando o sistema...");
            return;
        }
        System.out.println();
        System.out.println("========= Resposta =========");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> remover();
                case 5 -> totalFolha();
                case 6 -> relatorio();
                default -> System.out.println("Opcao invalida.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("============================");
    }

    private void cadastrar() {
        System.out.println("Tipo: 1 - CLT | 2 - PJ");
        int tipo = lerInteiro("Escolha o tipo: ");
        int codigo = lerInteiro("Codigo: ");
        String nome = lerTexto("Nome: ");
        String cpf = lerTexto("CPF: ");

        Funcionario funcionario = switch (tipo) {
            case 1 -> new FuncionarioCLT(codigo, nome, cpf,
                    lerDouble("Salario-base: "),
                    lerDouble("Adicional de beneficios: "));
            case 2 -> new FuncionarioPJ(codigo, nome, cpf,
                    lerDouble("Valor por projeto: "),
                    lerInteiro("Quantidade de projetos: "));
            default -> throw new IllegalArgumentException("Tipo de funcionario invalido.");
        };

        service.cadastrar(funcionario);
        System.out.println("Funcionario cadastrado com sucesso.");
    }

    private void listar() {
        List<Funcionario> funcionarios = service.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        funcionarios.forEach(System.out::println);
    }

    private void buscar() {
        System.out.println("Buscar por: 1 - Codigo | 2 - CPF | 3 - Nome");
        int tipo = lerInteiro("Escolha: ");
        switch (tipo) {
            case 1 -> exibirDetalhes(service.buscarFuncionario(lerInteiro("Codigo: ")));
            case 2 -> exibirDetalhes(service.buscarFuncionario(lerTexto("CPF: ")));
            case 3 -> service.buscarFuncionario(lerTexto("Nome: "), false).forEach(this::exibirDetalhes);
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void remover() {
        service.remover(lerInteiro("Codigo do funcionario a remover: "));
        System.out.println("Funcionario removido com sucesso.");
    }

    private void totalFolha() {
        System.out.printf(Locale.ROOT, "Total da folha de pagamento: R$ %.2f%n", service.calcularTotalFolha());
    }

    private void relatorio() {
        System.out.print(service.gerarRelatorio());
    }

    private void exibirDetalhes(Funcionario funcionario) {
        System.out.println("---------------------------------");
        System.out.println("Codigo: " + funcionario.getCodigo());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Tipo: " + funcionario.getClass().getSimpleName());
        System.out.printf(Locale.ROOT, "Salario-base: R$ %.2f%n", funcionario.getSalarioBase());
        System.out.printf(Locale.ROOT, "Remuneracao: R$ %.2f%n", funcionario.calcularRemuneracao());
        if (funcionario instanceof Tributavel tributavel) {
            System.out.printf(Locale.ROOT, "Tributos/descontos: R$ %.2f%n", tributavel.calcularTributos());
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Valor invalido. " + mensagem);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Valor invalido. " + mensagem);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
