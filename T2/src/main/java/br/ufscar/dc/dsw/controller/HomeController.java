package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
public class HomeController {

    @Autowired
    private IVagaService vagaService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IUsuarioService usuarioService;

    private Usuario getUsuarioAutenticado() {
        UsuarioDetails usuarioDetails = null;
        try {
            usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
        }
        System.out.println("\n \n \n \n \n \n \n \n \n" + usuarioDetails);
        if (usuarioDetails != null){
            Usuario user = usuarioDetails.getUsuario();
            return usuarioService.buscarPorId(user.getId());
        }
        return null;
    }

    @GetMapping("/home")
    public String home(Model model) {
        String empresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = getUsuarioAutenticado();
        if (usuario != null && usuario.getRole().equals("ROLE_PROFISSIONAL")){
            model.addAttribute("vagas", vagaService.buscarVagasNaoCandidatadasPorProfissional(usuario.getId()));
        } else {
            model.addAttribute("vagas", vagaService.buscarTodos());
        }
        model.addAttribute("empresaLogada", empresaLogada);
        model.addAttribute("cidades", empresaService.buscarTodasCidades());
        return "home"; 
    }

    @GetMapping("/filtrar")
    public String listarVagas(@RequestParam(value = "cidade", required = false) String cidade, ModelMap model) {
        List<Vaga> vagas;
        List<Vaga> vagasNC;
        Usuario usuario = getUsuarioAutenticado();
        String empresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if (usuario != null && usuario.getRole().equals("ROLE_PROFISSIONAL")) {
            vagasNC = vagaService.buscarVagasNaoCandidatadasPorProfissional(usuario.getId());
            if (cidade != null && !cidade.isEmpty()) {
                vagas = vagaService.buscarPorCidade(cidade);
                vagas.retainAll(vagasNC);
            } else {
                vagas = vagaService.buscarTodos();
                vagas.retainAll(vagasNC);
            }
        } else {
            if (cidade != null && !cidade.isEmpty()) {
                vagas = vagaService.buscarPorCidade(cidade);
            } else {
                vagas = vagaService.buscarTodos();
            }
        }
        
        model.addAttribute("empresaLogada", empresaLogada);
        model.addAttribute("vagas", vagas);
        model.addAttribute("cidades", empresaService.buscarTodasCidades()); 
        return "home";
    }
}
