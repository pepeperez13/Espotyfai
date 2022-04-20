package presentation;

import javax.swing.*;
import java.awt.*;

public class ConfigurationMenu extends JFrame{
    public ConfigurationMenu () {
        JFrame f = new JFrame("SPOTIFAI");
        //Configuración del frame

        f.setLocationRelativeTo(null);
        f.setSize(1500, 850);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos el Jpanel de la izquierda y derecha con layouts
        //f.add(createMenuPanelFront(), BorderLayout.WEST);
        //f.add(createContentPanel(), BorderLayout.EAST);
        createMenuPanelFront();
        createContentPanel();

        pack();
    }

    public void createMenuPanelFront () {
        JPanel menuPanel = new JPanel();
        JPanel subPanel = new JPanel();
        JLabel nuestroLogo = new JLabel();
        JLabel botonInicio = new JLabel();
        JLabel gestionarCuenta = new JLabel();
        JLabel gestionarMusica = new JLabel();
        JLabel misEstadisticas = new JLabel();
        JSeparator lineSeparador1 = new JSeparator();
        JSeparator lineSeparador2 = new JSeparator();
        JSeparator lineSeparador3 = new JSeparator();

        menuPanel.setBackground(new Color(191, 105, 240));
        menuPanel.setBounds(0, 0, 200, 900);
        //menuPanel.setLayout(new GridLayout(3, 1, 0, 3));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        subPanel.setBackground(new Color(191, 105, 240));
        subPanel.setBounds(0, 0, 200, 400);
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        nuestroLogo.setText("NuestroLogo");
        botonInicio.setText("Boton");
        gestionarMusica.setText("Gestionar música");
        gestionarCuenta.setText("Gestionar cuenta");
        misEstadisticas.setText("Mis estadísticas");
        lineSeparador1.setOrientation(SwingConstants.HORIZONTAL);
        lineSeparador1.setSize(200, 1);
        lineSeparador2.setOrientation(SwingConstants.HORIZONTAL);
        lineSeparador2.setSize(200, 1);
        lineSeparador3.setOrientation(SwingConstants.HORIZONTAL);
        lineSeparador3.setSize(200, 1);

        subPanel.add(gestionarCuenta);
        subPanel.add(lineSeparador1);
        subPanel.add(gestionarMusica);
        subPanel.add(lineSeparador2);
        subPanel.add(misEstadisticas);
        subPanel.add(lineSeparador3);

        menuPanel.add(nuestroLogo);
        menuPanel.add(botonInicio);
        menuPanel.add(subPanel);

        getContentPane().add(menuPanel);
    }

    public void createContentPanel () {
        JPanel content = new JPanel();
        content.setBackground(new Color(255, 255, 255));
        content.setBounds(0, 0, 1500, 900);

        getContentPane().add(content);
    }

    public static void main (String[] args) {
        ConfigurationMenu conf = new ConfigurationMenu();
        conf.setVisible(true);
    }
}
