package br.ufscar.dc.dsw.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;

public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {
        String sql = "INSERT INTO Vaga (empresa_id, descricao, remuneracao, data_limite_inscricao, status, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setBigDecimal(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setString(5, vaga.getStatus().toString());
            statement.setTimestamp(6, vaga.getDataCriacao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT v.*, e.* FROM Vaga v JOIN Empresa e ON v.empresa_id = e.id ORDER BY v.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("v.id");
                Long empresaId = resultSet.getLong("v.empresa_id");
                String descricao = resultSet.getString("v.descricao");
                BigDecimal remuneracao = resultSet.getBigDecimal("v.remuneracao");
                Date dataLimiteInscricao = resultSet.getDate("v.data_limite_inscricao");
                Integer status = resultSet.getInt("v.status");
                Timestamp dataCriacao = resultSet.getTimestamp("v.data_criacao");

                Long empresaIdRes = resultSet.getLong("e.id");
                String email = resultSet.getString("e.email");
                String senha = resultSet.getString("e.senha");
                String CNPJ = resultSet.getString("e.CNPJ");
                String nome = resultSet.getString("e.nome");
                String descricaoEmpresa = resultSet.getString("e.descricao");
                String cidade = resultSet.getString("e.cidade");

                Empresa empresa = new Empresa(empresaIdRes, email, senha, CNPJ, nome, descricaoEmpresa, cidade);
                Vaga vaga = new Vaga(id, empresa, descricao, remuneracao, dataLimiteInscricao, Vaga.StatusVaga.values()[status], dataCriacao);
                listaVagas.add(vaga);
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
        String sql = "UPDATE Vaga SET empresa_id = ?, descricao = ?, remuneracao = ?, data_limite_inscricao = ?, status = ?, data_criacao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getEmpresa().getId());
            statement.setString(2, vaga.getDescricao());
            statement.setBigDecimal(3, vaga.getRemuneracao());
            statement.setDate(4, vaga.getDataLimiteInscricao());
            statement.setString(5, vaga.getStatus().toString());
            statement.setTimestamp(6, vaga.getDataCriacao());
            statement.setLong(7, vaga.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;
        String sql = "SELECT v.*, e.* FROM Vaga v JOIN Empresa e ON v.empresa_id = e.id WHERE v.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long vagaId = resultSet.getLong("v.id");
                Long empresaId = resultSet.getLong("v.empresa_id");
                String descricao = resultSet.getString("v.descricao");
                BigDecimal remuneracao = resultSet.getBigDecimal("v.remuneracao");
                Date dataLimiteInscricao = resultSet.getDate("v.data_limite_inscricao");
                Integer status = resultSet.getInt("v.status");
                Timestamp dataCriacao = resultSet.getTimestamp("v.data_criacao");

                Long empresaIdRes = resultSet.getLong("e.id");
                String email = resultSet.getString("e.email");
                String senha = resultSet.getString("e.senha");
                String CNPJ = resultSet.getString("e.CNPJ");
                String nome = resultSet.getString("e.nome");
                String descricaoEmpresa = resultSet.getString("e.descricao");
                String cidade = resultSet.getString("e.cidade");

                Empresa empresa = new Empresa(empresaIdRes, email, senha, CNPJ, nome, descricaoEmpresa, cidade);
                vaga = new Vaga(vagaId, empresa, descricao, remuneracao, dataLimiteInscricao, Vaga.StatusVaga.values()[status], dataCriacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }
}
