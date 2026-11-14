package com.ulbra.folha.model;

public class FuncionarioPJ extends Funcionario implements Tributavel {
    private static final double ALIQUOTA_TRIBUTOS = 0.06;
    private double valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf, double valorPorProjeto, int quantidadeProjetos) {
        super(codigo, nome, cpf, 0.0);
        setValorPorProjeto(valorPorProjeto);
        setQuantidadeProjetos(quantidadeProjetos);
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * quantidadeProjetos;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * ALIQUOTA_TRIBUTOS;
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public void setValorPorProjeto(double valorPorProjeto) {
        if (valorPorProjeto < 0) {
            throw new IllegalArgumentException("O valor por projeto nao pode ser negativo.");
        }
        this.valorPorProjeto = valorPorProjeto;
    }
    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public void setQuantidadeProjetos(int quantidadeProjetos) {
        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("A quantidade de projetos nao pode ser negativa.");
        }
        this.quantidadeProjetos = quantidadeProjetos;
    }
}
