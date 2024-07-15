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

    public void insert(Empresa empresa) {
        String sql = "INSERT INTO Empresa (email, senha, CNPJ, nome, descricao, cidade) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getEmail());
            statement.setString(2, empresa.getSenha());
            statement.setString(3, empresa.getCNPJ());
            statement.setString(4, empresa.getNome());
            statement.setString(5, empresa.getDescricao());
            statement.setString(6, empresa.getCidade());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Empresa> getAll() {
        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * FROM Empresa ORDER BY id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String CNPJ = resultSet.getString("CNPJ");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");

                Empresa empresa = new Empresa(id, email, senha, CNPJ, nome, descricao, cidade);
                listaEmpresas.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }

    public void delete(Empresa empresa) {
        String sql = "DELETE FROM Empresa WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, empresa.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET email = ?, senha = ?, CNPJ = ?, nome = ?, descricao = ?, cidade = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getEmail());
            statement.setString(2, empresa.getSenha());
            statement.setString(3, empresa.getCNPJ());
            statement.setString(4, empresa.getNome());
            statement.setString(5, empresa.getDescricao());
            statement.setString(6, empresa.getCidade());
            statement.setLong(7, empresa.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa get(Long id) {
        Empresa empresa = null;

        String sql = "SELECT * FROM Empresa WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String CNPJ = resultSet.getString("CNPJ");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");

                empresa = new Empresa(id, email, senha, CNPJ, nome, descricao, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
}
