package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import br.ufscar.dc.dsw.service.impl.EmailService;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
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

    @Autowired
    private EmailService emailService;

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
            model.addAttribute("error", "Vaga não encontrada.");
            return "redirect:/error";
        }
        model.addAttribute("vaga", vaga);
        return "candidatura/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(name = "vagaId") Long vagaId, @RequestParam(name = "arquivoCurriculo") MultipartFile arquivoCurriculo, RedirectAttributes attr) throws IOException {

        Vaga vaga = vagaService.buscarPorId(vagaId);
        if (vaga == null) {
            attr.addFlashAttribute("error", "Vaga não encontrada.");
            return "redirect:/candidatura/cadastrar/" + vagaId;
        }

        Candidatura candidatura = new Candidatura();
        candidatura.setVaga(vaga);
        Profissional profissional = this.getProfissionalAutenticado();
        candidatura.setProfissional(profissional);

        Optional<Candidatura> candidaturaExistente = candidaturaService.buscarPorVagaEProfissional(vaga, profissional);
        if (candidaturaExistente.isPresent()) {
            attr.addFlashAttribute("error", "Você já se candidatou a esta vaga.");
            return "redirect:/candidatura/cadastrar/" + vagaId;
        }

        // Verifica se o arquivo de currículo não está vazio e se o tamanho é menor que 10MB
        if (!arquivoCurriculo.isEmpty()) {
            long maxSizeInBytes = 10 * 1024 * 1024; // 10MB em bytes
            if (arquivoCurriculo.getSize() > maxSizeInBytes) {
                attr.addFlashAttribute("error", "O currículo não pode ter mais de 10MB.");
                return "redirect:/candidatura/cadastrar/" + vagaId;
            }
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
        if (candidatura == null) {
            model.addAttribute("error", "Candidatura não encontrada.");
            return "redirect:/error";
        }

        Vaga vaga = candidatura.getVaga();
        Empresa empresaAutenticada = getEmpresaAutenticado();

        if (!vaga.getEmpresa().getId().equals(empresaAutenticada.getId())) {
            return "redirect:/error";
        }

        model.addAttribute("candidatura", candidatura);
        return "candidatura/edicao";
    }

    @PostMapping("/editar")
    public String editar(@RequestParam("id") Long id, @RequestParam("status") String status, RedirectAttributes attr, ModelMap model) throws UnsupportedEncodingException, AddressException {
        Candidatura candidatura = candidaturaService.buscarPorId(id);
        if (candidatura == null) {
            model.addAttribute("error", "Candidatura não encontrada.");
            return "redirect:/error";
        }

        // Atualiza o status da candidatura
        candidatura.setStatus(Status.valueOf(status));
        candidaturaService.salvar(candidatura);
        attr.addFlashAttribute("success", "Candidatura atualizada com sucesso.");

        if (status.equals("ENTREVISTA")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String agora = LocalDateTime.now().format(formatter);
            model.addAttribute("agora", agora);
            // Passa a candidatura para o modelo para preencher o formulário corretamente
            model.addAttribute("candidatura", candidatura);
            return "candidatura/entrevista";
        } else if (status.equals("NAO_SELECIONADO")) {
            InternetAddress from = new InternetAddress("testearca092@gmail.com", "Email de Teste");
            InternetAddress to = new InternetAddress(candidatura.getProfissional().getEmail());
            String subject = "Candidatura não aprovada";
            String body = "Sua candidatura para a vaga " + candidatura.getVaga().getDescricao() + " não foi aprovada.";
            emailService.send(from, to, subject, body);
        }

        return "redirect:/candidatura/listar/" + candidatura.getVaga().getId();
    }

    @PostMapping("/email")
    public String email(@RequestParam("id") Long id,
            @RequestParam(value = "horarioEntrevista")  LocalDateTime horarioEntrevista,
            @RequestParam(value = "linkVideoconferencia") String linkVideoconferencia,
            ModelMap model) throws UnsupportedEncodingException, AddressException {

        Candidatura candidatura = candidaturaService.buscarPorId(id);
        if (candidatura == null) {
            model.addAttribute("error", "Candidatura não encontrada.");
            return "redirect:/error";
        }

        candidatura.setHorarioEntrevista(horarioEntrevista);
        candidatura.setLinkVideoconferencia(linkVideoconferencia);
        candidaturaService.salvar(candidatura);

        // Enviar o email
        InternetAddress from = new InternetAddress("testearca092@gmail.com", "Email de Teste");
        InternetAddress to = new InternetAddress(candidatura.getProfissional().getEmail());
        String subject = "Aproovado para entrevista";
        String body = "Sua candidatura para a vaga " + candidatura.getVaga().getDescricao() + " foi aprovada para entrevista. O horário da entrevista é " + horarioEntrevista + " e o link para a videoconferência é " + linkVideoconferencia;
        emailService.send(from, to, subject, body);

        return "redirect:/candidatura/listar/" + candidatura.getVaga().getId();
    }

    @GetMapping(value = "/download/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
        Candidatura candidatura = candidaturaService.buscarPorId(id);

        if (candidatura == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf");
        try {
            response.getOutputStream().write(candidatura.getArquivoCurriculo());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Error :- " + e.getMessage());
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        candidaturaService.excluir(id);
        attr.addFlashAttribute("success", "candidatura.delete.sucess");
        return "redirect:/candidatura/listar";
    }
}
