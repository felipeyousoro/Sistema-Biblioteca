package org.example.sistemaemprestimo.controllers;

import org.example.sistemaemprestimo.models.Aluno;
import org.example.sistemaemprestimo.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private AlunoService alunoService;

    public AlunoController() {
        this.alunoService = new AlunoService();
    }

    @GetMapping("/")
    public List<Aluno> getAlunos() {
        return alunoService.getAll();
    }

    @PostMapping("/")
    public void create(@RequestBody Aluno aluno) {
        alunoService.criarAluno(aluno);
    }
}
