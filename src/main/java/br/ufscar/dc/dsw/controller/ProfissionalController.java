package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
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
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.isAdmin()) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas usuários administradores têm acesso a essa página!");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    inserir(request, response);
                    break;
                case "/remocao":
                    remover(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualizar(request, response);
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
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        List<Profissional> listaProfissionais = profissionalDAO.getAll();

        request.setAttribute("listaProfissionais", listaProfissionais);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void inserir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

        Usuario usuario = new Usuario(email, nome, senha, admin);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insert(usuario);
        usuario = usuarioDAO.getByEmail(email);

        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        Integer sexo = Integer.parseInt(request.getParameter("sexo"));
        String dataNascimentoString = request.getParameter("dataNascimento");
        java.sql.Date dataNascimento = null;
        try {
            dataNascimento = java.sql.Date.valueOf(dataNascimentoString); 
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao converter data de nascimento", e);
        }
        System.out.println(dataNascimento);
        System.out.println(sexo);
        Profissional profissional = new Profissional(usuario, cpf, telefone, Profissional.Sexo.values()[sexo], dataNascimento);

        dao.insert(profissional);
        response.sendRedirect("lista");
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = dao.get(id);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

        Usuario usuario = new Usuario(email, nome, senha, admin);

        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        Integer sexo = Integer.parseInt(request.getParameter("sexo"));
        String dataNascimentoString = request.getParameter("dataNascimento");
        java.sql.Date dataNascimento = null;

        try {
            dataNascimento = java.sql.Date.valueOf(dataNascimentoString); 
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao converter data de nascimento", e);
        }
    
        Profissional profissional = new Profissional(id, usuario, cpf, telefone, Profissional.Sexo.values()[sexo], dataNascimento);

        dao.update(profissional);
        response.sendRedirect("lista");
    }

    private void remover(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Profissional profissional = new Profissional(id);

        dao.delete(profissional);
        response.sendRedirect("lista");
    }

}
