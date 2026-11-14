package com.ulbra.folha.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuncionarioNaoEncontradoExceptionTest {
    @Test
    void deveArmazenarAMensagem() {
        FuncionarioNaoEncontradoException excecao =
                new FuncionarioNaoEncontradoException("Funcionario nao encontrado.");

        assertEquals("Funcionario nao encontrado.", excecao.getMessage());
    }
}
