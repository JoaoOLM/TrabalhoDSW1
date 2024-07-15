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

    public void insert(Profissional profissional) {
        String sql = "INSERT INTO Profissional (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getTelefone());
            statement.setInt(6, profissional.getSexo().ordinal());  
            statement.setDate(7, new Date(profissional.getDataNascimento().getTime()));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {
        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * FROM Profissional ORDER BY id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("CPF");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                Integer sexo = resultSet.getInt("sexo");
                java.sql.Date dataNascimento = resultSet.getDate("data_nascimento");

                Profissional profissional = new Profissional(id, email, senha, cpf, nome, telefone, Profissional.Sexo.values()[sexo], dataNascimento);
                listaProfissionais.add(profissional);
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
        String sql = "DELETE FROM Profissional WHERE id = ?";

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
        String sql = "UPDATE Profissional SET email = ?, senha = ?, CPF = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getTelefone());
            statement.setInt(6, profissional.getSexo().ordinal());
            statement.setDate(7, new Date(profissional.getDataNascimento().getTime()));
            statement.setLong(8, profissional.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long id) {
        Profissional profissional = null;

        String sql = "SELECT * FROM Profissional WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("CPF");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                Integer sexo = resultSet.getInt("sexo");
                java.sql.Date dataNascimento = resultSet.getDate("data_nascimento");

                profissional = new Profissional(id, email, senha, cpf, nome, telefone, Profissional.Sexo.values()[sexo], dataNascimento);
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
