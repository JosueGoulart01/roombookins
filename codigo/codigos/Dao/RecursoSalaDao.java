package Dao;

import Model.Recurso;
import Model.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecursoSalaDao {

    public void salvarRecursosDaSala(Sala sala) throws SQLException {
        String sql = "INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES (?, ?)";

        try (Connection conn = BancoDados.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Recurso recurso : sala.getRecursos()) {
                stmt.setString(1, sala.getCodigoSala());
                stmt.setInt(2, recurso.getId()); // Recurso deve ter getId()
                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }
}
