package presentation.view;

import business.BuscadorManager;
import presentation.controller.BuscadorViewController;
import presentation.controller.InicioViewController;
import presentation.controller.SideMenuBarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
//package presentation.view;
//import persistance.dao.sql.SQLConnector;
//import persistance.dao.sql.SQLConnectorSong;
//import presentation.controller.ConfMusicController;
//import presentation.controller.SideBarController;

public class SideMenuBarView extends JFrame {
    public static final String GO_INICIO = "GO_INICIO";
    public static final String GO_BUSCADOR = "GO_BUSCADOR";
    public static final String GO_MISLISTAS = "GO_MISLISTAS";
    public static final String GO_SETTINGS = "GO_SETTINGS";
    public int numView;
    private SideMenuBarController sideMenuBarController;
    private JButton inicio;
    private JButton jbconfMusic;
    private JButton jbconfBuscar;
    private JButton jbconfListas;
    private JButton jbconfSettings;
    private BuscadorView buscadorView;
    private BuscadorViewController buscadorViewController;
    private InicioView inicioView;
    private InicioViewController inicioViewController;
    private PlaylistView playlistView;
    private BottomBarPanel bottomBarPanel;
    private JPanel cardPanel = new JPanel();
    private JPanel sideMenuBar = new JPanel();
    private JPanel reproductor_panel = new JPanel();
    private CardLayout c = new CardLayout();
    private final GridBagConstraints constraint = new GridBagConstraints();

    public SideMenuBarView() {
    }

    public void sideMenuBarView () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMaximumSize(new Dimension(1500, 900));
        this.setMinimumSize(new Dimension(900, 500));
        this.setTitle("Espotifai - Buscar");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        setLayout(new BorderLayout());

        Dimension dimension = getPreferredSize();
        dimension.width = 200;
        setPreferredSize(dimension);

        setLayout(new BorderLayout());
        //setLayout(new GridBagLayout());
        setBackground(new Color(191, 105, 240));

        sideMenuBarController = new SideMenuBarController(this);

        sideMenuBar = configureSideMenuBar();
        add(sideMenuBar, BorderLayout.WEST);
        c = new CardLayout();
        configureCardPanel();
        cardPanel.setBackground(Color.PINK.brighter());
        add(cardPanel, BorderLayout.CENTER);

        setSize(1500, 900);

        this.setVisible(true);
    }

    private JPanel configureSideMenuBar () {
        sideMenuBar.setBackground(new Color(191, 105, 240));
        sideMenuBar.setLayout(new GridBagLayout());
        constraint.fill = GridBagConstraints.NONE;

        ImageIcon logoSimbol = new ImageIcon("logo.png");
        Image image1 = logoSimbol.getImage();
        image1 = image1.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        logoSimbol = new ImageIcon(image1);
        JLabel logoApp = new JLabel(logoSimbol);
        logoApp.setBounds(0, 0, 100, 100);
        logoApp.setAlignmentX(Component.CENTER_ALIGNMENT);

        jbconfMusic = new JButton();
        jbconfMusic.setText("Inicio");
        jbconfMusic.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfMusic.setForeground(new Color(255, 255, 255));
        jbconfMusic.setBackground(new Color(191, 105, 240));
        jbconfMusic.setBorderPainted(false);
        jbconfMusic.setActionCommand(GO_INICIO);

        jbconfBuscar = new JButton();
        jbconfBuscar.setText("Buscar música");
        jbconfBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfBuscar.setForeground(new Color(255, 255, 255));
        jbconfBuscar.setBackground(new Color(191, 105, 240));
        jbconfBuscar.setBorderPainted(false);
        jbconfBuscar.setActionCommand(GO_BUSCADOR);
        //jbconfMusic.addActionListener(this);

        jbconfListas = new JButton();
        jbconfListas.setText("Mis Listas");
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
        sideMenuBar.add(logoApp, constraint);

        //Colocamos los botones
        constraint.gridx = 0;
        constraint.gridy = 1;
        sideMenuBar.add(groupBotones, constraint);

        registerController(sideMenuBarController);

        //reproductor_panel = reproductorBarView.ReproductorBarView();

        return sideMenuBar;
    }

    private void registerController(ActionListener listener) {
        jbconfMusic.addActionListener(listener);
        jbconfBuscar.addActionListener(listener);
        jbconfListas.addActionListener(listener);
        jbconfSettings.addActionListener(listener);
    }
    public void changeView(String name) {
        c.show(cardPanel, name);
    }

    private void configureCardPanel () {
        cardPanel.setLayout(c);
        cardPanel.setOpaque(true);

        inicioView = new InicioView();
        inicioViewController = new InicioViewController(inicioView);
        buscadorView = new BuscadorView();
        buscadorViewController = new BuscadorViewController(buscadorView, new BuscadorManager());
        buscadorView.registerController(buscadorViewController);
        playlistView = new PlaylistView();

        inicioView.iniViewController(inicioViewController);
        JPanel grin = new JPanel();
        grin.setBackground(Color.PINK);
        cardPanel.add(inicioView, "cero");
        cardPanel.add(buscadorView, "uno");
        cardPanel.add(playlistView, "dos");
        cardPanel.add(grin, "tres");

        c.first(cardPanel);

    }

}