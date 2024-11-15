package br.com.mvc.energymi.controller;
import br.com.mvc.energymi.model.Alerta;
import br.com.mvc.energymi.repository.AlertaRepository;
import br.com.mvc.energymi.repository.AparelhoRepository;
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
@RequestMapping("alerta")
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Alerta alerta, Model model) {
        model.addAttribute("aparelhos", aparelhoRepository.findAll());
        model.addAttribute("alerta", new Alerta());

        return "alerta/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Alerta alerta, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            return "alerta/cadastrar";
        }
        alertaRepository.save(alerta);
        redirectAttributes.addFlashAttribute("mensagem", "alerta cadastrado com sucesso!");
        return "redirect:/alerta/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("alertas", alertaRepository.findAll());
        return "alerta/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesAlerta(@PathVariable Long id, Model model) {
        Optional<Alerta> optionalAlerta = alertaRepository.findById(id);
        if (optionalAlerta.isPresent()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            model.addAttribute("alerta", optionalAlerta.get());
        } else {
            model.addAttribute("erro", "alerta não encontrado");
            return "error";
        }
        return "alerta/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("aparelhos", aparelhoRepository.findAll());
        model.addAttribute("alerta", alertaRepository.findById(id));
        return "alerta/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Alerta alerta, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            return "alerta/editar";
        }
        alertaRepository.save(alerta);
        redirectAttributes.addFlashAttribute("mensagem", "o alerta foi atualizado!");
        return "redirect:/alerta/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        alertaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "alerta removido com sucesso");
        return "redirect:/alerta/listar";
    }

}