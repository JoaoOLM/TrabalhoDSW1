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

    public static Candidatura setCandidatura(ResultSet rs) throws SQLException {
        Candidatura candidatura = new Candidatura();

        try {
            candidatura.setId(rs.getLong("c.id"));
            candidatura.setProfissional(ProfissionalDAO.setProfissional(rs));
            candidatura.setVaga(VagaDAO.setVaga(rs));
            candidatura.setArquivoCurriculo(rs.getString("c.arquivo_curriculo"));
            candidatura.setDataCandidatura(rs.getTimestamp("c.data_candidatura"));
            candidatura.setStatus(Candidatura.Status.values()[rs.getInt("c.status")]);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return candidatura;
    }

    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (vaga_id, profissional_id, arquivo_curriculo, status) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, candidatura.getVaga().getId());
            statement.setLong(2, candidatura.getProfissional().getId());
            statement.setString(3, candidatura.getArquivoCurriculo());
            statement.setInt(4, candidatura.getStatus().ordinal());  

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    
        String sql =  "SELECT * FROM candidatura c, profissional p, usuario u, vaga v, empresa e, usuario ue WHERE u.id = p.id_usuario AND ue. id = e.id_usuario AND v.empresa_id = e.id AND c.vaga_id = v.id AND c.profissional_id = p.id AND c.id = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
    
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                candidatura = setCandidatura(resultSet);
            }
    
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidatura;
    }

    
    public List<Candidatura> getAll() {
        List<Candidatura> listaCandidaturas = new ArrayList<>();

        String sql =  "SELECT * FROM candidatura c, profissional p, usuario u, vaga v, empresa e, usuario ue WHERE u.id = p.id_usuario AND ue. id = e.id_usuario AND v.empresa_id = e.id AND c.vaga_id = v.id AND c.profissional_id = p.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listaCandidaturas.add(setCandidatura(resultSet));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCandidaturas;
    }

    public List<Candidatura> getAllByProfissional(Long id) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();

        String sql =  "SELECT * FROM candidatura c, profissional p, usuario u, vaga v, empresa e, usuario ue WHERE u.id = p.id_usuario AND ue. id = e.id_usuario AND v.empresa_id = e.id AND c.vaga_id = v.id AND c.profissional_id = p.id AND p.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listaCandidaturas.add(setCandidatura(resultSet));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCandidaturas;
    }
    
}
