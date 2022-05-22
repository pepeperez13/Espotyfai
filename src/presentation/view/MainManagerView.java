package presentation.view;

import business.BuscadorManager;
import business.entities.Song;
import presentation.controller.BuscadorViewController;
import presentation.controller.DetailedSongController;
import presentation.controller.MainViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainManagerView extends JPanel {
    JTable songs_table;
    Song song;

    public static final String GO_INICIO = "GO_INICIO";
    public static final String GO_BUSCADOR = "GO_BUSCADOR";
    public static final String GO_MISLISTAS = "GO_MISLISTAS";
    public static final String GO_SETTINGS = "GO_SETTINGS";
    public int numView;
    private JButton jbconfMusic;
    private JButton jbconfBuscar;
    private JButton jbconfListas;
    private JButton jbconfSettings;
    private BuscadorView buscadorView;
    private BuscadorViewController buscadorViewController;
    private MainViewController mainViewController;
    private ConfigAccountView configAccountView;
    private PlaylistView playlistView;
    private DetailedSongView detailedSongView;
    private DetailedSongController detailedSongController;
    private JPanel cardPanel = new JPanel();
    private JPanel sideMenuBar = new JPanel();
    private final CardLayout c = new CardLayout();
    private final GridBagConstraints constraint = new GridBagConstraints();
    private MainView mainView;

    public MainManagerView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);
        //this.setOpaque(true);
        mainViewController = new MainViewController(this);

        sideMenuBar = configureSideMenuBar();
        add(sideMenuBar, BorderLayout.WEST);
        add(cardPanel,  BorderLayout.CENTER);

        detailedSongView = new DetailedSongView();
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

        registerController(mainViewController);

        //reproductor_panel = reproductorBarView.ReproductorBarView();

        return sideMenuBar;
    }

    private void registerController(ActionListener listener) {
        jbconfMusic.addActionListener(listener);
        jbconfBuscar.addActionListener(listener);
        jbconfListas.addActionListener(listener);
        jbconfSettings.addActionListener(listener);
    }
    public void changeView(int num) {
        numView = num;
        configureCardPanel();
        c.show(cardPanel, String.valueOf(numView));
    }

    private void configureCardPanel () {
        cardPanel.setLayout(c);
        //cardPanel.setOpaque(true);

        mainViewController = new MainViewController(this);
        buscadorView = new BuscadorView();
        playlistView = new PlaylistView();
        configAccountView = new ConfigAccountView();

        detailedSongController = new DetailedSongController(detailedSongView);
        buscadorViewController = new BuscadorViewController(buscadorView, new BuscadorManager(), detailedSongView, this);
        buscadorView.registerController(buscadorViewController);
        mainView= new MainView();

        cardPanel.add(mainView, "1");
        cardPanel.add(buscadorView, "2");
        cardPanel.add(playlistView, "3");
        cardPanel.add(configAccountView, "4");
        cardPanel.add(detailedSongView, "5");

        c.first(cardPanel);

    }

}
