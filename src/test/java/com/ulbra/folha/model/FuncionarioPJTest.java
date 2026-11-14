package com.ulbra.folha.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioPJTest {

    @Test
    void deveCalcularRemuneracaoComoValorPorProjetoVezesQuantidade() {
        FuncionarioPJ funcionario = new FuncionarioPJ(1024, "Trunks", "11111111111", 1500.0, 4);

        assertEquals(6000.0, funcionario.calcularRemuneracao(), 0.0001);
    }

    @Test
    void deveCalcularTributosComAliquotaDeSeisPorCento() {
        FuncionarioPJ funcionario = new FuncionarioPJ(57, "Liz Webber", "22222222222", 1500.0, 4);

        assertEquals(360.0, funcionario.calcularTributos(), 0.0001);
    }

    @Test
    void deveRetornarZeroQuandoNaoHaProjetos() {
        FuncionarioPJ funcionario = new FuncionarioPJ(3391, "Kaneki Ken", "33333333333", 1500.0, 0);

        assertEquals(0.0, funcionario.calcularRemuneracao(), 0.0001);
    }

    @Test
    void deveLancarExcecaoParaValorPorProjetoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(782, "Hikaru", "44444444444", -1.0, 4));
    }

    @Test
    void deveLancarExcecaoParaQuantidadeDeProjetosNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(149, "Natsuki", "55555555555", 1500.0, -2));
    }
}
