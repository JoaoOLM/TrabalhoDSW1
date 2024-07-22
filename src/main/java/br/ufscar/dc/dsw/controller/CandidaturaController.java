
package br.ufscar.dc.dsw.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import static br.ufscar.dc.dsw.Constants.MAX_FILE_SIZE;
import static br.ufscar.dc.dsw.Constants.MAX_REQUEST_SIZE;
import static br.ufscar.dc.dsw.Constants.MEMORY_THRESHOLD;
import static br.ufscar.dc.dsw.Constants.UPLOAD_DIRECTORY;
import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/candidaturas/*")
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                case "/cadastrar":
                    apresentaFormCadastro(request, response);
                    break;
                case "/inserir":
                    inserir(request, response);
                    break;
                // case "/remover":
                //     remover(request, response);
                //     break;
                case "/inscrever":
                    apresentaFormInscricao(request, response);
                    break;
                // case "/atualizar":
                //     atualizar(request, response);
                //     break;
                case "/lista":
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

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/logado/profissional/formulario.jsp");
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
        System.out.println("entrou onde devia");
        request.setAttribute("vaga", vaga);
        // response.sendRedirect("Estagio/logado/candidatura/formulario.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/candidatura/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissionalLogado");

        if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				List<FileItem> formItems = upload.parseRequest(request);
                Vaga vaga = null;
                String fileName = null;
                if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							String originalFileName = new File(item.getName()).getName();
                            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                            fileName = profissional.getId() + fileExtension;
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            request.getSession().setAttribute("message", "File " + fileName + " has uploaded successfully!");
						} else {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            if (fieldName.equals("idVaga")) {
                                Long idVaga = Long.parseLong(fieldValue);
                                vaga = vagaDAO.get(idVaga);
                            }
                        }
					}
                    Candidatura candidatura = new Candidatura(vaga, profissional, fileName);
                    candidaturaDAO.insert(candidatura);
                    response.sendRedirect("lista");
				}
			} catch (Exception ex) {
				request.getSession().setAttribute("message", "There was an error: " + ex.getMessage());
			}
		}
    }
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
