package com.ulbra.folha.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuncionarioJaCadastradoExceptionTest {
    @Test
    void deveArmazenarAMensagem() {
        FuncionarioJaCadastradoException excecao =
                new FuncionarioJaCadastradoException("Funcionario ja cadastrado.");

        assertEquals("Funcionario ja cadastrado.", excecao.getMessage());
    }
}
