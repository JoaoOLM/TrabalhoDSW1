package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Candidatura.Status;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/candidatura")
public class CandidaturaController {

    @Autowired
    private ICandidaturaService candidaturaService;

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IVagaService vagaService;

    @Autowired
    private IUsuarioService usuarioService;

    private Usuario getUsuarioAutenticado() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario user = usuarioDetails.getUsuario();
        return usuarioService.buscarPorId(user.getId());
    }

    private Profissional getProfissionalAutenticado() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario user = usuarioDetails.getUsuario();
        return profissionalService.buscarPorId(user.getId());
    }

    private Empresa getEmpresaAutenticado() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario user = usuarioDetails.getUsuario();
        return empresaService.buscarPorId(user.getId());
    }

    @GetMapping("/cadastrar/{vagaId}")
    public String cadastrar(@PathVariable("vagaId") Long vagaId, ModelMap model) {
        Vaga vaga = vagaService.buscarPorId(vagaId);
        if (vaga == null) {
            System.out.println("Vaga não encontrada");
            return "redirect:/error";
        }
        model.addAttribute("vaga", vaga);

        return "candidatura/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(name = "vagaId") Long vagaId, @RequestParam(name = "arquivoCurriculo") MultipartFile arquivoCurriculo, RedirectAttributes attr) throws IOException {

        // Obtém a vaga pelo ID
        Vaga vaga = vagaService.buscarPorId(vagaId);
        if (vaga == null) {
            attr.addFlashAttribute("error", "Vaga não encontrada.");
            return "redirect:/candidatura/cadastrar/" + vagaId;
        }

        // Cria a candidatura
        Candidatura candidatura = new Candidatura();
        candidatura.setVaga(vaga);
        Profissional profissional = this.getProfissionalAutenticado();
        candidatura.setProfissional(profissional);

        // Verifica se o profissional já se candidatou a esta vaga
        Optional<Candidatura> candidaturaExistente = candidaturaService.buscarPorVagaEProfissional(vaga, profissional);
        if (candidaturaExistente.isPresent()) {
            attr.addFlashAttribute("error", "Você já se candidatou a esta vaga.");
            return "redirect:/candidatura/cadastrar/" + vagaId;
        }

        // Processa o arquivo do currículo
        if (!arquivoCurriculo.isEmpty()) {
            candidatura.setArquivoCurriculo(arquivoCurriculo.getBytes());
        }

        candidaturaService.salvar(candidatura);
        attr.addFlashAttribute("success", "Candidatura realizada com sucesso.");
        return "redirect:/home";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        Profissional profissional = this.getProfissionalAutenticado();
        model.addAttribute("candidaturas", candidaturaService.buscarPorProfissional(profissional));
        return "candidatura/lista";
    }

    @GetMapping("/listar/{id}")
    public String listar(@PathVariable("id") Long id, ModelMap model) {
        Vaga vaga = vagaService.buscarPorId(id);
        Empresa empresa = this.getEmpresaAutenticado();
        if (!empresa.getVagas().contains(vaga)) {
            return "redirect:/error";
        }
        model.addAttribute("candidaturas", candidaturaService.buscarPorVaga(vaga));
        return "candidatura/lista";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {

        Candidatura candidatura = candidaturaService.buscarPorId(id);
        Vaga vaga = candidatura.getVaga();
        Empresa empresaAutenticada = getEmpresaAutenticado();

        // Verifique se a empresa autenticada é a proprietária da vaga
        if (vaga.getEmpresa().getId().equals(empresaAutenticada.getId())) {
            model.addAttribute("candidatura", candidatura);
            return "candidatura/edicao";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/editar")
    public String editar(@RequestParam("profissionalId") Long profissionalId,
            @RequestParam("vagaId") Long vagaId,
            @RequestParam("status") Integer status,
            @RequestParam(value = "horarioEntrevista", required = false) LocalDateTime horarioEntrevista,
            @RequestParam(value = "linkVideoconferencia", required = false) String linkVideoconferencia,
            RedirectAttributes attr) {

        Profissional profissional = profissionalService.buscarPorId(profissionalId);
        Vaga vaga = vagaService.buscarPorId(vagaId);

        // Carregar e atualizar a candidatura
        Candidatura candidatura = candidaturaService.buscarPorVagaEProfissional(vaga, profissional).orElseThrow();
        candidatura.setStatus(Status.values()[status]);

        if (Status.ENTREVISTA.equals(Status.values()[status])) {
            //Email aprovado
        } else {
            //Email recusado
        }

        candidaturaService.salvar(candidatura);

        // Adicionar mensagem de sucesso e redirecionar
        attr.addFlashAttribute("success", "Candidatura atualizada com sucesso.");
        return "redirect:/candidatura/listar";
    }

    @GetMapping(value = "/download/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
        Candidatura candidatura = candidaturaService.buscarPorId(id);

        // set content type
        response.setContentType("application/pdf");

        // add response header (caso queira forçar o download)
        // response.addHeader("Content-Disposition", "attachment; filename=" + candidatura.getName());
        try {
            // copies all bytes to an output stream
            response.getOutputStream().write(candidatura.getArquivoCurriculo());

            // flushes output stream
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Error :- " + e.getMessage());
        }
    }
}
