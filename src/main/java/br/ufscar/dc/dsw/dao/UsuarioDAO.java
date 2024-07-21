package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public static Usuario setUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("u.id"));
        usuario.setNome(rs.getString("u.nome"));
        usuario.setEmail(rs.getString("u.email"));
        usuario.setSenha(rs.getString("u.senha"));
        usuario.setAdmin(rs.getBoolean("u.is_admin"));
        return usuario;
    }

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, is_admin) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setBoolean(4, usuario.isAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário", e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario u";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                listaUsuarios.add(setUsuario(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return listaUsuarios;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário", e);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, is_admin = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setBoolean(4, usuario.isAdmin());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    public Usuario getByEmail(String emailUsuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario u WHERE email = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, emailUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = setUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email", e);
        }
        return usuario;
    }
}
