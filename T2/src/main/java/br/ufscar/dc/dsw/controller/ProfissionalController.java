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
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private ICandidaturaService candidaturaService;

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

        if (result.hasErrors()) {
            return "profissional/cadastro";
        }

        profissional.setRole("ROLE_PROFISSIONAL");
        profissional.setPassword(encoder.encode(profissional.getPassword()));

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.create.sucess");
        return "redirect:/profissional/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null){
            model.addAttribute("error", "Profissional não encontrado.");
            return "redirect:/error";
        }

        model.addAttribute("profissional", profissional);
        return "profissional/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
        
        if (result.getFieldErrorCount() > 2 || result.getFieldError("cpf") == null || result.getFieldError("email") == null) {
			return "profissional/cadastro";
		}

        profissional.setRole("ROLE_PROFISSIONAL");

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.edit.sucess");
        return "redirect:/profissional/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr, ModelMap model) {
        if (!candidaturaService.buscarPorProfissional(profissionalService.buscarPorId(id)).isEmpty()) {
			model.addAttribute("fail", "profissional.delete.fail");
		} else {
			profissionalService.excluir(id);
			model.addAttribute("sucess", "profissional.delete.sucess");
		}
        return listar(model);
    }
}
