package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/vagas")
public class VagasController {
    
    @Autowired
	private IVagaService service;

    @Autowired
	private IVagaDAO vagasDAO;

    @Autowired
	private IEmpresaService empresaService;

	@Autowired
	private IUsuarioService usuarioService;

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
		Vaga vaga = service.buscarPorId(id);
		System.out.println("id da vaga" + id);
		System.out.println("vaga" + vaga.getDescricao());

        // Obtenha a empresa autenticada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = usuarioService.buscarPorEmail(auth.getName());

        // Verifique se a empresa autenticada é a proprietária da vaga
        if (vaga.getEmpresa().getId().equals(usuarioAutenticado.getId())) {
            model.addAttribute("vaga", vaga);
            return "vagas/cadastro";
        } else {
            return "redirect:/error"; 
        }
	}

	@PostMapping("/editar")
	public String editar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr) {

		service.salvar(vaga);
		attr.addFlashAttribute("success", "vagas.edit.sucess");
		return "redirect:/empresa/vagas";
	}

	@GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addFlashAttribute("sucess", "vagas.delete.sucess");
        return "redirect:/empresa/vagas";
    }

}
