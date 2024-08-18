package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfissionalDAO profissionalDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        profissionalDAO = new ProfissionalDAO();
        usuarioDAO = new UsuarioDAO();
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
        List<Profissional> listaProfissionais = profissionalDAO.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        forward(request, response, "/logado/profissional/lista.jsp");
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/logado/profissional/formulario.jsp");
    }

    private void inserir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Usuario usuario = criarUsuario(request);
        usuarioDAO.insert(usuario);
        usuario = usuarioDAO.getByEmail(usuario.getEmail());

        Profissional profissional = criarProfissional(request, usuario);
        profissionalDAO.insert(profissional);

        response.sendRedirect("lista");
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = profissionalDAO.get(id);
        request.setAttribute("profissional", profissional);
        forward(request, response, "/logado/profissional/formulario.jsp");
    }

    private void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = criarUsuario(request);

        Profissional profissional = criarProfissional(request, usuario);
        profissional.setId(id);

        profissionalDAO.update(profissional);
        response.sendRedirect("lista");
    }

    private void remover(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = new Profissional(id);
        profissionalDAO.delete(profissional);
        response.sendRedirect("lista");
    }

    private Usuario criarUsuario(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
        return new Usuario(email, nome, senha, admin);
    }

    private Profissional criarProfissional(HttpServletRequest request, Usuario usuario) throws ServletException {
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        Date dataNascimento = converterData(request.getParameter("dataNascimento"));
        return new Profissional(usuario, cpf, telefone, Profissional.Sexo.values()[sexo], dataNascimento);
    }

    private Date converterData(String dataString) throws ServletException {
        try {
            return Date.valueOf(dataString);
        } catch (IllegalArgumentException e) {
            throw new ServletException("Erro ao converter data de nascimento", e);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
