package com.ulbra.folha;
import com.ulbra.folha.repository.FuncionarioRepository;
import com.ulbra.folha.service.FuncionarioService;
import com.ulbra.folha.view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService service = new FuncionarioService(repository);
        FuncionarioView view = new FuncionarioView(service);
        view.iniciar();
    }
}
