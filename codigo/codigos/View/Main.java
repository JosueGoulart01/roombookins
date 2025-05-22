package View;

import Controller.MenuController;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MenuController();
        });
    }
}
