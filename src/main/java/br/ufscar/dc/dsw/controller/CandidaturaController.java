
package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/candidatura/*")
public class CandidaturaController extends HttpServlet {
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
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");
        Erro erros = new Erro();

        if (usuario == null || profissional == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                // case "/cadastrar":
                //     apresentaFormCadastro(request, response);
                //     break;
                // case "/inserir":
                //     inserir(request, response);
                //     break;
                // case "/remover":
                //     remover(request, response);
                //     break;
                case "/inscrever":
                    apresentaFormInscricao(request, response);
                    break;
                // case "/atualizar":
                //     atualizar(request, response);
                //     break;
                case "/candidaturas":
                    lista(request, response);
                    break;
                default:
                    listaVagas(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaVagas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VagaDAO vagaDAO = new VagaDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        String cidade = request.getParameter("cidade");

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");

        List<Vaga> listaVagas;
        if (cidade == null || cidade.isEmpty()) {
            listaVagas = vagaDAO.getAllOpenAvailable(profissional.getId());
        } else {
            listaVagas = vagaDAO.getOpenVagasByCidade(cidade);
        }

        // Obtenha todas as cidades para o filtro
        List<String> cidades = empresaDAO.getAllCidades();
        request.setAttribute("cidades", cidades);
        
        request.setAttribute("listaVagasAbertas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/listaVagas.jsp");
        dispatcher.forward(request, response);
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CandidaturaDAO candidaturaDAO = new CandidaturaDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");

        List<Candidatura> listaCandidaturas = candidaturaDAO.getAllByProfissional(profissional.getId());
        request.setAttribute("listaCandidaturas", listaCandidaturas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormInscricao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Vaga vaga = vagaDAO.get(id);
        System.out.println("Vaga: " + vaga);
        System.out.println("id: " + id);
        request.setAttribute("vaga", vaga);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/formulario.jsp");
        dispatcher.forward(request, response);
    }
}
