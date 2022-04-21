package presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddMusic extends JFrame {
    ConfigurationMenu plantilla = new ConfigurationMenu();
    BarReproduccion barraReproduccion = new BarReproduccion();

    public AddMusic () throws IOException {
        //plantilla.add(barraReproduccion);
    }

    public static void main (String[] args) throws IOException {
        ConfigurationMenu add = new ConfigurationMenu();
        add.setVisible(true);
    }
}
