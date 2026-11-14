package com.ulbra.folha.model;

public abstract class Funcionario implements Identificavel {
    private int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    protected Funcionario(int codigo, String nome, String cpf, double salarioBase) {
        setCodigo(codigo);
        setNome(nome);
        setCpf(cpf);
        setSalarioBase(salarioBase);
    }

    public abstract double calcularRemuneracao();

    @Override
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O codigo do funcionario deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionario e obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do funcionario e obrigatorio.");
        }
        String somenteDigitos = cpf.replaceAll("\\D", "");
        if (somenteDigitos.length() != 11) {
            throw new IllegalArgumentException("CPF invalido: " + cpf);
        }
        this.cpf = somenteDigitos;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salario-base nao pode ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - CPF: %s - Remuneracao: R$ %.2f",
                codigo, nome, cpf, calcularRemuneracao());
    }
}
