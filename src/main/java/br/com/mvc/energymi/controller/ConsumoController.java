package br.com.mvc.energymi.controller;


import br.com.mvc.energymi.model.Consumo;
import br.com.mvc.energymi.repository.ConsumoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("consumo")
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Consumo consumo, Model model) {
        model.addAttribute("consumo", new Consumo());

        return "consumo/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Consumo consumo, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {

            return "consumo/cadastrar";
        }
        consumoRepository.save(consumo);
        redirectAttributes.addFlashAttribute("mensagem", "consumo cadastrado com sucesso!");
        return "redirect:/consumo/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("consumos", consumoRepository.findAll());
        return "consumo/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesConsumo(@PathVariable Long id, Model model) {
        Optional<Consumo> optionalConsumo = consumoRepository.findById(id);
        if (optionalConsumo.isPresent()) {
            model.addAttribute("consumo", optionalConsumo.get());
        } else {
            model.addAttribute("erro", "consumo não encontrado");
            return "error";
        }
        return "consumo/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("consumo", consumoRepository.findById(id));
        return "consumo/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Consumo consumo, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "consumo/editar";
        }
        consumoRepository.save(consumo);
        redirectAttributes.addFlashAttribute("mensagem", "o consumo foi atualizado!");
        return "redirect:/consumo/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        consumoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "consumo removido com sucesso");
        return "redirect:/consumo/listar";
    }
}