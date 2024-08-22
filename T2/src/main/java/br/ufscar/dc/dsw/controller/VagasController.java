package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;


@Controller
@RequestMapping("/vagas")
public class VagasController {
    
    @Autowired
	private IVagaService service;

    @Autowired
	private IVagaDAO vagasDAO;

    @Autowired
	private IEmpresaService empresaService;

	private Empresa getEmpresa(){
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = usuarioDetails.getUsuario();
        return empresaService.buscarPorId(user.getId());
    }
	
	@GetMapping("/cadastrar")
	public String cadastrar(Vaga vagas) {
        vagas.setEmpresa(this.getEmpresa());
		System.out.println("id" + vagas.getEmpresa().getId());

		return "vagas/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		//fazer metodo para listar todas as abertas

		return "vagas/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Vaga vagas, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println(result);
			return "vagas/cadastro";
		}
	
        service.salvar(vagas);
		attr.addFlashAttribute("success", "Vaga inserida com sucesso.");
		return "redirect:/empresa/vagas";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		System.out.println("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
		System.out.println("id da vaga1" + id);
		model.addAttribute("vaga", service.buscarPorId(id));
		System.out.println("id da vaga" + id);
		System.out.println("vaga" + service.buscarPorId(id).getDescricao());
		return "vagas/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr) {

		service.salvar(vaga);
		attr.addFlashAttribute("success", "Vaga editada com sucesso.");
		return "redirect:/empresa/vagas";
	}
}
