package com.gdw.projeto_final.gdw_final.controller;

import com.gdw.projeto_final.gdw_final.model.Cliente;
import com.gdw.projeto_final.gdw_final.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Formulário para criar novo Cliente
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    // Salvar novo Cliente
    @PostMapping("/criar")
    public String criar(Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cliente-form";
        }
        clienteRepository.save(cliente);
        return "redirect:/consultar-dados";
    }

    // Formulário para editar Cliente existente
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Long id, Model model) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return "redirect:/consultar-dados";
        }
        model.addAttribute("cliente", cliente.get());
        return "cliente-form";
    }

    // Salvar edição do Cliente
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cliente-form";
        }
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/consultar-dados";
    }

    // Excluir Cliente
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consultar-dados";
    }
}
