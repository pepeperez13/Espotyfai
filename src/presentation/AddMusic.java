package presentation;

import presentation.view.MainView;

import javax.swing.*;

public class AddMusic extends JFrame {
    MainView plantilla = new MainView();
    BarReproduccion barraReproduccion = new BarReproduccion();

    public AddMusic () {
        //plantilla.add(barraReproduccion);
    }

    public static void main (String[] args) {
        MainView add = new MainView();
        add.setVisible(true);
    }
}
