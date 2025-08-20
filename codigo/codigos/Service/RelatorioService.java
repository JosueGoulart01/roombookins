package Service;

import Dao.RelatorioDao;
import Dao.SalaDao;
import Dao.UsuarioDao;
import Model.Reserva;
import Model.Sala;
import Model.Usuario;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;

public class RelatorioService {

    private final ArrayList<Reserva> listaReservas;
    private final ArrayList<Usuario> listaUsuarios;
    private final ArrayList<Sala> listaSalas;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final SalaDao salaDao = new SalaDao();
    private final RelatorioDao relatorioDao = new RelatorioDao();

    public RelatorioService(ArrayList<Reserva> listaReservas,
                            ArrayList<Usuario> listaUsuarios,
                            ArrayList<Sala> listaSalas) {
        this.listaReservas = listaReservas;
        this.listaUsuarios = listaUsuarios;
        this.listaSalas = listaSalas;
    }

    public void carregarDados() {
        try {
            if (listaUsuarios.isEmpty()) {
                listaUsuarios.addAll(usuarioDao.carregar());
            }
            if (listaSalas.isEmpty()) {
                listaSalas.addAll(salaDao.carregar());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Map<Usuario, Double> obterMediaHorasPorCliente() {
        try {
            return relatorioDao.calcularMediaHorasPorCliente();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular média de horas por cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyMap();
        }
    }

    public Map<String, Long> obterReservasPorSala(int mes, int ano) {
        try {
            return relatorioDao.calcularReservasPorSala(mes, ano);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular reservas por sala: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyMap();
        }
    }

    public List<Reserva> buscarReservasNoPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        try {
            return relatorioDao.buscarReservasNoPeriodo(inicio, fim, listaUsuarios, listaSalas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar reservas no período: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyList();
        }
    }
}
