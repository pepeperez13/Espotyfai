package presentation;

import presentation.view.MainConfigurationView;

import javax.swing.*;

public class AddMusic extends JFrame {
    MainConfigurationView plantilla = new MainConfigurationView();
    BarReproduccion barraReproduccion = new BarReproduccion();

    public AddMusic () {
        //plantilla.add(barraReproduccion);
    }

    public static void main (String[] args) {
        MainConfigurationView add = new MainConfigurationView();
        add.setVisible(true);
    }
}
