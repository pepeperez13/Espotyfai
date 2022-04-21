package presentation.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SideMenuPanel extends JPanel {
    public static final String INICIO = "INICIO";
    public static final String GESTION_MUSICA = "GESTION_MUSICA";
    public static final String GESTION_CUENTA = "GESTION";
    public static final String ESTADISTICAS = "ESTADISTICAS";

    /** The constraints for the layout. */
    private GridBagConstraints constraint;


    public SideMenuPanel() throws IOException {

        setLayout(new GridBagLayout());

        this.setBackground(new Color(191, 105, 240));

        constraint = new GridBagConstraints();

        constraint.anchor = GridBagConstraints.NORTH;
        constraint.weighty = 1;

        createMenuPanelFront();
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


            //subPanel.setBackground(new Color(191, 105, 240));
            //subPanel.setBounds(0, 0, 200, 200);
            //subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

            //nuestroLogo.setText("NuestroLogo");
            //botonInicio.setText("Boton");
            gestionarMusica.setText("Gestionar música");
            //gestionarMusica.addMouseListener();
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

            this.add(menuPanel, constraint);
        }

        public void goHome () {
            //Actualizar menu frontal
            //Actualizar CENTER
            JPanel l = new JPanel();
            this.add(l);
        }

        public void goConfMusic () {

        }

        public void goConfCuenta () {

        }

        public void goEstadisticas () {

        }

    }
