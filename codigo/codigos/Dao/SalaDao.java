package Dao;

import Model.Endereco;
import Model.Sala;
import Model.SalaPremium;
import Model.SalaStandard;
import Model.SalaVip;
import java.sql.*;
import java.util.ArrayList;

public class SalaDao {
    private final Connection cn;

    public SalaDao() {
        BancoDados.getInstancia();
        cn = BancoDados.getConexao();
    }

    public void salvar(ArrayList<Sala> listaSalas) throws SQLException {
        String sql = "INSERT INTO Sala (codigoSala, capacidade, tipo, cep, rua, numero, cidade, pais) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
             "ON DUPLICATE KEY UPDATE capacidade = VALUES(capacidade), tipo = VALUES(tipo), " +
             "cep = VALUES(cep), rua = VALUES(rua), numero = VALUES(numero), cidade = VALUES(cidade), pais = VALUES(pais)";

        try (PreparedStatement stmt = cn.prepareStatement(sql)) {
            for (Sala sala : listaSalas) {
                Endereco endereco = sala.getEndereco();

                stmt.setString(1, sala.getCodigoSala());
                stmt.setInt(2, sala.getCapacidade());
                stmt.setInt(3, sala.getTipo());
                stmt.setString(4, endereco.getCep());
                stmt.setString(5, endereco.getRua());
                stmt.setString(6, endereco.getNumero());
                stmt.setString(7, endereco.getCidade());
                stmt.setString(8, endereco.getPais());

                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public ArrayList<Sala> carregar() throws Exception {
        ArrayList<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM Sala";

        try (PreparedStatement stmt = cn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String codigo = rs.getString("codigoSala");
                int capacidade = rs.getInt("capacidade");
                int tipo = rs.getInt("tipo");

                Endereco endereco = new Endereco(
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cidade"),
                        rs.getString("pais")
                );

                Sala sala = switch (tipo) {
                    case 1 -> new SalaPremium(codigo, capacidade, tipo, endereco);
                    case 2 -> new SalaVip(codigo, capacidade, tipo, endereco);
                    case 3 -> new SalaStandard(codigo, capacidade, tipo, endereco);
                    default -> throw new IllegalArgumentException("Tipo de sala inválido ao carregar.");
                };

                lista.add(sala);
            }
        }

        return lista;
    }
}
