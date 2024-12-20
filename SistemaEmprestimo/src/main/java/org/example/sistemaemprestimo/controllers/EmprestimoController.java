package org.example.sistemaemprestimo.controllers;

import org.example.sistemaemprestimo.models.Aluno;
import org.example.sistemaemprestimo.models.Emprestimo;
import org.example.sistemaemprestimo.models.Livro;
import org.example.sistemaemprestimo.service.AlunoService;
import org.example.sistemaemprestimo.service.EmprestimoService;
import org.example.sistemaemprestimo.service.LivroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    private EmprestimoService emprestimoService;
    private AlunoService alunoService;
    private LivroService livroService;

    public EmprestimoController() {
        this.emprestimoService = new EmprestimoService();
        this.livroService = new LivroService();
        this.alunoService = new AlunoService();
    }

    @PostMapping("/emprestar")
    public boolean emprestar(@RequestBody Map<String, Object> requestBody) {
        Integer alunoMatricula = (Integer) requestBody.get("alunoMatricula");
        List<Integer> livrosIds = (List<Integer>) requestBody.get("livrosIds");

        Aluno aluno = alunoService.getByMatricula(alunoMatricula);
        if (!alunoService.verificarAluno(aluno)) {
            return false;
        }

        List<Livro> livros = new ArrayList<>();
        for (Integer id : livrosIds) {
            Livro livro = livroService.getLivroById(id);
            if (livro.isDisponivel() && !livro.isExemplarBiblioteca()) {
                livros.add(livro);
            }
        }

        Emprestimo e = emprestimoService.emprestar(aluno, livros);
        return e != null;
    }

    @PostMapping("/devolver")
    public void devolver(@RequestBody Map<String, Object> requestBody) {
        List<Integer> livrosIds = (List<Integer>) requestBody.get("livrosIds");
        String dataDevolucaoStr = (String) requestBody.get("dataDevolucao");

        Date dataDevolucao;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dataDevolucao = formatter.parse(dataDevolucaoStr);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Expected yyyy-MM-dd", e);
        }

        List<Livro> livros = new ArrayList<>();
        for (Integer id : livrosIds) {
            Livro livro = livroService.getLivroById(id);
            livros.add(livro);
        }

        emprestimoService.devolver(livros, dataDevolucao);
    }
}
