package com.ulbra.folha.repository;
import com.ulbra.folha.model.Funcionario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FuncionarioRepository {
    private final Map<Integer, Funcionario> funcionarios = new LinkedHashMap<>();

    public void salvar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("O funcionario nao pode ser nulo.");
        }
        funcionarios.put(funcionario.getCodigo(), funcionario);
    }

    public boolean existePorCodigo(int codigo) {
        return funcionarios.containsKey(codigo);
    }

    public Optional<Funcionario> buscarPorCodigo(int codigo) {
        return Optional.ofNullable(funcionarios.get(codigo));
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        if (cpf == null) {
            return Optional.empty();
        }
        String somenteDigitos = cpf.replaceAll("\\D", "");
        return funcionarios.values().stream()
                .filter(f -> f.getCpf().equals(somenteDigitos))
                .findFirst();
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios.values());
    }
    public boolean remover(int codigo) {
        return funcionarios.remove(codigo) != null;
    }
    public int quantidade() {
        return funcionarios.size();
    }
}
