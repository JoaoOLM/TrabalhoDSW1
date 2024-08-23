package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
public class HomeController {

    @Autowired
    private IVagaService vagaService;

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping("/home")
    public String home(Model model) {
        String empresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("vagas", vagaService.buscarTodos());
        model.addAttribute("empresaLogada", empresaLogada);
        return "home"; 
    }

    @GetMapping("/filtrar")
	public String listarVagas(@RequestParam(value = "cidade", required = false) String cidade, ModelMap model) {
		List<Vaga> vagas;
		if (cidade != null && !cidade.isEmpty()) {
			vagas = vagaService.buscarPorCidade(cidade);
		} else {
			vagas = vagaService.buscarTodos();
		}
		model.addAttribute("vagas", vagas);
		model.addAttribute("cidades", empresaService.buscarTodasCidades()); 
		return "home";
	}
}
