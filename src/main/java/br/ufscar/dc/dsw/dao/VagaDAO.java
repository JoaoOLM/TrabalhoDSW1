package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;

public class VagaDAO extends GenericDAO {

    public static Vaga setVaga(ResultSet rs) throws SQLException {
        Vaga vaga = new Vaga();
        vaga.setId(rs.getLong("v.id"));
        vaga.setEmpresa(EmpresaDAO.setEmpresa(rs));
        vaga.setDescricao(rs.getString("v.descricao"));
        vaga.setRemuneracao(rs.getDouble("v.remuneracao"));
        vaga.setDataCriacao(rs.getTimestamp("v.data_criacao"));
        vaga.setDataLimiteInscricao(rs.getDate("v.data_limite_inscricao"));
        return vaga;
    }

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (empresa_id, descricao, remuneracao, data_limite_inscricao, data_criacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setDouble(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setTimestamp(5, vaga.getDataCriacao());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vaga", e);
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                listaVagas.add(setVaga(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas", e);
        }
        return listaVagas;
    }

    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, vaga.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar vaga", e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET empresa_id = ?, descricao = ?, remuneracao = ?, data_limite_inscricao = ?, data_criacao = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setDouble(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setTimestamp(5, vaga.getDataCriacao());
            statement.setLong(6, vaga.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga", e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND v.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    vaga = setVaga(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por id", e);
        }
        return vaga;
    }

    public List<Vaga> getAllVagasByEmpresa(Long id) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND e.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaVagas.add(setVaga(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas por empresa", e);
        }
        return listaVagas;
    }

    public List<Vaga> getAllOpenVagasByEmpresa(Long empresaId) {
        System.out.println(empresaId);
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v "
                   + "JOIN empresa e ON v.empresa_id = e.id " 
                   + "JOIN usuario u ON e.id_usuario = u.id " 
                   + "WHERE e.id = ? " 
                   + "AND DATE(v.data_limite_inscricao) >= CURDATE() " 
                   +  "ORDER BY v.data_limite_inscricao";

    
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, empresaId);
            System.out.println(statement);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaVagas.add(setVaga(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas abertas para a empresa", e);
        }
    
        return listaVagas;
    }
    
    
    
    public List<Vaga> getAllExpiredVagasByEmpresa(Long empresaId) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v "
                   + "JOIN empresa e ON v.empresa_id = e.id " 
                   + "JOIN usuario u ON e.id_usuario = u.id " 
                   + "WHERE e.id = ? " 
                   + "AND DATE(v.data_limite_inscricao) < CURDATE() " 
                   +  "ORDER BY v.data_limite_inscricao";
    
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, empresaId);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaVagas.add(setVaga(resultSet));
                }
            }
    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas expiradas para a empresa", e);
        }
    
        return listaVagas;
    }

    public List<Vaga> getAllOpenVagas() {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND DATE(v.data_limite_inscricao) >= CURDATE()";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                listaVagas.add(setVaga(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas abertas", e);
        }
        return listaVagas;
    }

    public List<Vaga> getAllOpenAvailable(Long idProfissional) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND DATE(v.data_limite_inscricao) >= CURDATE() AND v.id NOT IN (SELECT vaga_id FROM candidatura WHERE profissional_id = ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, idProfissional);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaVagas.add(setVaga(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas abertas disponíveis", e);
        }
        return listaVagas;
    }

    public List<Vaga> getOpenVagasByCidade(String cidade) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga v JOIN empresa e ON v.empresa_id = e.id JOIN usuario u ON e.id_usuario = u.id WHERE e.cidade = ? AND v.data_limite_inscricao > CURRENT_DATE";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cidade);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaVagas.add(setVaga(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas abertas por cidade", e);
        }
        return listaVagas;
    }
}
