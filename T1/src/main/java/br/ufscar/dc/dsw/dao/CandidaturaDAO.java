package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;

public class CandidaturaDAO extends GenericDAO {

    public static Candidatura setCandidatura(ResultSet rs) throws SQLException {
        Candidatura candidatura = new Candidatura();
        candidatura.setId(rs.getLong("c.id"));
        candidatura.setProfissional(ProfissionalDAO.setProfissional(rs));
        candidatura.setVaga(VagaDAO.setVaga(rs));
        candidatura.setArquivoCurriculo(rs.getString("c.arquivo_curriculo"));
        candidatura.setDataCandidatura(rs.getTimestamp("c.data_candidatura"));
        candidatura.setStatus(Candidatura.Status.values()[rs.getInt("c.status")]);
        return candidatura;
    }

    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (vaga_id, profissional_id, arquivo_curriculo, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, candidatura.getVaga().getId());
            statement.setLong(2, candidatura.getProfissional().getId());
            statement.setString(3, candidatura.getArquivoCurriculo());
            statement.setInt(4, candidatura.getStatus().ordinal());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir candidatura", e);
        }
    }

    public List<Candidatura> getAll() {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidatura c "
                   + "JOIN profissional p ON c.profissional_id = p.id "
                   + "JOIN vaga v ON c.vaga_id = v.id "
                   + "JOIN empresa e ON v.empresa_id = e.id "
                   + "JOIN usuario u ON p.id_usuario = u.id "
                   + "JOIN usuario ue ON e.id_usuario = ue.id";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                listaCandidaturas.add(setCandidatura(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidaturas", e);
        }
        return listaCandidaturas;
    }

    public void delete(Candidatura candidatura) {
        String sql = "DELETE FROM Candidatura WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, candidatura.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidatura", e);
        }
    }

    public void update(Candidatura candidatura) {
        String sql = "UPDATE Candidatura SET vaga_id = ?, profissional_id = ?, arquivo_curriculo = ?, data_candidatura = ?, status = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, candidatura.getVaga().getId());
            statement.setLong(2, candidatura.getProfissional().getId());
            statement.setString(3, candidatura.getArquivoCurriculo());
            statement.setTimestamp(4, new Timestamp(candidatura.getDataCandidatura().getTime()));
            statement.setInt(5, candidatura.getStatus().ordinal());
            statement.setLong(6, candidatura.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar candidatura", e);
        }
    }

    public Candidatura get(Long id) {
        Candidatura candidatura = null;
        String sql = "SELECT * FROM candidatura c "
                   + "JOIN profissional p ON c.profissional_id = p.id "
                   + "JOIN vaga v ON c.vaga_id = v.id "
                   + "JOIN empresa e ON v.empresa_id = e.id "
                   + "JOIN usuario u ON p.id_usuario = u.id "
                   + "JOIN usuario ue ON e.id_usuario = ue.id "
                   + "WHERE c.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    candidatura = setCandidatura(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidatura por id", e);
        }
        return candidatura;
    }

    public List<Candidatura> getAllByProfissional(Long id) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidatura c "
                   + "JOIN profissional p ON c.profissional_id = p.id "
                   + "JOIN vaga v ON c.vaga_id = v.id "
                   + "JOIN empresa e ON v.empresa_id = e.id "
                   + "JOIN usuario u ON p.id_usuario = u.id "
                   + "JOIN usuario ue ON e.id_usuario = ue.id "
                   + "WHERE p.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaCandidaturas.add(setCandidatura(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidaturas por profissional", e);
        }
        return listaCandidaturas;
    }

    public List<Candidatura> getAllByVaga(Long vagaId) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidatura c "
                   + "JOIN profissional p ON c.profissional_id = p.id "
                   + "JOIN vaga v ON c.vaga_id = v.id "
                   + "JOIN empresa e ON v.empresa_id = e.id "
                   + "JOIN usuario u ON p.id_usuario = u.id "
                   + "JOIN usuario ue ON e.id_usuario = ue.id "
                   + "WHERE v.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, vagaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaCandidaturas.add(setCandidatura(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidaturas por vaga", e);
        }
        return listaCandidaturas;
    }
    
}
