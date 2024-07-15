package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.SmartphoneDAO;
import br.ufscar.dc.dsw.domain.Smartphone;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/smartphones/*")
public class SmartphoneController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private SmartphoneDAO dao;

    @Override
    public void init() {
        dao = new SmartphoneDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
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
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Smartphone> listaSmartphones = dao.getAll();
        request.setAttribute("listaSmartphones", listaSmartphones);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/smartphone/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/smartphone/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Smartphone smartphone = dao.get(id);
        request.setAttribute("smartphone", smartphone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/smartphone/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String sistemaOperacional = request.getParameter("sistemaOperacional");
        Integer memoriaRamGB = Integer.parseInt(request.getParameter("memoriaRamGB"));
        Integer armazenamentoGB = Integer.parseInt(request.getParameter("armazenamentoGB"));
        Float tamanhoTela = Float.parseFloat(request.getParameter("tamanhoTela"));
        Double preco = Double.parseDouble(request.getParameter("preco"));
        
        Smartphone smartphone = new Smartphone(marca, modelo, sistemaOperacional, memoriaRamGB, armazenamentoGB, tamanhoTela, preco);
        dao.insert(smartphone);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String sistemaOperacional = request.getParameter("sistemaOperacional");
        Integer memoriaRamGB = Integer.parseInt(request.getParameter("memoriaRamGB"));
        Integer armazenamentoGB = Integer.parseInt(request.getParameter("armazenamentoGB"));
        Float tamanhoTela = Float.parseFloat(request.getParameter("tamanhoTela"));
        Double preco = Double.parseDouble(request.getParameter("preco"));
        
        Smartphone smartphone = new Smartphone(id, marca, modelo, sistemaOperacional, memoriaRamGB, armazenamentoGB, tamanhoTela, preco);
        dao.update(smartphone);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Smartphone smartphone = new Smartphone(id);
        dao.delete(smartphone);
        response.sendRedirect("lista");
    }
}
