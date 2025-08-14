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

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    // ALTERAÇÃO: @ModelAttribute("cliente") para casar com th:object
    @PostMapping("/criar")
    public String criar(@ModelAttribute("cliente") Cliente cliente,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            return "cliente-form";
        }
        clienteRepository.save(cliente);
        return "redirect:/consultar-dados";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Long id, Model model) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return "redirect:/consultar-dados";
        }
        model.addAttribute("cliente", cliente.get());
        return "cliente-form";
    }

    // ALTERAÇÃO: @ModelAttribute("cliente") + setId(id)
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id,
                         @ModelAttribute("cliente") Cliente cliente,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "cliente-form";
        }
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/consultar-dados";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consultar-dados";
    }
}
