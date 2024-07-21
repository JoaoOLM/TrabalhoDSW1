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

        try {
            vaga.setId(rs.getLong("v.id"));
            vaga.setEmpresa(EmpresaDAO.setEmpresa(rs));
            vaga.setDescricao(rs.getString("v.descricao"));
            vaga.setRemuneracao(rs.getDouble("v.remuneracao"));
            vaga.setDataCriacao(rs.getTimestamp("v.data_criacao"));
            vaga.setDataLimiteInscricao(rs.getDate("v.data_limite_inscricao"));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return vaga;
    }

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (empresa_id, descricao, remuneracao, data_limite_inscricao, data_criacao) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setDouble(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setTimestamp(5, vaga.getDataCriacao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id";;

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listaVagas.add(setVaga(resultSet));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET empresa_id = ?, descricao = ?, remuneracao = ?, data_limite_inscricao = ?, data_criacao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setDouble(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setTimestamp(5, vaga.getDataCriacao());
            statement.setLong(6, vaga.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND v.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                vaga = setVaga(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }

    public List<Vaga> getAllVagasByEmpresa(Long id) {
        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * FROM vaga v, empresa e, usuario u WHERE v.empresa_id = e.id AND e.id_usuario = u.id AND e.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                listaVagas.add(setVaga(resultSet));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }
}
