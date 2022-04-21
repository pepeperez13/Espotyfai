package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfigurationMenu extends JFrame{
    public ConfigurationMenu () throws IOException {
        setTitle("SPOTIFAI");
        setLocationRelativeTo(null);
        setSize(1500, 900);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos el Jpanel de la izquierda y derecha con layouts
        //f.add(createMenuPanelFront(), BorderLayout.WEST);
        //f.add(createContentPanel(), BorderLayout.EAST);
        createMenuPanelFront();
        createContentPanel();
    }

    private void createMenuPanelFront () throws IOException {
        JPanel menuPanel = new JPanel();
        JPanel subPanel = new JPanel();
        //JLabel nuestroLogo = new JLabel();
        //JLabel botonInicio = new JLabel();
        JLabel gestionarCuenta = new JLabel();
        JLabel gestionarMusica = new JLabel();
        JLabel misEstadisticas = new JLabel();
        JSeparator lineSeparador1 = new JSeparator();
        JSeparator lineSeparador2 = new JSeparator();
        JSeparator lineSeparador3 = new JSeparator();

        BufferedImage logo = ImageIO.read(new File("images/logo.png"));
        BufferedImage iconInicio = ImageIO.read(new File("images/icons8-casa-24.png"));

        JLabel nuestroLogo = new JLabel(new ImageIcon(logo.getScaledInstance(150, 150, 1)));
        JLabel botonInicio = new JLabel(new ImageIcon(iconInicio.getScaledInstance(30,30, 1)));

        menuPanel.setBackground(new Color(191, 105, 240));
        menuPanel.setBounds(0, 0, 200, 900);
        //menuPanel.setLayout(new GridLayout(3, 1, 0, 3));


        subPanel.setBackground(new Color(255, 255, 0));
        subPanel.setBounds(0, 0, 200, 200);
        //subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        //nuestroLogo.setText("NuestroLogo");
        //botonInicio.setText("Boton");
        gestionarMusica.setText("Gestionar música");
        gestionarCuenta.setText("Gestionar cuenta");
        misEstadisticas.setText("Mis estadísticas");
        lineSeparador1.setOrientation(SwingConstants.HORIZONTAL);
        //lineSeparador1.setSize(200, 1);
        lineSeparador2.setOrientation(SwingConstants.HORIZONTAL);
        //lineSeparador2.setSize(200, 1);
        lineSeparador3.setOrientation(SwingConstants.HORIZONTAL);
        //lineSeparador3.setSize(200, 1);

        subPanel.add(gestionarCuenta);
        subPanel.add(lineSeparador1);
        subPanel.add(gestionarMusica);
        subPanel.add(lineSeparador2);
        subPanel.add(misEstadisticas);
        subPanel.add(lineSeparador3);
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        menuPanel.add(nuestroLogo);
        menuPanel.add(botonInicio);
        menuPanel.add(subPanel);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        getContentPane().add(menuPanel);
    }

    private void createContentPanel () {
        JPanel content = new JPanel();
        content.setBackground(new Color(100, 100, 255));
        content.setBounds(200, 0, 1300, 900);

        getContentPane().add(content);
    }

    public static void main (String[] args) throws IOException {
        ConfigurationMenu conf = new ConfigurationMenu();
        conf.setVisible(true);
    }
}
