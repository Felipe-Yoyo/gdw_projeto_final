package com.gdw.projeto_final.gdw_final.controller;

import com.gdw.projeto_final.gdw_final.model.Produto;
import com.gdw.projeto_final.gdw_final.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Formulário para criar novo Produto
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto-form";
    }

    // Salvar novo Produto
    @PostMapping("/criar")
    public String criar(Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "produto-form";
        }
        produtoRepository.save(produto);
        return "redirect:/consultar-dados";
    }

    // Formulário para editar Produto existente
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Long id, Model model) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return "redirect:/consultar-dados";
        }
        model.addAttribute("produto", produto.get());
        return "produto-form";
    }

    // Salvar edição do Produto
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "produto-form";
        }
        produto.setId(id);
        produtoRepository.save(produto);
        return "redirect:/consultar-dados";
    }

    // Excluir Produto
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/consultar-dados";
    }
}
