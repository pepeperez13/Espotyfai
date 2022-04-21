package presentation.view;

import presentation.JImagePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SideMenuPanel extends JPanel {


    /** The constraints for the layout. */
    private GridBagConstraints constraint;


    public SideMenuPanel() {

        setLayout(new GridBagLayout());

        this.setBackground(new Color(40, 40, 40));

        constraint = new GridBagConstraints();

        constraint.anchor = GridBagConstraints.NORTH;
        constraint.weighty = 1;


        ImageIcon logoImage = new ImageIcon("images/spotiLogo.png");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);
        this.add(logoImageLabel, constraint);

        createMenuPanelFront();
        }

        private void createMenuPanelFront () {

            JPanel menuPanel = new JPanel();
            //JPanel subPanel = new JPanel();
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

            //subPanel.setBackground(new Color(191, 105, 240));
            //subPanel.setBounds(0, 0, 200, 200);
            //subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

            nuestroLogo.setText("NuestroLogo");
            botonInicio.setText("Boton");
            gestionarMusica.setText("Gestionar música");
            gestionarCuenta.setText("Gestionar cuenta");
            misEstadisticas.setText("Mis estadísticas");
            lineSeparador1.setOrientation(SwingConstants.HORIZONTAL);
            //lineSeparador1.setSize(200, 1);
            lineSeparador2.setOrientation(SwingConstants.HORIZONTAL);
            //lineSeparador2.setSize(200, 1);
            lineSeparador3.setOrientation(SwingConstants.HORIZONTAL);
            //lineSeparador3.setSize(200, 1);

            menuPanel.add(nuestroLogo);
            menuPanel.add(botonInicio);
            //menuPanel.add(subPanel);
            menuPanel.add(gestionarCuenta);
            menuPanel.add(lineSeparador1);
            menuPanel.add(gestionarMusica);
            menuPanel.add(lineSeparador2);
            menuPanel.add(misEstadisticas);
            menuPanel.add(lineSeparador3);

            this.add(menuPanel, constraint);
        }



    }
