package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Smartphone;

public class SmartphoneDAO extends GenericDAO {

    public void insert(Smartphone smartphone) {
        String sql = "INSERT INTO Smartphone (marca, modelo, sistema_operacional, memoria_ram_gb, armazenamento_gb, tamanho_tela, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, smartphone.getMarca());
            statement.setString(2, smartphone.getModelo());
            statement.setString(3, smartphone.getSistemaOperacional());
            statement.setInt(4, smartphone.getMemoriaRamGB());
            statement.setInt(5, smartphone.getArmazenamentoGB());
            statement.setFloat(6, smartphone.getTamanhoTela());
            statement.setDouble(7, smartphone.getPreco());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Smartphone> getAll() {
        List<Smartphone> listaSmartphones = new ArrayList<>();

        String sql = "SELECT * FROM Smartphone ORDER BY id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String sistemaOperacional = resultSet.getString("sistema_operacional");
                Integer memoriaRamGB = resultSet.getInt("memoria_ram_gb");
                Integer armazenamentoGB = resultSet.getInt("armazenamento_gb");
                Float tamanhoTela = resultSet.getFloat("tamanho_tela");
                Double preco = resultSet.getDouble("preco");

                Smartphone smartphone = new Smartphone(id, marca, modelo, sistemaOperacional, memoriaRamGB, armazenamentoGB, tamanhoTela, preco);
                listaSmartphones.add(smartphone);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaSmartphones;
    }

    public void delete(Smartphone smartphone) {
        String sql = "DELETE FROM Smartphone WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, smartphone.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Smartphone smartphone) {
        String sql = "UPDATE Smartphone SET marca = ?, modelo = ?, sistema_operacional = ?, memoria_ram_gb = ?, armazenamento_gb = ?, tamanho_tela = ?, preco = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, smartphone.getMarca());
            statement.setString(2, smartphone.getModelo());
            statement.setString(3, smartphone.getSistemaOperacional());
            statement.setInt(4, smartphone.getMemoriaRamGB());
            statement.setInt(5, smartphone.getArmazenamentoGB());
            statement.setFloat(6, smartphone.getTamanhoTela());
            statement.setDouble(7, smartphone.getPreco());
            statement.setLong(8, smartphone.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Smartphone get(Long id) {
        Smartphone smartphone = null;

        String sql = "SELECT * FROM Smartphone WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String sistemaOperacional = resultSet.getString("sistema_operacional");
                Integer memoriaRamGB = resultSet.getInt("memoria_ram_gb");
                Integer armazenamentoGB = resultSet.getInt("armazenamento_gb");
                Float tamanhoTela = resultSet.getFloat("tamanho_tela");
                Double preco = resultSet.getDouble("preco");

                smartphone = new Smartphone(id, marca, modelo, sistemaOperacional, memoriaRamGB, armazenamentoGB, tamanhoTela, preco);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return smartphone;
    }
}
