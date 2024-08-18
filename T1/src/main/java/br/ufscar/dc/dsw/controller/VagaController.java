package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.EmailService;
import br.ufscar.dc.dsw.util.Erro;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(urlPatterns = "/vagas/*")
public class VagaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private VagaDAO vagaDAO;
    private CandidaturaDAO candidaturaDAO;

    @Override
    public void init() {
        vagaDAO = new VagaDAO();
        candidaturaDAO = new CandidaturaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");
        Erro erros = new Erro();

        if (usuario == null || empresa == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "/cadastrar":
                    apresentaFormCadastro(request, response);
                    break;
                case "/inserir":
                    inserir(request, response);
                    break;
                case "/remover":
                    remover(request, response);
                    break;
                case "/editar":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizar":
                    atualizar(request, response);
                    break;
                case "/editarCandidatura":
                    editarCandidatura(request, response);
                    break;
                case "/atualizarCandidatura":
                    atualizarCandidatura(request, response);
                    break;
                case "/candidaturas":
                    getCandidaturasByVaga(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }
    

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VagaDAO vagaDAO = new VagaDAO();

        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");

        System.out.println(empresa.getId());
        // Filtra as vagas abertas
        List<Vaga> listaVagasAbertas = vagaDAO.getAllOpenVagasByEmpresa(empresa.getId());

        // Filtra as vagas expiradas
        List<Vaga> listaVagasExpiradas = vagaDAO.getAllExpiredVagasByEmpresa(empresa.getId());

        request.setAttribute("listaVagasAbertas", listaVagasAbertas);
        request.setAttribute("listaVagasExpiradas", listaVagasExpiradas);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vagas/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vagas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void inserir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");

        String descricao = request.getParameter("descricao");
        Double remuneracao = Double.parseDouble(request.getParameter("remuneracao"));
        String dataLimiteString = request.getParameter("dataLimite");
        System.out.println("datalimiteString: " + dataLimiteString);
        java.sql.Date dataLimiteInscricao = null;
        try {
            dataLimiteInscricao = java.sql.Date.valueOf(dataLimiteString); 
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao converter data limite", e);
        }
        System.out.println(dataLimiteInscricao);
        Vaga vaga = new Vaga(empresa, descricao, remuneracao, dataLimiteInscricao);
        System.out.println("vaga "+vaga);
        vagaDAO.insert(vaga);
        response.sendRedirect("lista");
    }
    
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");
        request.setAttribute("empresa", empresa);

        Long id = Long.parseLong(request.getParameter("id"));
        Vaga vaga = vagaDAO.get(id);
        request.setAttribute("vaga", vaga);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vagas/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));

        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");

        String descricao = request.getParameter("descricao");
        Double remuneracao = Double.parseDouble(request.getParameter("remuneracao"));

        String dataLimiteString = request.getParameter("dataLimite");
        java.sql.Date dataLimiteInscricao = null;

        try {
            dataLimiteInscricao = java.sql.Date.valueOf(dataLimiteString); 
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao converter data limite", e);
        }

        Vaga vaga = new Vaga(id, empresa, descricao, remuneracao, dataLimiteInscricao);

        vagaDAO.update(vaga);
        response.sendRedirect("lista");
    }

    private void editarCandidatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));

        Candidatura candidatura = candidaturaDAO.get(id);
        request.setAttribute("candidatura", candidatura);
        System.out.println("candidatura:" + candidatura);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vagas/formularioCandidatura.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarCandidatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Entrou mesmo");

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));

        System.out.println("E não saiu");

        // Obtém a candidatura existente
        Candidatura candidatura = candidaturaDAO.get(id);

        if (candidatura == null) {
            throw new ServletException("Candidatura não encontrada.");
        }

        Profissional profissional = candidatura.getProfissional();


        String statusParam = request.getParameter("status");
        System.out.println("Status: " + statusParam);
        if (statusParam == null || statusParam.trim().isEmpty()) {
            throw new ServletException("Status não fornecido.");
        }

        Integer status;
        try {
            status = Integer.parseInt(statusParam);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao converter o status", e);
        }
        
        candidatura.setStatus(Candidatura.Status.values()[status]);

        // Atualiza a candidatura no banco de dados
        candidaturaDAO.update(candidatura);

        Properties prop = new Properties();
        InputStream is = VagaController.class.getClassLoader().getResourceAsStream("config.properties");

        if (is != null) {
            prop.load(is);
        } else {
            throw new FileNotFoundException("config.properties not found in the classpath");
        }

        String username = prop.getProperty("username");
        String name = prop.getProperty("name");

        // Envia e-mails conforme o status
        try {
            EmailService emailService = new EmailService();
            InternetAddress from = new InternetAddress(username, name);
            InternetAddress to = new InternetAddress(profissional.getUsuario().getEmail(), profissional.getUsuario().getNome());

            if (status == Candidatura.Status.NAO_SELECIONADO.ordinal()) {
                String subject = "Status da Candidatura: Não Selecionado";
                String body = "Olá " + profissional.getUsuario().getNome() + ",\n\nInfelizmente, sua candidatura para a vaga " + candidatura.getVaga().getDescricao() +  " não foi selecionada. Agradecemos seu interesse.";
                emailService.send(from, to, subject, body);
            } else if (status == Candidatura.Status.ENTREVISTA.ordinal()) {
                String linkEntrevista = request.getParameter("linkEntrevista");
                String horarioEntrevista = request.getParameter("horarioEntrevista");
                String subject = "Status da Candidatura: Entrevista Agendada";
                String body = "Olá " + profissional.getUsuario().getNome() + ",\n\n" +
                            "Parabéns! Você foi selecionado para uma entrevista para a candidatura à vaga " +
                            candidatura.getVaga().getDescricao() + ". A entrevista será pelo link " + 
                            linkEntrevista + " às " + horarioEntrevista + ".";
                emailService.send(from, to, subject, body);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao enviar e-mail", e);
        }

        // Redireciona para a lista de candidaturas
        response.sendRedirect("vagas/candidaturas");
    }

    private void remover(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Vaga vaga = new Vaga(id);

        vagaDAO.delete(vaga);
        response.sendRedirect("lista");
    }

    private void getCandidaturasByVaga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CandidaturaDAO candidaturaDAO = new CandidaturaDAO();

        Empresa empresa = (Empresa) request.getSession().getAttribute("empresaLogada");

        Long id = Long.parseLong(request.getParameter("id"));
        
        List<Candidatura> candidaturas = candidaturaDAO.getAllByVaga(id);

        request.setAttribute("candidaturas", candidaturas);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/vagas/listaCandidaturas.jsp");
        dispatcher.forward(request, response);
    }
}
