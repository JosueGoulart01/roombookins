package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Reserva;
import Model.Sala;
import Model.Usuario;

public class RelatorioDao {
    public Map<Usuario, Double> calcularMediaHorasPorCliente() throws SQLException {
        Map<Usuario, Double> resultado = new HashMap<>();

        String sql = """
            SELECT u.cpf, u.nome, 
                   SUM(TIMESTAMPDIFF(HOUR, r.dataInicio, r.dataFim)) AS totalHoras,
                   COUNT(*) AS totalReservas
            FROM Reserva r
            JOIN Usuario u ON r.cpf = u.cpf
            GROUP BY u.cpf, u.nome
        """;

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                double totalHoras = rs.getDouble("totalHoras");
                long totalReservas = rs.getLong("totalReservas");

                Usuario usuario = new Usuario(nome, cpf, null, false);
                double media = totalHoras / totalReservas;

                resultado.put(usuario, media);
            }
        }

        return resultado;
    }

    public Map<String, Long> calcularReservasPorSala(int mes, int ano) throws SQLException {
        Map<String, Long> resultado = new HashMap<>();

        String sql = """
            SELECT r.codigoSala, COUNT(*) AS totalReservas
            FROM Reserva r
            WHERE MONTH(r.dataInicio) = ? AND YEAR(r.dataInicio) = ?
            GROUP BY r.codigoSala
        """;

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, ano);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put(rs.getString("codigoSala"), rs.getLong("totalReservas"));
            }
        }

        return resultado;
    }

    public List<Reserva> buscarReservasNoPeriodo(LocalDateTime inicio, LocalDateTime fim,
                                             List<Usuario> usuarios, List<Sala> salas) throws SQLException {
    List<Reserva> reservas = new ArrayList<>();

    String sql = """
        SELECT * FROM Reserva
        WHERE dataInicio <= ? AND dataFim >= ?
    """;

    try (PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setTimestamp(1, Timestamp.valueOf(fim));
        ps.setTimestamp(2, Timestamp.valueOf(inicio));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String cpf = rs.getString("cpf");
            String codigoSala = rs.getString("codigoSala");
            LocalDateTime dataInicio = rs.getTimestamp("dataInicio").toLocalDateTime();
            LocalDateTime dataFim = rs.getTimestamp("dataFim").toLocalDateTime();

            Usuario usuario = usuarios.stream()
                    .filter(u -> u.getCpf().equals(cpf))
                    .findFirst().orElse(null);

            Sala sala = salas.stream()
                    .filter(s -> s.getCodigoSala().equals(codigoSala))
                    .findFirst().orElse(null);

            if (usuario != null && sala != null) {
                Reserva reserva = new Reserva(usuario, sala, dataInicio, dataFim);
                reserva.setId(id);
                reservas.add(reserva);
            }
        }
    }

    return reservas;
}
}
