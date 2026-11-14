package com.ulbra.folha.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioTest {

    @Test
    void deveCriarFuncionarioComDadosValidos() {
        Funcionario funcionario = new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 0.0);

        assertEquals(1024, funcionario.getCodigo());
        assertEquals("Natsuki", funcionario.getNome());
        assertEquals("11111111111", funcionario.getCpf());
        assertEquals(2000.0, funcionario.getSalarioBase());
    }

    @Test
    void deveRetornarCodigoPelaInterfaceIdentificavel() {
        Identificavel identificavel = new FuncionarioCLT(3391, "Trunks", "33333333333", 1000.0, 0.0);

        assertEquals(3391, identificavel.getCodigo());
    }

    @Test
    void deveLancarExcecaoParaCodigoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(0, "Natsuki", "11111111111", 2000.0, 0.0));
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(782, "  ", "11111111111", 2000.0, 0.0));
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(782, null, "11111111111", 2000.0, 0.0));
    }

    @Test
    void deveLancarExcecaoParaCpfInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(149, "Liz Webber", "123", 2000.0, 0.0));
    }

    @Test
    void deveLancarExcecaoParaSalarioBaseNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(6005, "Kaneki Ken", "44444444444", -1.0, 0.0));
    }
}
