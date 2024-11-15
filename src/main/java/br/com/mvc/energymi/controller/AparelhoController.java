package br.com.mvc.energymi.controller;

import br.com.mvc.energymi.model.Aparelho;
import br.com.mvc.energymi.model.Instalacao;
import br.com.mvc.energymi.repository.AparelhoRepository;
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
@RequestMapping("aparelho")
public class AparelhoController {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private InstalacaoRepository instalacaoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Aparelho aparelho, Model model) {
        model.addAttribute("instalacoes", instalacaoRepository.findAll());
        model.addAttribute("aparelho", new Aparelho());

        return "aparelho/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Aparelho aparelho, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("instalacoes", instalacaoRepository.findAll());
            return "aparelho/cadastrar";
        }
        aparelhoRepository.save(aparelho);
        redirectAttributes.addFlashAttribute("mensagem", "aparelho cadastrado com sucesso!");
        return "redirect:/aparelho/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("aparelhos", aparelhoRepository.findAll());
        return "aparelho/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesAparelho(@PathVariable Long id, Model model) {
        Optional<Aparelho> optionalAparelho = aparelhoRepository.findById(id);
        if (optionalAparelho.isPresent()) {
            model.addAttribute("instalacoes", instalacaoRepository.findAll());

            model.addAttribute("aparelho", optionalAparelho.get());
        } else {
            model.addAttribute("erro", "aparelho não encontrado");
            return "error";
        }
        return "aparelho/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("instalacoes", instalacaoRepository.findAll());
        model.addAttribute("aparelho", aparelhoRepository.findById(id));
        return "aparelho/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Aparelho aparelho, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("instalacoes", instalacaoRepository.findAll());
            return "aparelho/editar";
        }
        aparelhoRepository.save(aparelho);
        redirectAttributes.addFlashAttribute("mensagem", "o aparelho foi atualizado!");
        return "redirect:/aparelho/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        aparelhoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "aparelho removido com sucesso");
        return "redirect:/aparelho/listar";
    }
}
