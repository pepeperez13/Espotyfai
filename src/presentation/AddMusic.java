package presentation;

import javax.swing.*;

public class AddMusic extends JFrame {
    ConfigurationMenu plantilla = new ConfigurationMenu();
    BarReproduccion barraReproduccion = new BarReproduccion();

    public AddMusic () {
        //plantilla.add(barraReproduccion);
    }

    public static void main (String[] args) {
        ConfigurationMenu add = new ConfigurationMenu();
        add.setVisible(true);
    }
}
