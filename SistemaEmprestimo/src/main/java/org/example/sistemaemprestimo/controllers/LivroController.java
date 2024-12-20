package org.example.sistemaemprestimo.controllers;

import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private LivroService livroService;

    public LivroController() {
        this.livroService = new LivroService();
    }

    @GetMapping("/")
    public List<Livro> getLivros() {
        return livroService.getAllLivros();
    }

    @PostMapping("/")
    public void create(@RequestBody Livro livro) {
        livroService.criarLivro(livro);
    }
}
