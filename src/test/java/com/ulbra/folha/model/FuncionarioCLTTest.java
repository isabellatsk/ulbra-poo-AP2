package com.ulbra.folha.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioCLTTest {

    @Test
    void deveCalcularRemuneracaoComoSalarioBaseMaisBeneficios() {
        FuncionarioCLT funcionario = new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 500.0);

        assertEquals(2500.0, funcionario.calcularRemuneracao(), 0.0001);
    }

    @Test
    void deveCalcularTributosComAliquotaDeOnzePorCento() {
        FuncionarioCLT funcionario = new FuncionarioCLT(57, "Hikaru", "22222222222", 2000.0, 500.0);

        assertEquals(275.0, funcionario.calcularTributos(), 0.0001);
    }

    @Test
    void deveSerTributavel() {
        FuncionarioCLT funcionario = new FuncionarioCLT(3391, "Trunks", "33333333333", 2000.0, 500.0);

        assertTrue(funcionario instanceof Tributavel);
    }

    @Test
    void deveLancarExcecaoParaAdicionalBeneficiosNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(782, "Thiago Fritz", "44444444444", 2000.0, -10.0));
    }
}
