package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public static Profissional setProfissional(ResultSet rs) throws SQLException {
        Profissional profissional = new Profissional();
        try {
            profissional.setId(rs.getLong("p.id"));
            profissional.setUsuario(UsuarioDAO.setUsuario(rs));
            profissional.setCpf(rs.getString("p.cpf"));
            profissional.setTelefone(rs.getString("p.telefone"));
            profissional.setSexo(Profissional.Sexo.values()[rs.getInt("p.sexo")]);
            profissional.setDataNascimento(rs.getDate("p.data_nascimento"));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return profissional;
    }

    public void insert(Profissional profissional) {
        String sql = "INSERT INTO profissional (id_usuario, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getUsuario().getId());
            statement.setString(2, profissional.getCpf());
            statement.setString(3, profissional.getTelefone());
            statement.setInt(4, profissional.getSexo().ordinal());  
            statement.setDate(5, new Date(profissional.getDataNascimento().getTime()));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {
        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * FROM profissional p, usuario u WHERE p.id_usuario = u.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                listaProfissionais.add(setProfissional(resultSet));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE u FROM usuario u JOIN profissional p ON u.id = p.id_usuario WHERE p.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET CPF = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getTelefone());
            statement.setInt(3, profissional.getSexo().ordinal());
            statement.setDate(4, new Date(profissional.getDataNascimento().getTime()));
            statement.setLong(5, profissional.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long id) {
        Profissional profissional = null;

        String sql = "SELECT * FROM profissional p, usuario u WHERE p.id_usuario = u.id AND p.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profissional = setProfissional(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public Profissional getById_usuario(Long id_usuario) {
        Profissional profissional = null;

        String sql = "SELECT * FROM profissional p, usuario u WHERE p.id_usuario = u.id AND p.id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_usuario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profissional = setProfissional(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}
