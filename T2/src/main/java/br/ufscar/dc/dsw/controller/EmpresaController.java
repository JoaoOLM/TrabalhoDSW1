package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private IVagaDAO vagasDAO;

	@Autowired
	private IEmpresaService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Empresa getEmpresa(){
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = usuarioDetails.getUsuario();
        return service.buscarPorId(user.getId());
    }
	
	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "empresa/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("empresas",service.buscarTodos());
		return "empresa/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}

		empresa.setRole("ROLE_EMPRESA");
		empresa.setPassword(encoder.encode(empresa.getPassword()));

		service.salvar(empresa);
		attr.addFlashAttribute("sucess", "empresa.create.sucess");
		return "redirect:/empresa/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("empresa", service.buscarPorId(id));
		return "empresa/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldErrorCount() > 2 || result.getFieldError("CNPJ") == null || result.getFieldError("email") == null) {
			return "empresa/cadastro";
		}

		empresa.setRole("ROLE_EMPRESA");

		service.salvar(empresa);
		attr.addFlashAttribute("sucess", "empresa.edit.sucess");
		return "redirect:/empresa/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr, ModelMap model) {
		if (service.empresaTemVagas(id)) {
			model.addAttribute("fail", "empresa.delete.fail");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "empresa.delete.sucess");
		}
        return listar(model);
	}

	@GetMapping("/vagas") 
	public String minhasVagas(ModelMap model) {
		String empresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("empresaLogada", empresaLogada);
		model.addAttribute("vagas", vagasDAO.findByEmpresa(getEmpresa()));
		return "vagas/lista";
	}
}
