package presentation.view;

import presentation.controller.ConfigMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Menú lateral que contiene las siguientes opciones:
 * -INICIO
 * -CONFIGURACIÓN DE LA MÚSICA
 * -CONFIGURACIÓN DEL USUARIO
 * -ESTADÍSTICAS
 */
public class ConfigMenuView extends JPanel {
    public static final String GO_INICIO = "GO_INICIO";
    public static final String GO_CONFIG_MUSIC = "GO_CONFIG_MUSIC";
    public static final String GO_CONFIG_USER = "GO_CONFIG_USER";
    public static final String GO_STATICS = "GO_STATICS";
    private JButton jbInicio;
    private JButton jbconfMusic;
    private JButton jbconfUsuario;
    private JButton jbconfEstadisticas;
    private ConfigMenuController configMenuController;
    private final GridBagConstraints constraint = new GridBagConstraints();

    /**
     *Constructor en el que se describe la estructura del menú
     * <<Se inicializa el controller de la vista para la interacción con los botones>>
     * @param mainManagerView
     */
    public ConfigMenuView(MainManagerView mainManagerView) {

        configMenuController = new ConfigMenuController(mainManagerView);

        setBackground(new Color(191, 105, 240));
        setLayout(new GridBagLayout());
        constraint.fill = GridBagConstraints.NONE;

        ImageIcon logoSimbol = new ImageIcon("Images/logo.png");
        Image image1 = logoSimbol.getImage();
        image1 = image1.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        logoSimbol = new ImageIcon(image1);
        JLabel logoApp = new JLabel(logoSimbol);
        logoApp.setBounds(0, 0, 100, 100);
        logoApp.setAlignmentX(Component.CENTER_ALIGNMENT);

        jbInicio = new JButton();
        jbInicio.setText("Inicio");
        jbInicio.setFont(new Font("Arial", Font.BOLD, 18));
        jbInicio.setForeground(new Color(255, 255, 255));
        jbInicio.setBackground(new Color(191, 105, 240));
        jbInicio.setBorderPainted(false);
        jbInicio.setActionCommand(GO_INICIO);

        jbconfMusic = new JButton();
        jbconfMusic.setText("Gestionar música");
        jbconfMusic.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfMusic.setForeground(new Color(255, 255, 255));
        jbconfMusic.setBackground(new Color(191, 105, 240));
        jbconfMusic.setBorderPainted(false);
        jbconfMusic.setActionCommand(GO_CONFIG_MUSIC);
        //jbconfMusic.addActionListener(this);

        jbconfUsuario = new JButton();
        jbconfUsuario.setText("Gestionar cuenta");
        jbconfUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfUsuario.setForeground(new Color(255, 255, 255));
        jbconfUsuario.setBackground(new Color(191, 105, 240));
        jbconfUsuario.setBorderPainted(false);
        jbconfUsuario.setActionCommand(GO_CONFIG_USER);
        //jbconfUsuario.addActionListener(this);

        jbconfEstadisticas = new JButton();
        jbconfEstadisticas.setText("Mis estadísticas");
        jbconfEstadisticas.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfEstadisticas.setForeground(new Color(255, 255, 255));
        jbconfEstadisticas.setBackground(new Color(191, 105, 240));
        jbconfEstadisticas.setBorderPainted(false);
        jbconfEstadisticas.setActionCommand(GO_STATICS);
        //jbconfEstadisticas.addActionListener(this);

        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);

        JPanel groupBotones = new JPanel();
        groupBotones.setBackground(new Color(191, 105,240));
        groupBotones.setLayout(new BoxLayout(groupBotones, BoxLayout.Y_AXIS));

        groupBotones.add(jbconfMusic);
        groupBotones.add(separator1);
        groupBotones.add(jbconfUsuario);
        groupBotones.add(separator2);
        groupBotones.add(jbconfEstadisticas);
        groupBotones.add(separator3);

        //Colocamos el Icono de la app
        constraint.gridx = 0;
        constraint.gridy = 0;
        add(logoApp, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        add(jbInicio, constraint);
        //Colocamos los botones
        constraint.gridx = 0;
        constraint.gridy = 2;
        add(groupBotones, constraint);

        registerController(configMenuController);
    }

    /**
     * Método que conecta la vista con el controlador [View->Controller]
     */
    private void registerController(ActionListener listener) {
        jbInicio.addActionListener(listener);
        jbconfUsuario.addActionListener(listener);
        jbconfMusic.addActionListener(listener);
        jbconfEstadisticas.addActionListener(listener);
    }
}
