package com.gdw.projeto_final.gdw_final.controller;

import com.gdw.projeto_final.gdw_final.repository.ClienteRepository;
import com.gdw.projeto_final.gdw_final.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaController {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public ConsultaController(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/consultar-dados")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "consultar-dados";
    }
}
