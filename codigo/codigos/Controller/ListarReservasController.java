package Controller;

import Model.Reserva;
import View.ListarReservasView;
import java.util.List;
import javax.swing.*;

public class ListarReservasController {

    public ListarReservasController(JDesktopPane desktopPane, List<Reserva> listaReservas) {
        ListarReservasView view = new ListarReservasView(listaReservas);
        desktopPane.add(view);
        view.setVisible(true);

        // Centraliza a janela interna
        int x = (desktopPane.getWidth() - view.getWidth()) / 2;
        int y = (desktopPane.getHeight() - view.getHeight()) / 2;
        view.setLocation(x, y);

        try {
            view.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
