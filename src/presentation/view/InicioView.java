package presentation.view;

import presentation.controller.InicioController;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JPanel {
    public static final String GO_CONFIG = "GO_CONFIG";
    public static final String GO_INICIO = "GO_INICIO";
    public static final String GO_SEARCH = "GO_SEARCH";
    public static final String GO_LIST = "GO_LIST";
    private InicioController inicioController;
    private JButton go_config;
    private JButton go_inicio;
    private JButton go_search;
    private JButton go_list;
    InicioView(InitView initView) {
        inicioController = new InicioController(this, initView);

        this.setLayout(new BorderLayout());
        add(configureMenuFrontal (), BorderLayout.WEST);
        //this.setBackground(Color.white);

        //MODEL
        //File carpeta = new File("songs");
        //File[] lista = carpeta.listFiles();
        /*System.out.println("\n Hay " +  lista.length + " elementos");
        //private JButton play_pause, back_song, next_song, full_screen;
        int filas = lista.length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {

            }
        }
        File canciones = carpeta.getAbsoluteFile();*/

        //JTABLE

        // Data to be displayed in the JTable
        String[][] data = {
                { "1", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "2", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "3", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "4", "As it was", "Harry Styles", "pop-rock", "3,35" },

        };

        //Columnas
        String[] columnNames = { "List_number", "Name", "Artist", "Genre", "Duration" };

        // Initializing the JTable
        JTable songs_table = new JTable(data, columnNames);
        songs_table.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(songs_table);

        //PANELES
        //JPanel side_menu = new JPanel(new GridLayout(6, 1));
        JPanel home_title = new JPanel(new GridLayout(1,3));
        JPanel songs_list = new JPanel(new GridLayout(1, 1));
        JPanel song_player = new JPanel();

        //COLORES DE FONDO
        //side_menu.setBackground(Color.MAGENTA.darker());

        //SONG PLAYER
        song_player.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel song_info_label = new JLabel("nombre de la cancion - artista");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        song_player.add(song_info_label, c);

        JButton back_song = new JButton("<<");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        song_player.add(back_song, c);

        JButton play_stop = new JButton("|| / >");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        song_player.add(play_stop, c);

        JButton next_song = new JButton(">>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        song_player.add(next_song, c);

        JButton full_screen = new JButton("Full Screen");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;       //aligned with button 2
        c.gridy = 2;       //third row
        song_player.add(full_screen, c);

        this.add(song_player, BorderLayout.SOUTH);

        //COMPONENTES
        /*JLabel title = new JLabel("Espotify");
        title.setFont(new Font("Arial", Font.BOLD,25));

        JLabel home = new JLabel("HOME");
        home.setFont(new Font("Arial", Font.BOLD,25));

        JLabel nulo = new JLabel(" ");

        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("logop.png"));

        JLabel option_inicio = new JLabel("Inicio");
        option_inicio.setFont(new Font("Arial", Font.BOLD,15));

        JLabel option_buscar = new JLabel("Buscar");
        option_buscar.setFont(new Font("Arial", Font.BOLD,15));

        JLabel option_listas = new JLabel("Mis Listas");
        option_listas.setFont(new Font("Arial", Font.BOLD,15));

        //JLabel option_ajustes = new JLabel("Settings");
        go_config = new JButton("Settings");
        go_config.setActionCommand(GO_CONFIG);
        go_config.addActionListener(inicioController);
        option_listas.setFont(new Font("Arial", Font.BOLD,15));*/


        //ADICIONES
        /*side_menu.add(title, BorderLayout.CENTER);
        side_menu.add(imagen, BorderLayout.CENTER);
        side_menu.add(option_inicio, BorderLayout.CENTER);
        side_menu.add(option_buscar, BorderLayout.CENTER);
        side_menu.add(option_listas, BorderLayout.CENTER);
        //side_menu.add(option_ajustes, BorderLayout.CENTER);
        side_menu.add(go_config, BorderLayout.CENTER);*/

        /*home_title.add(nulo, BorderLayout.CENTER);
        home_title.add(home);
        home_title.add(nulo, BorderLayout.CENTER);*/

        songs_list.add(songs_table.getTableHeader(), BorderLayout.NORTH);
        songs_list.add(songs_table, BorderLayout.CENTER);

        //this.add(side_menu, BorderLayout.WEST);
        this.add(home_title, BorderLayout.NORTH);
        this.add(songs_list, BorderLayout.EAST);
        //this.add(nulo, BorderLayout.EAST);
        this.add(sp);


        //VISIBILIDAD
        //this.setVisible(true);
    }

    private JPanel configureMenuFrontal () {
        GridBagConstraints constraint = new GridBagConstraints();
        JPanel menuFrontal = new JPanel();

        menuFrontal.setBackground(new Color(191, 105, 240));
        menuFrontal.setLayout(new GridBagLayout());
        constraint.fill = GridBagConstraints.NONE;

        ImageIcon logoSimbol = new ImageIcon("Images/logo.png");
        Image image1 = logoSimbol.getImage();
        image1 = image1.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        logoSimbol = new ImageIcon(image1);
        JLabel logoApp = new JLabel(logoSimbol);
        logoApp.setBounds(0, 0, 100, 100);
        logoApp.setAlignmentX(Component.CENTER_ALIGNMENT);

        go_inicio = new JButton();
        go_inicio.setText("INICIO");
        go_inicio.setFont(new Font("Arial", Font.BOLD, 18));
        go_inicio.setForeground(new Color(255, 255, 255));
        go_inicio.setBackground(new Color(191, 105, 240));
        go_inicio.setBorderPainted(false);
        go_inicio.setActionCommand(GO_INICIO);
        go_inicio.addActionListener(inicioController);
        //jbconfMusic.addActionListener(this);

        go_search = new JButton();
        go_search.setText("BUSCAR");
        go_search.setFont(new Font("Arial", Font.BOLD, 18));
        go_search.setForeground(new Color(255, 255, 255));
        go_search.setBackground(new Color(191, 105, 240));
        go_search.setBorderPainted(false);
        go_search.setActionCommand(GO_SEARCH);
        go_search.addActionListener(inicioController);
        //jbconfUsuario.addActionListener(this);

        go_list = new JButton();
        go_list.setText("MIS LISTAS");
        go_list.setFont(new Font("Arial", Font.BOLD, 18));
        go_list.setForeground(new Color(255, 255, 255));
        go_list.setBackground(new Color(191, 105, 240));
        go_list.setBorderPainted(false);
        go_list.setActionCommand(GO_LIST);
        go_list.addActionListener(inicioController);
        //jbconfEstadisticas.addActionListener(this);

        go_config = new JButton();
        go_config.setText("CONFIGURACIÓN");
        go_config.setFont(new Font("Arial", Font.BOLD, 18));
        go_config.setForeground(new Color(255, 255, 255));
        go_config.setBackground(new Color(191, 105, 240));
        go_config.setBorderPainted(false);
        go_config.setActionCommand(GO_CONFIG);
        go_config.addActionListener(inicioController);
        //jbconfEstadisticas.addActionListener(this);

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

        groupBotones.add(go_inicio);
        groupBotones.add(separator1);
        groupBotones.add(go_search);
        groupBotones.add(separator2);
        groupBotones.add(go_list);
        groupBotones.add(separator3);
        groupBotones.add(go_config);
        groupBotones.add(separator4);

        //Colocamos el Icono de la app
        constraint.gridx = 0;
        constraint.gridy = 0;
        menuFrontal.add(logoApp, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        menuFrontal.add(groupBotones, constraint);

        return menuFrontal;
    }


}
