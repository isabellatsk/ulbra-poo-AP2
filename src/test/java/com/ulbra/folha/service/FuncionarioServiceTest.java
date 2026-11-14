package com.ulbra.folha.service;

import com.ulbra.folha.exception.FuncionarioJaCadastradoException;
import com.ulbra.folha.exception.FuncionarioNaoEncontradoException;
import com.ulbra.folha.model.Funcionario;
import com.ulbra.folha.model.FuncionarioCLT;
import com.ulbra.folha.model.FuncionarioPJ;
import com.ulbra.folha.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    private FuncionarioService service;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService(new FuncionarioRepository());
    }

    @Test
    void deveCadastrarFuncionario() {
        service.cadastrar(new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 0.0));

        assertEquals("Natsuki", service.buscarFuncionario(1024).getNome());
    }

    @Test
    void deveLancarExcecaoAoCadastrarCodigoDuplicado() {
        service.cadastrar(new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 0.0));

        assertThrows(FuncionarioJaCadastradoException.class,
                () -> service.cadastrar(new FuncionarioCLT(1024, "Hikaru", "22222222222", 3000.0, 0.0)));
    }

    @Test
    void deveLancarExcecaoAoBuscarPorCodigoInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(99));
    }

    @Test
    void deveBuscarPorCpf() {
        service.cadastrar(new FuncionarioCLT(57, "Trunks", "11111111111", 2000.0, 0.0));

        assertEquals(57, service.buscarFuncionario("11111111111").getCodigo());
    }

    @Test
    void deveLancarExcecaoAoBuscarPorCpfInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class,
                () -> service.buscarFuncionario("22222222222"));
    }

    @Test
    void deveBuscarPorNomeComBuscaExata() {
        service.cadastrar(new FuncionarioCLT(3391, "Thiago Fritz", "11111111111", 2000.0, 0.0));

        List<Funcionario> encontrados = service.buscarFuncionario("Thiago Fritz", true);

        assertEquals(1, encontrados.size());
    }

    @Test
    void deveBuscarPorNomeParcial() {
        service.cadastrar(new FuncionarioCLT(782, "Kaneki Ken", "11111111111", 2000.0, 0.0));
        service.cadastrar(new FuncionarioCLT(149, "Natsuki", "22222222222", 2000.0, 0.0));

        List<Funcionario> encontrados = service.buscarFuncionario("k", false);

        assertEquals(2, encontrados.size());
    }

    @Test
    void deveLancarExcecaoAoBuscarPorNomeInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class,
                () -> service.buscarFuncionario("Goku", true));
    }

    @Test
    void deveRemoverFuncionario() {
        service.cadastrar(new FuncionarioCLT(6005, "Liz Webber", "11111111111", 2000.0, 0.0));

        service.remover(6005);

        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(6005));
    }

    @Test
    void deveLancarExcecaoAoRemoverFuncionarioInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.remover(99));
    }

    @Test
    void deveCalcularTotalDaFolha() {
        service.cadastrar(new FuncionarioCLT(1024, "Natsuki", "11111111111", 2000.0, 500.0));
        service.cadastrar(new FuncionarioPJ(57, "Trunks", "22222222222", 1000.0, 2));

        assertEquals(2500.0 + 2000.0, service.calcularTotalFolha(), 0.0001);
    }

    @Test
    void deveGerarRelatorioComOsFuncionarios() {
        service.cadastrar(new FuncionarioCLT(3391, "Kaneki Ken", "11111111111", 2000.0, 500.0));

        String relatorio = service.gerarRelatorio();

        assertTrue(relatorio.contains("Kaneki Ken"));
        assertTrue(relatorio.contains("Total da folha"));
    }

    @Test
    void deveLancarExcecaoAoCriarServiceComRepositorioNulo() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioService(null));
    }
}
