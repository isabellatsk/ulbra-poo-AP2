package com.ulbra.folha.repository;

import com.ulbra.folha.model.Funcionario;
import com.ulbra.folha.model.FuncionarioCLT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioRepositoryTest {

    private FuncionarioRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
    }

    @Test
    void deveSalvarERecuperarFuncionarioPorCodigo() {
        Funcionario funcionario = new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 0.0);

        repository.salvar(funcionario);

        assertTrue(repository.buscarPorCodigo(1024).isPresent());
        assertEquals("Natsuki", repository.buscarPorCodigo(1024).get().getNome());
    }

    @Test
    void deveRecuperarFuncionarioPorCpf() {
        repository.salvar(new FuncionarioCLT(57, "Hikaru", "222.222.222-22", 2000.0, 0.0));

        assertTrue(repository.buscarPorCpf("22222222222").isPresent());
    }

    @Test
    void deveRetornarVazioParaCodigoInexistente() {
        assertFalse(repository.buscarPorCodigo(99).isPresent());
    }

    @Test
    void deveListarTodosOsFuncionarios() {
        repository.salvar(new FuncionarioCLT(1024, "Trunks", "11111111111", 2000.0, 0.0));
        repository.salvar(new FuncionarioCLT(3391, "Thiago Fritz", "33333333333", 3000.0, 0.0));

        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveRemoverFuncionarioExistente() {
        repository.salvar(new FuncionarioCLT(782, "Liz Webber", "44444444444", 2000.0, 0.0));

        assertTrue(repository.remover(782));
        assertFalse(repository.existePorCodigo(782));
    }

    @Test
    void deveRetornarFalsoAoRemoverFuncionarioInexistente() {
        assertFalse(repository.remover(99));
    }

    @Test
    void deveLancarExcecaoAoSalvarFuncionarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> repository.salvar(null));
    }
}
