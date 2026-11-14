package com.ulbra.folha.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolimorfismoTest {

    @Test
    void deveExecutarImplementacaoCorrespondenteAoObjetoInstanciado() {
        Funcionario clt = new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 500.0);
        Funcionario pj = new FuncionarioPJ(57, "Trunks", "22222222222", 1500.0, 4);

        assertEquals(2500.0, clt.calcularRemuneracao(), 0.0001);
        assertEquals(6000.0, pj.calcularRemuneracao(), 0.0001);
    }

    @Test
    void deveCalcularTributosParaFuncionariosTributaveis() {
        Funcionario[] funcionarios = {
                new FuncionarioCLT(3391, "Hikaru", "33333333333", 2000.0, 0.0),
                new FuncionarioPJ(782, "Kaneki Ken", "44444444444", 1000.0, 1)
        };

        double totalTributos = 0.0;
        int tributaveis = 0;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Tributavel tributavel) {
                totalTributos += tributavel.calcularTributos();
                tributaveis++;
            }
        }

        assertEquals(2, tributaveis);
        assertEquals(2000.0 * 0.11 + 1000.0 * 0.06, totalTributos, 0.0001);
    }
}
