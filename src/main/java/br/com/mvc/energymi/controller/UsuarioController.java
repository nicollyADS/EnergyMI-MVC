package br.com.mvc.energymi.controller;

import br.com.mvc.energymi.dto.UseForm;
import br.com.mvc.energymi.repository.RoleRepository;
import br.com.mvc.energymi.repository.UsuarioRepository;
import br.com.mvc.energymi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("usuario", new UseForm());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrar(@Valid UseForm useForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "usuario/cadastrar";
        }
        usuarioService.salvar(useForm.getUsername(),
                passwordEncoder.encode(useForm.getPassword()), useForm.getRoles());
        return "redirect:/login";
    }

}
