package br.ufscar.dc.dsw.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public class CandidaturaDAO extends GenericDAO {

    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (vaga_id, profissional_id, arquivo_curriculo, data_candidatura, status) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, candidatura.getVaga().getId());
            statement.setLong(2, candidatura.getProfissional().getId());
            statement.setString(3, candidatura.getArquivoCurriculo());
            statement.setTimestamp(4, new java.sql.Timestamp(candidatura.getDataCandidatura().getTime())); 
            statement.setInt(5, candidatura.getStatus().ordinal());  

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Candidatura> getAll() {
        List<Candidatura> listaCandidaturas = new ArrayList<>();

        String sql = "SELECT c.*, v.*, p.* FROM Candidatura c JOIN Vaga v ON c.vaga_id = v.id " +
                     " JOIN Profissional p ON c.profissional_id = p.id ORDER BY c.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long candidaturaId = resultSet.getLong("c.id");
                String arquivoCurriculo = resultSet.getString("c.arquivo_curriculo");
                Timestamp dataCandidatura = resultSet.getTimestamp("c.data_candidatura");
                int status = resultSet.getInt("c.status");

                // Dados da Vaga
                Long vagaId = resultSet.getLong("v.id");
                Long empresaId = resultSet.getLong("v.empresa_id");
                String descricaoVaga = resultSet.getString("v.descricao");
                BigDecimal remuneracao = resultSet.getBigDecimal("v.remuneracao");
                java.sql.Date dataLimiteInscricao = resultSet.getDate("v.data_limite_inscricao");
                int statusVaga = resultSet.getInt("v.status");
                Timestamp dataCriacao = resultSet.getTimestamp("v.data_criacao");

                Vaga vaga = new Vaga(vagaId, null, descricaoVaga, remuneracao, dataLimiteInscricao, Vaga.StatusVaga.values()[statusVaga], dataCriacao);

                // Dados do Profissional
                Long profissionalId = resultSet.getLong("p.id");
                String email = resultSet.getString("p.email");
                String senha = resultSet.getString("p.senha");
                String cpf = resultSet.getString("p.CPF");
                String nome = resultSet.getString("p.nome");
                String telefone = resultSet.getString("p.telefone");
                int sexo = resultSet.getInt("p.sexo");
                java.sql.Date dataNascimento = resultSet.getDate("p.data_nascimento");

                Profissional profissional = new Profissional(profissionalId, email, senha, cpf, nome, telefone, Profissional.Sexo.values()[sexo], dataNascimento);
                
                Candidatura candidatura = new Candidatura(candidaturaId, vaga, profissional, arquivoCurriculo, dataCandidatura, Candidatura.Status.values()[status]);
                listaCandidaturas.add(candidatura);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCandidaturas;
    }

    public void delete(Candidatura candidatura) {
        String sql = "DELETE FROM Candidatura WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, candidatura.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Candidatura candidatura) {
        String sql = "UPDATE Candidatura SET vaga_id = ?, profissional_id = ?, arquivo_curriculo = ?, data_candidatura = ?, status = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, candidatura.getVaga().getId());
            statement.setLong(2, candidatura.getProfissional().getId());
            statement.setString(3, candidatura.getArquivoCurriculo());
            statement.setTimestamp(4, new java.sql.Timestamp(candidatura.getDataCandidatura().getTime()));  
            statement.setInt(5, candidatura.getStatus().ordinal()); 
            statement.setLong(6, candidatura.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Candidatura get(Long id) {
        Candidatura candidatura = null;
    
        String sql = "SELECT c.*, v.*, p.* FROM Candidatura c JOIN Vaga v ON c.vaga_id = v.id " +
                     "JOIN Profissional p ON c.profissional_id = p.id WHERE c.id = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
    
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long candidaturaId = resultSet.getLong("c.id");
                String arquivoCurriculo = resultSet.getString("c.arquivo_curriculo");
                Timestamp dataCandidatura = resultSet.getTimestamp("c.data_candidatura");
                int status = resultSet.getInt("c.status");

                // Dados da Vaga
                Long vagaId = resultSet.getLong("v.id");
                Vaga vaga = new VagaDAO().get(vagaId);
                
                // Dados do Profissional
                Long profissionalId = resultSet.getLong("p.id");
                Profissional profissional = new ProfissionalDAO().get(profissionalId);
        
                candidatura = new Candidatura(candidaturaId, vaga, profissional, arquivoCurriculo, dataCandidatura, Candidatura.Status.values()[status]);
            }
    
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidatura;
    }
    
}
