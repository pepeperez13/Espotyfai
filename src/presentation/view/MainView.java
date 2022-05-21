package presentation.view;

import business.BuscadorManager;
import business.entities.Song;
import presentation.controller.BuscadorViewController;
import presentation.controller.DetailedSongController;
import presentation.controller.MainViewController;
import presentation.view.detailedSong.DetailedSongView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

public class MainView extends JPanel {
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
    private CardLayout c = new CardLayout();
    private final GridBagConstraints constraint = new GridBagConstraints();

    public MainView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);
        //this.setOpaque(true);
        mainViewController = new MainViewController(this);

        sideMenuBar = configureSideMenuBar();
        add(sideMenuBar, BorderLayout.WEST);

        //MODEL
        File carpeta = new File("songs");
        File[] lista = carpeta.listFiles();
        //System.out.println("\n Hay " +  lista.length + " elementos");

        File canciones = carpeta.getAbsoluteFile();

        // Data to be displayed in the JTable
        String[][] data = {
                { "1", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "2", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "3", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "4", "As it was", "Harry Styles", "pop-rock", "3,35" },

        };

        //Prueba ArrayList Songs
        LinkedList list_songs = new LinkedList<Song>();
        Song song1 = new Song("As it was", "Pop", "AsItWas", "Harry Styles", "path", "Aleserra");
        Song song2 = new Song("Bam Bam", "Pop-Rock", "BAMBAM", "Camila Cabello", "path", "Abraham");
        Song song3 = new Song("Heat Waves", "Electro-Pop", "HeatWaves", "Glass Animal", "path","Borja");
        Song song4 = new Song("Pantisyto", "Reggeaton", "ReggeaAlbum", "Feid", "path","Pepe");
        Song song5 = new Song("Cayo la noche", "Reggeaton - Trap", "Cayo la night", "path","Quevedo, Bad Bunny", "Lachner");

        list_songs.add(song1);
        list_songs.add(song2);
        list_songs.add(song3);
        list_songs.add(song4);
        list_songs.add(song5);

        //String[][] table_list_of_songs = list_songs.toArray();


        //Columnas
        String[] columnNames = { "Title", "Genre", "Album", "Artist", "Owner" };

        // Initializing the JTable
        songs_table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(songs_table);
        //songs_table.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(songs_table);

        //PANELES
        JPanel home_panel = new JPanel();
        JPanel songs_list = new JPanel(new BorderLayout());

        //BORDES HOME
        Border borde_home_panel = new TitledBorder(new EtchedBorder(), "HOME ");
        home_panel.setBorder(borde_home_panel);

        Border borde_lista_canciones_home = new TitledBorder(new EtchedBorder(), "CANCIONES: ");
        songs_list.setBorder(borde_lista_canciones_home);

        songs_list.add(sp, BorderLayout.CENTER);
        songs_list.add(songs_table.getTableHeader(), BorderLayout.NORTH);
        songs_list.add(songs_table, BorderLayout.CENTER);

        home_panel.add(songs_list, BorderLayout.CENTER);

        this.add(home_panel, BorderLayout.CENTER);

        //this.setVisible(true);
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
        buscadorViewController = new BuscadorViewController(buscadorView, new BuscadorManager());
        buscadorView.registerController(buscadorViewController);
        playlistView = new PlaylistView();
        configAccountView = new ConfigAccountView();
        detailedSongView = new DetailedSongView();
        detailedSongController = new DetailedSongController(detailedSongView);

        cardPanel.add(this, "1");
        cardPanel.add(buscadorView, "2");
        cardPanel.add(playlistView, "3");
        cardPanel.add(configAccountView, "4");
        cardPanel.add(detailedSongView, "5");

        c.first(cardPanel);

    }

}
