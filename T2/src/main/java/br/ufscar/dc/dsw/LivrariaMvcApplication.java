package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class LivrariaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setEmail("admin@admin.com");
			u1.setPassword(encoder.encode("admin"));
			u1.setNome("Administrador");
			u1.setRole("ROLE_ADMIN");
			usuarioDAO.save(u1);

			Empresa e1 = new Empresa();
			e1.setEmail("empresa@empresa.com");
			e1.setPassword(encoder.encode("empresa"));
			e1.setNome("Empresa1");
			e1.setRole("ROLE_EMPRESA");
			e1.setCNPJ("00.000.000/0000-00");
			e1.setCidade("Água Boa");
			e1.setDescricao("Melhor empresa do mundo");
			usuarioDAO.save(e1);
			
			Profissional p1 = new Profissional();
			p1.setEmail("profissional@profissional.com");
			p1.setPassword(encoder.encode("profissional"));
			p1.setNome("Profissional1");
			p1.setRole("ROLE_PROFISSIONAL");
			p1.setCpf("000.000.000-00");
			p1.setTelefone("(00) 00000-0000");
			p1.setSexo(1);
			p1.setDataNascimento("00/00/0001");
			usuarioDAO.save(p1);
			
			// Usuario u3 = new Usuario();
			// u3.setUsername("fulano");
			// u3.setPassword(encoder.encode("123"));
			// u3.setCPF("367.318.380-04");
			// u3.setName("Fulano Silva");
			// u3.setRole("ROLE_USER");
			// u3.setEnabled(true);
			// usuarioDAO.save(u3);
			
			// Editora e1 = new Editora();
			// e1.setCNPJ("55.789.390/0008-99");
			// e1.setNome("Companhia das Letras");
			// editoraDAO.save(e1);
			
			// Editora e2 = new Editora();
			// e2.setCNPJ("71.150.470/0001-40");
			// e2.setNome("Record");
			// editoraDAO.save(e2);
			
			// Editora e3 = new Editora();
			// e3.setCNPJ("32.106.536/0001-82");
			// e3.setNome("Objetiva");
			// editoraDAO.save(e3);
			
			// Livro l1 = new Livro();
			// l1.setTitulo("Ensaio sobre a Cegueira");
			// l1.setAutor("José Saramago");
			// l1.setAno(1995);
			// l1.setPreco(BigDecimal.valueOf(54.9));
			// l1.setEditora(e1);
			// livroDAO.save(l1);
			
			// Livro l2 = new Livro();
			// l2.setTitulo("Cem anos de Solidão");
			// l2.setAutor("Gabriel Garcia Márquez");
			// l2.setAno(1977);
			// l2.setPreco(BigDecimal.valueOf(59.9));
			// l2.setEditora(e2);
			// livroDAO.save(l2);
			
			// Livro l3 = new Livro();
			// l3.setTitulo("Diálogos Impossíveis");
			// l3.setAutor("Luis Fernando Verissimo");
			// l3.setAno(2012);
			// l3.setPreco(BigDecimal.valueOf(22.9));
			// l3.setEditora(e3);
			// livroDAO.save(l3);
		};
	}
}
