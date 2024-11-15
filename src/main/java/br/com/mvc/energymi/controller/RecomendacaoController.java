package br.com.mvc.energymi.controller;

import br.com.mvc.energymi.model.Recomendacao;
import br.com.mvc.energymi.repository.AparelhoRepository;
import br.com.mvc.energymi.repository.RecomendacaoRepository;
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
@RequestMapping("recomendacao")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Recomendacao recomendacao, Model model) {
        model.addAttribute("aparelhos", aparelhoRepository.findAll());
        model.addAttribute("recomendacao", new Recomendacao());

        return "recomendacao/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Recomendacao recomendacao, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            return "recomendacao/cadastrar";
        }
        recomendacaoRepository.save(recomendacao);
        redirectAttributes.addFlashAttribute("mensagem", "recomendacao cadastrada com sucesso!");
        return "redirect:/recomendacao/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("recomendacoes", recomendacaoRepository.findAll());
        return "recomendacao/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesRecomendacao(@PathVariable Long id, Model model) {
        Optional<Recomendacao> optionalRecomendacao = recomendacaoRepository.findById(id);
        if (optionalRecomendacao.isPresent()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            model.addAttribute("recomendacao", optionalRecomendacao.get());
        } else {
            model.addAttribute("erro", "recomendacao não encontrada");
            return "error";
        }
        return "recomendacao/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("aparelhos", aparelhoRepository.findAll());
        model.addAttribute("recomendacao", recomendacaoRepository.findById(id));
        return "recomendacao/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Recomendacao recomendacao, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("aparelhos", aparelhoRepository.findAll());
            return "recomendacao/editar";
        }
        recomendacaoRepository.save(recomendacao);
        redirectAttributes.addFlashAttribute("mensagem", "a recomendacao foi atualizada!");
        return "redirect:/recomendacao/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        recomendacaoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "recomendacao removida com sucesso");
        return "redirect:/recomendacao/listar";
    }
}