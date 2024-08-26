package br.ufscar.dc.dsw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Profissional.Sexo;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

@SpringBootApplication
public class EstagioMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstagioMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IVagaDAO vagaDAO, BCryptPasswordEncoder encoder) {
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

            Vaga v1 = new Vaga();
            v1.setEmpresa(e1);
            v1.setDescricao("FullStack");
            v1.setRemuneracao(1000.00);
			// Definindo a data limite de inscrição usando SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date utilDate = sdf.parse("10-02-2028");
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertendo java.util.Date para java.sql.Date
                v1.setDataLimiteInscricao(sqlDate);
            } catch (ParseException e) {
                e.printStackTrace(); // Trate a exceção conforme necessário
            }
            vagaDAO.save(v1);

            Empresa e2 = new Empresa();
            e2.setEmail("empresa2@empresa.com");
            e2.setPassword(encoder.encode("empresa"));
            e2.setNome("Empresa2");
            e2.setRole("ROLE_EMPRESA");
            e2.setCNPJ("11.111.111/1111-11");
            e2.setCidade("Brasília");
            e2.setDescricao("Empresa de aviação nacional");
            usuarioDAO.save(e2);

			Vaga v2 = new Vaga();
            v2.setEmpresa(e2);
            v2.setDescricao("Analista de dados");
            v2.setRemuneracao(500.00);
			// Definindo a data limite de inscrição usando SimpleDateFormat
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date utilDate = sdf.parse("15-04-2025");
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertendo java.util.Date para java.sql.Date
                v2.setDataLimiteInscricao(sqlDate);
            } catch (ParseException e) {
                e.printStackTrace(); // Trate a exceção conforme necessário
            }
            vagaDAO.save(v2);

            Profissional p1 = new Profissional();
            p1.setEmail("profissional@profissional.com");
            p1.setPassword(encoder.encode("profissional"));
            p1.setNome("Profissional1");
            p1.setRole("ROLE_PROFISSIONAL");
            p1.setCpf("000.000.000-00");
            p1.setTelefone("(00) 00000-0000");
            p1.setSexo(Sexo.FEMININO);
            // Definindo a data limite de inscrição usando SimpleDateFormat
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date utilDate = sdf.parse("01-01-0001");
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertendo java.util.Date para java.sql.Date
                p1.setDataNascimento(sqlDate);
            } catch (ParseException e) {
                e.printStackTrace(); // Trate a exceção conforme necessário
            }
            usuarioDAO.save(p1);

        };
    }
}
