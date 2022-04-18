package presentation;

import javax.swing.*;
import java.awt.*;

public class ConfigurationMenu {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public  ConfigurationMenu () {
        JFrame f = new JFrame("SPOTIFAI");

        //Configuración del frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        f.setSize(screenSize.width, screenSize.height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos el Jpanel de la izquierda y derecha con layouts
        //f.add(createMenuPanelFront(), BorderLayout.WEST);
        //f.add(createContentPanel(), BorderLayout.EAST);
        createMenuPanelFront();
        createContentPanel();

        //pack();
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
        menuPanel.setBounds(0, 0, 200, screenSize.height);
        menuPanel.setLayout(new GridLayout(3, 1, 0, 3));

        subPanel.setBackground(new Color(191, 105, 240));
        subPanel.setBounds(0, 0, 200, 400);
        subPanel.setLayout(new GridLayout(6, 1, 0, 3));

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

        //getContentPane().add(menuPanel);
    }

    public void createContentPanel () {
        JPanel content = new JPanel();
        content.setBackground(new Color(255, 255, 255));
        content.setBounds(0, 0, screenSize.width, screenSize.height);

        //getContentPane().add(content);
    }
}
