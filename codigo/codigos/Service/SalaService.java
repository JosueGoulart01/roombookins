package Service;

import Dao.SalaDao;
import Model.*;
import java.util.ArrayList;
import java.util.List;

public class SalaService {

    private final SalaDao salaDao;

    public SalaService(SalaDao salaDao) {
        this.salaDao = salaDao;
    }

    public void carregarSalasSeNecessario(List<Sala> listaSalas) {
        try {
            if (listaSalas.isEmpty()) {
                listaSalas.addAll(salaDao.carregar());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar salas: " + e.getMessage(), e);
        }
    }

    public void validarCamposObrigatorios(String codigo, String capacidadeStr, Endereco endereco, String tipoSala) {
        if (codigo == null || codigo.isEmpty() ||
            capacidadeStr == null || capacidadeStr.isEmpty() ||
            tipoSala == null || tipoSala.isEmpty()) {
            throw new IllegalArgumentException("Por favor, preencha todos os campos obrigatórios.");
        }

        if (endereco.getCep().isEmpty() || endereco.getRua().isEmpty() ||
            endereco.getNumero().isEmpty() || endereco.getCidade().isEmpty() ||
            endereco.getPais().isEmpty()) {
            throw new IllegalArgumentException("Por favor, preencha todos os campos do endereço.");
        }
    }

    public int converterCapacidade(String capacidadeStr) {
        try {
            int capacidade = Integer.parseInt(capacidadeStr);
            if (capacidade <= 0) {
                throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
            }
            return capacidade;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Capacidade deve ser um número válido.");
        }
    }

    public Sala criarSala(String codigo, int capacidade, int tipo, Endereco endereco) throws Exception {
        return switch (tipo) {
            case 1 -> new SalaPremium(codigo, capacidade, tipo, endereco);
            case 2 -> new SalaVip(codigo, capacidade, tipo, endereco);
            case 3 -> new SalaStandard(codigo, capacidade, tipo, endereco);
            default -> throw new IllegalArgumentException("Tipo de sala inválido.");
        };
    }

    public int obterTipoPorNome(String tipoSala) {
        return switch (tipoSala) {
            case "Premium" -> 1;
            case "Vip" -> 2;
            case "Standard" -> 3;
            default -> throw new IllegalArgumentException("Tipo de sala inválido.");
        };
    }

    public void salvarSalas(List<Sala> salas) {
        try {
            salaDao.salvar(new ArrayList<>(salas));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar salas: " + e.getMessage(), e);
        }
    }
}