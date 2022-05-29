package presentation.view;

import presentation.controller.MainViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Menú lateral que contiene las siguientes opciones:
 * -INICIO
 * -BUSCADOR CANCIÓN
 * -LISTAS DE REPRODUCCIÓN
 * -CONFIGURACIÓN
 */
public class MainMenu extends JPanel {
    public static final String GO_INICIO = "GO_INICIO";
    public static final String GO_BUSCADOR = "GO_BUSCADOR";
    public static final String GO_MISLISTAS = "GO_MISLISTAS";
    public static final String GO_SETTINGS = "GO_SETTINGS";
    private JButton jbconfMusic;
    private JButton jbconfBuscar;
    private JButton jbconfListas;
    private JButton jbconfSettings;
    private GridBagConstraints constraint = new GridBagConstraints();
    private MainViewController mainViewController;

    /**
     *Constructor en el que se describe la estructura del menú
     * <<Se inicializa el controller de la vista para la interacción con los botones>>
     * @param mainManagerView
     */
    public MainMenu (MainManagerView mainManagerView) {
        mainViewController = new MainViewController(this, mainManagerView);

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

        jbconfMusic = new JButton();
        jbconfMusic.setText("Home");
        jbconfMusic.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfMusic.setForeground(new Color(255, 255, 255));
        jbconfMusic.setBackground(new Color(191, 105, 240));
        jbconfMusic.setBorderPainted(false);
        jbconfMusic.setActionCommand(GO_INICIO);

        jbconfBuscar = new JButton();
        jbconfBuscar.setText("Search music");
        jbconfBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfBuscar.setForeground(new Color(255, 255, 255));
        jbconfBuscar.setBackground(new Color(191, 105, 240));
        jbconfBuscar.setBorderPainted(false);
        jbconfBuscar.setActionCommand(GO_BUSCADOR);
        //jbconfMusic.addActionListener(this);

        jbconfListas = new JButton();
        jbconfListas.setText("PlayLists");
        jbconfListas.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfListas.setForeground(new Color(255, 255, 255));
        jbconfListas.setBackground(new Color(191, 105, 240));
        jbconfListas.setBorderPainted(false);
        jbconfListas.setActionCommand(GO_MISLISTAS);
        //jbconfUsuario.addActionListener(this);

        jbconfSettings = new JButton();
        jbconfSettings.setText("Settings");
        jbconfSettings.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfSettings.setForeground(new Color(255, 255, 255));
        jbconfSettings.setBackground(new Color(191, 105, 240));
        jbconfSettings.setBorderPainted(false);
        jbconfSettings.setActionCommand(GO_SETTINGS);
        //jbconfUsuario.addActionListener(this);


        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator4 = new JSeparator();
        separator4.setOrientation(SwingConstants.HORIZONTAL);

        JPanel groupBotones = new JPanel();
        groupBotones.setBackground(new Color(191, 105,240));
        groupBotones.setLayout(new BoxLayout(groupBotones, BoxLayout.Y_AXIS));

        groupBotones.add(jbconfMusic);
        groupBotones.add(separator1);
        groupBotones.add(jbconfBuscar);
        groupBotones.add(separator2);
        groupBotones.add(jbconfListas);
        groupBotones.add(separator3);
        groupBotones.add(jbconfSettings);
        groupBotones.add(separator4);

        //Colocamos el Icono de la app
        constraint.gridx = 0;
        constraint.gridy = 0;
        add(logoApp, constraint);

        //Colocamos los botones
        constraint.gridx = 0;
        constraint.gridy = 1;
        add(groupBotones, constraint);

        registerController(mainViewController);

    }

    /**
     * Método que conecta la vista con el controlador [View->Controller]
     */
    private void registerController(ActionListener listener) {
        jbconfMusic.addActionListener(listener);
        jbconfBuscar.addActionListener(listener);
        jbconfListas.addActionListener(listener);
        jbconfSettings.addActionListener(listener);
    }

}
