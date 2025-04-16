package com.example.NovoTesteCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    @GetMapping("/index")
    public String index() {
        return "paginas/index";
    }

    @GetMapping("/login")
    public String login() {
        return "paginas/login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "paginas/cadastro";
    }

    @GetMapping("/reset-senha")
    public String resetSenha() {
        return "paginas/reset-senha";
    }

    @GetMapping("/troca-senha")
    public String trocaSenha() {
        return "paginas/troca_senha";
    }

    @GetMapping("/senha")
    public String senha() {
        return "paginas/senha";
    }

    @GetMapping("/perfil-usuario")
    public String perfilUsuario() {
        return "paginas/perfil_usuario";
    }

    @GetMapping("/perfil-academias")
    public String perfilAcademias() {
        return "paginas/perfil_academias";
    }

    @GetMapping("/perfil-personais")
    public String perfilPersonais() {
        return "paginas/perfil_personais";
    }

    @GetMapping("/academias")
    public String academias() {
        return "paginas/academias";
    }

    @GetMapping("/cadastrar-academia")
    public String cadastrarAcademia() {
        return "paginas/cadastrar_academia";
    }

    @GetMapping("/detalhes-academias")
    public String detalhesAcademias() {
        return "paginas/detalhes_academias";
    }

    @GetMapping("/personais")
    public String personais() {
        return "paginas/personais";
    }

    @GetMapping("/detalhes-personais")
    public String detalhesPersonais() {
        return "paginas/detalhes_personais";
    }

    @GetMapping("/treinos")
    public String treinos() {
        return "paginas/treinos";
    }

    @GetMapping("/detalhes-treinos")
    public String detalhesTreinos() {
        return "paginas/detalhes_treinos";
    }
}
