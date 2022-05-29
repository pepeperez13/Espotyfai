package presentation.view;

import business.PlaylistManager;
import business.SongPlaylistManager;
import business.Owner;
import business.entities.Playlist;

import presentation.controller.PlayListController;
import presentation.render.PlayListRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que se encarga de la vista de listar las Playlist
 */
public class PlaylistView extends JPanel {
    public static final String CREAR_PLAYLIST = "CREAR_PLAYLIST";
    private PlayListRender playListRender;
    private ActionListener playListController;
    private PlaylistManager manager = new PlaylistManager();
    private SongPlaylistManager songPlaylistManager= new SongPlaylistManager();
    private ActionListener controller;
    private JScrollPane jScrollPane;
    JButton crearPlaylist = new JButton();

    /**
     * Constructor de la clase
     */
    public PlaylistView() {
        this.playListController = new PlayListController(this);
        this.setBackground(Color.red);
        setSize(1500, 900);
        //setResizable(true);
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        jScrollPane= new JScrollPane();
        this.add(jScrollPane);
    }

    /**
     * Metodo que te hace introducir el nombre de una Playlist para crearla
     * @return devuelve el nombre de la Playlist creada
     */
    public static String crearPlaylist() {
            JPanel panel= new JPanel();
            JButton createPlaylist= new JButton("Create Playlist");
            panel.add(createPlaylist);
        String name =  JOptionPane.showInputDialog( panel, "Enter the name of the new Playlist", JOptionPane.PLAIN_MESSAGE);
        return name;
    }

    /**
     * Metodo que te muestra un mensaje de si la Playlist ya existe
     */
    public void showErrorPlaylistCreation() {
        JPanel panel= new JPanel();
        JOptionPane.showMessageDialog(panel,"Playlist already exists");
    }

    /**
     *Le paso el controlador del MainViewController
     * @param controller le paso el controlador
     */
    public void registerController(ActionListener controller){
        this.controller = controller;
    }

    /**
     * Metodo que configura la vista para mostrar las Playlist del User y todas las Playlist
     * @param playlists todass las playlist que existen
     * @param playlistsOfUser playlist del usuario
     */
    public void bringPlaylists(LinkedList<Playlist> playlists,LinkedList<Playlist> playlistsOfUser) {



        GridBagLayout gridLayout= new GridBagLayout();
        JSeparator separatorvertical= new JSeparator();
        GridBagConstraints c = new GridBagConstraints();
        separatorvertical.setOrientation(SwingConstants.VERTICAL);
        separatorvertical.setBackground(Color.black);
        JPanel panel=new JPanel();
        JLabel jlabelizq= new JLabel();
        JLabel jlabelder =new JLabel();
        jlabelizq.setText("My Playlists");
        jlabelder.setText("All Playlists");
        jlabelder.setAlignmentX(SwingConstants.CENTER);
        jlabelizq.setAlignmentX(SwingConstants.CENTER);
        jlabelizq.setFont(new Font("Arial", Font.BOLD,20));
        jlabelder.setFont(new Font("Arial", Font.BOLD,20));
        panel.setLayout(gridLayout);
        JPanel panelizq = new JPanel();
        JPanel panelder = new JPanel();
        panelizq.setBackground(Color.cyan);
        panelder.setBackground(Color.cyan);
        panelizq.setLayout(new BoxLayout(panelizq,BoxLayout.Y_AXIS));
        panelder.setLayout(new BoxLayout(panelder,BoxLayout.Y_AXIS));
        crearPlaylist.setText("Crear Playlist");
        crearPlaylist.setActionCommand(CREAR_PLAYLIST);
        crearPlaylist.addActionListener(playListController);
        panelizq.add(jlabelizq);
        panelder.add(jlabelder);
        for(Playlist pu: playlistsOfUser){
            panelizq.add(new PlayListRender(pu,controller,this.playListController));
        }
        for(Playlist pa: playlists){
            panelder.add(new PlayListRender(pa,controller,this.playListController));
        }
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 1.0;
        c.weightx=1.0;
        panel.add(panelizq,c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 40;
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1.0;
        c.weightx=1.0;
        panel.add(panelder,c);
        panel.add(crearPlaylist);
        panel.setBackground(Color.white);
        this.jScrollPane.setViewportView(panel);
    }


}
