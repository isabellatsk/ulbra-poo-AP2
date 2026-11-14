package com.ulbra.folha.service;

import com.ulbra.folha.exception.FuncionarioJaCadastradoException;
import com.ulbra.folha.exception.FuncionarioNaoEncontradoException;
import com.ulbra.folha.model.Funcionario;
import com.ulbra.folha.repository.FuncionarioRepository;

import java.util.List;
import java.util.Locale;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("O repositorio nao pode ser nulo.");
        }
        this.repository = repository;
    }

    public void cadastrar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionario nao pode ser nulo.");
        }
        if (repository.existePorCodigo(funcionario.getCodigo())) {
            throw new FuncionarioJaCadastradoException(
                    "Ja existe um funcionario cadastrado com o codigo " + funcionario.getCodigo() + ".");
        }
        repository.salvar(funcionario);
    }

    public Funcionario buscarFuncionario(int codigo) {
        return repository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(
                        "Funcionario com codigo " + codigo + " nao encontrado."));
    }

    public Funcionario buscarFuncionario(String cpf) {
        return repository.buscarPorCpf(cpf)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(
                        "Funcionario com CPF " + cpf + " nao encontrado."));
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca e obrigatorio.");
        }
        String alvo = nome.trim().toLowerCase(Locale.ROOT);
        List<Funcionario> encontrados = repository.listarTodos().stream()
                .filter(f -> buscaExata
                        ? f.getNome().toLowerCase(Locale.ROOT).equals(alvo)
                        : f.getNome().toLowerCase(Locale.ROOT).contains(alvo))
                .toList();
        if (encontrados.isEmpty()) {
            throw new FuncionarioNaoEncontradoException(
                    "Nenhum funcionario encontrado com o nome '" + nome + "'.");
        }
        return encontrados;
    }

    public void remover(int codigo) {
        if (!repository.remover(codigo)) {
            throw new FuncionarioNaoEncontradoException(
                    "Nao e possivel remover: funcionario com codigo " + codigo + " nao encontrado.");
        }
    }

    public List<Funcionario> listarTodos() {
        return repository.listarTodos();
    }

    public double calcularTotalFolha() {
        return repository.listarTodos().stream()
                .mapToDouble(Funcionario::calcularRemuneracao)
                .sum();
    }

    public String gerarRelatorio() {
        List<Funcionario> funcionarios = repository.listarTodos();
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n===== RELATORIO DE FUNCIONARIOS =====").append(System.lineSeparator());
        if (funcionarios.isEmpty()) {
            relatorio.append("\nNenhum funcionario cadastrado.").append(System.lineSeparator());
        } else {
            for (Funcionario funcionario : funcionarios) {
                relatorio.append(String.format(Locale.ROOT,
                        "Codigo: %d | Nome: %s | Tipo: %s | Remuneracao: R$ %.2f%n",
                        funcionario.getCodigo(),
                        funcionario.getNome(),
                        funcionario.getClass().getSimpleName(),
                        funcionario.calcularRemuneracao()));
            }
        }
        relatorio.append(String.format(Locale.ROOT, "Total da folha: R$ %.2f%n", calcularTotalFolha()));
        return relatorio.toString();
    }
}
