package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {

    public static Empresa setEmpresa(ResultSet rs) throws SQLException {
        Empresa empresa = new Empresa();
        empresa.setId(rs.getLong("e.id"));
        empresa.setUsuario(UsuarioDAO.setUsuario(rs));
        empresa.setCNPJ(rs.getString("e.cnpj"));
        empresa.setDescricao(rs.getString("e.descricao"));
        empresa.setCidade(rs.getString("e.cidade"));
        return empresa;
    }

    public void insert(Empresa empresa) {
        String sql = "INSERT INTO Empresa (id_usuario, CNPJ, descricao, cidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, empresa.getUsuario().getId());
            statement.setString(2, empresa.getCNPJ());
            statement.setString(3, empresa.getDescricao());
            statement.setString(4, empresa.getCidade());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir empresa", e);
        }
    }

    public List<Empresa> getAll() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa e, usuario u WHERE e.id_usuario = u.id";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                listaEmpresas.add(setEmpresa(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empresas", e);
        }
        return listaEmpresas;
    }

    public void delete(Empresa empresa) {
        String sql = "DELETE u FROM usuario u JOIN empresa e ON u.id = e.id_usuario WHERE e.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, empresa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar empresa", e);
        }
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET CNPJ = ?, descricao = ?, cidade = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, empresa.getCNPJ());
            statement.setString(2, empresa.getDescricao());
            statement.setString(3, empresa.getCidade());
            statement.setLong(4, empresa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empresa", e);
        }
    }

    public Empresa get(Long id) {
        Empresa empresa = null;
        String sql = "SELECT * FROM empresa e, usuario u WHERE e.id_usuario = u.id AND e.id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empresa = setEmpresa(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empresa por id", e);
        }
        return empresa;
    }

    public Empresa getById_Empresa(Long id_usuario) {
        Empresa empresa = null;
        String sql = "SELECT * FROM empresa e, usuario u WHERE e.id_usuario = u.id AND e.id_usuario = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id_usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empresa = setEmpresa(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empresa por id de usuário", e);
        }
        return empresa;
    }

    public List<String> getAllCidades() {
        List<String> cidades = new ArrayList<>();
        String query = "SELECT DISTINCT cidade FROM empresa";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cidades.add(rs.getString("cidade"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as cidades", e);
        }

        return cidades;
    }
}
