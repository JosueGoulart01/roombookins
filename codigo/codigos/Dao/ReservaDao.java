package Dao;

import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
    private Connection cn;

    public ReservaDao() {
        BancoDados.getInstancia();
        cn = BancoDados.getConexao();
    }

    public List<Reserva> carregar(List<Usuario> usuarios, List<Sala> salas) throws SQLException {
        List<Reserva> listaReservas = new ArrayList<>();

        String sql = "SELECT * FROM Reserva";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String codigoSala = rs.getString("codigoSala");
                LocalDateTime inicio = rs.getTimestamp("dataInicio").toLocalDateTime();
                LocalDateTime fim = rs.getTimestamp("dataFim").toLocalDateTime();

                Usuario usuario = usuarios.stream()
                        .filter(u -> u.getCpf().equals(cpf))
                        .findFirst()
                        .orElse(null);

                Sala sala = salas.stream()
                        .filter(s -> s.getCodigoSala().equals(codigoSala))
                        .findFirst()
                        .orElse(null);

                if (usuario != null && sala != null) {
                    Reserva reserva = new Reserva(usuario, sala, inicio, fim);
                    reserva.setId(id);
                    listaReservas.add(reserva);
                }
            }
        }

        return listaReservas;
    }

   public void salvar(List<Reserva> reservas) throws SQLException {
    String sql = "INSERT INTO Reserva (dataInicio, dataFim, cpf, codigoSala) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
        for (Reserva reserva : reservas) {
            ps.setTimestamp(1, Timestamp.valueOf(reserva.getDataInicio()));
            ps.setTimestamp(2, Timestamp.valueOf(reserva.getDataFim()));
            ps.setString(3, reserva.getUsuario().getCpf());
            ps.setString(4, reserva.getSala().getCodigoSala());
            ps.addBatch();
        }
        ps.executeBatch();
    }
}
public void salvar(Reserva reserva) throws SQLException {
    List<Reserva> lista = new ArrayList<>();
    lista.add(reserva);
    salvar(lista);
}
}
