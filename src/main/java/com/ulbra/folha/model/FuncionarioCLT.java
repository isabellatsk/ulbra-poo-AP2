package com.ulbra.folha.model;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private static final double ALIQUOTA_TRIBUTOS = 0.11;
    private double adicionalBeneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf, double salarioBase, double adicionalBeneficios) {
        super(codigo, nome, cpf, salarioBase);
        setAdicionalBeneficios(adicionalBeneficios);
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + adicionalBeneficios;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * ALIQUOTA_TRIBUTOS;
    }

    public double getAdicionalBeneficios() {
        return adicionalBeneficios;
    }

    public void setAdicionalBeneficios(double adicionalBeneficios) {
        if (adicionalBeneficios < 0) {
            throw new IllegalArgumentException("O adicional de beneficios nao pode ser negativo.");
        }
        this.adicionalBeneficios = adicionalBeneficios;
    }
}
