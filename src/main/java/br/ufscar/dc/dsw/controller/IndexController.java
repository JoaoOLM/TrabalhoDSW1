package br.ufscar.dc.dsw.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

            System.out.println("Login: " + login);
            System.out.println("Senha: " + senha);

			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
            System.out.println("entrou aq1");

			if (!erros.isExisteErros()) {
				UsuarioDAO usuario_dao = new UsuarioDAO();
                EmpresaDAO empresa_dao = new EmpresaDAO();
                ProfissionalDAO profissional_dao = new ProfissionalDAO();
				Usuario usuario = usuario_dao.getByEmail(login);
                System.out.println(usuario);                
                if (usuario != null) {
                    Empresa empresa = empresa_dao.getById_Empresa(usuario.getId());
                    Profissional profissional = profissional_dao.getById_usuario(usuario.getId());
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
                        if (usuario.isAdmin()){
						    response.sendRedirect("admin/");
                        }
						else if (empresa != null){ 
                            request.getSession().setAttribute("empresaLogada", empresa);
                            response.sendRedirect("vagasempresa/");
                        }
                        else if (profissional != null){
                            request.getSession().setAttribute("profissionalLogado", profissional);
                            response.sendRedirect("vagas/");
                        }
                        return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}

        request.getSession().invalidate();
		request.setAttribute("mensagens", erros);

		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
    }
}
