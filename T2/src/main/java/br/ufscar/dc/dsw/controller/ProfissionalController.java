package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Profissional profissional) {
        return "profissional/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("profissionais", profissionalService.buscarTodos());
        return "profissional/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

        profissional.setRole("ROLE_PROFISSIONAL");
        profissional.setPassword(encoder.encode(profissional.getPassword()));

        if (result.hasErrors()) {
            return "profissional/cadastro";
        }

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.create.sucess");
        return "redirect:/profissional/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("profissional", profissionalService.buscarPorId(id));
        return "profissional/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "profissional/cadastro";
        }

        // Busque o profissional original no banco de dados
        Profissional profissionalOriginal = profissionalService.buscarPorId(profissional.getId());

        // Verifique se a senha foi alterada
        if (!profissionalOriginal.getPassword().equals(profissional.getPassword())) {
            profissional.setPassword(encoder.encode(profissional.getPassword()));
        } else {
            profissional.setPassword(profissionalOriginal.getPassword());
        }

        profissional.setRole("ROLE_PROFISSIONAL");

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.edit.sucess");
        return "redirect:/profissional/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        profissionalService.excluir(id);
        attr.addFlashAttribute("sucess", "profissional.delete.sucess");
        return "redirect:/profissional/listar";
    }
}
