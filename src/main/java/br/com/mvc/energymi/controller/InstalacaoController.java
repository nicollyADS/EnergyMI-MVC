package br.com.mvc.energymi.controller;

import br.com.mvc.energymi.model.Instalacao;
import br.com.mvc.energymi.repository.InstalacaoRepository;
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
@RequestMapping("instalacao")
public class InstalacaoController {

    @Autowired
    private InstalacaoRepository instalacaoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Instalacao instalacao, Model model) {
        model.addAttribute("instalacao", new Instalacao());

        return "instalacao/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Instalacao instalacao, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {

            return "instalacao/cadastrar";
        }
        instalacaoRepository.save(instalacao);
        redirectAttributes.addFlashAttribute("mensagem", "instalacao cadastrada com sucesso!");
        return "redirect:/instalacao/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("instalacoes", instalacaoRepository.findAll());
        return "instalacao/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesInstalacao(@PathVariable Long id, Model model) {
        Optional<Instalacao> optionalInstalacao = instalacaoRepository.findById(id);
        if (optionalInstalacao.isPresent()) {
            model.addAttribute("instalacao", optionalInstalacao.get());
        } else {
            model.addAttribute("erro", "instalacão não encontrada");
            return "error";
        }
        return "instalacao/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("instalacao", instalacaoRepository.findById(id));
        return "instalacao/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Instalacao instalacao, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "instalacao/editar";
        }
        instalacaoRepository.save(instalacao);
        redirectAttributes.addFlashAttribute("mensagem", "a instalacao foi atualizada!");
        return "redirect:/instalacao/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        instalacaoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "instalacao removida com sucesso");
        return "redirect:/instalacao/listar";
    }
}