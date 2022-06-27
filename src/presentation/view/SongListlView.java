package presentation.view;

import business.Owner;
import business.PlaylistManager;

import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.ManageAccountController;
import presentation.controller.SongListController;
import presentation.render.SongListRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Clase que se encarga de la vista de listar las Canciones
 */
public class SongListlView extends JPanel {
    private PlaylistManager manager = new PlaylistManager();
    private SongListController songListController;
    public static final String ADDSONG = "ADDSONG";
    private JScrollPane jScrollPane;
    private static Playlist selectedPlaylist;
    private JComboBox comboSongs;
    private static LinkedList<Song> allSongs = new LinkedList<>();

    public SongListlView(){
        this.songListController = new SongListController(this);
        this.setBackground(Color.red);
        setSize(1500, 900);

        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        jScrollPane = new JScrollPane();
        this.add(jScrollPane);
        //this.controller.setSongListRender(songListRender);
    }

    /*
    /**
     *Le paso el controlador del MainViewCOntroller
     * @param controller le paso el controlador
     *
    public void registerController(ActionListener controller){
        this.mainController = controller;
    }
    */

    /**
     * Metodo que configura la vista para mostrar las canciones de una playlist
     */
    public void loadSongs() {
        List<Song> sorted;
        JLabel playlistName= new JLabel();
        JPanel panel = new JPanel();
        JPanel addSongPanel= new JPanel();
        addSongPanel.setBackground(Color.white);

        comboSongs = new JComboBox();
        for(Song s: allSongs){
            comboSongs.addItem(s);
        }

        JButton addSongButton= new JButton();
        addSongButton.setText("Add song");
        addSongButton.setBackground(Color.red);
        addSongButton.setActionCommand(ADDSONG);
        addSongButton.addActionListener(songListController);
        addSongButton.putClientProperty("playlist",selectedPlaylist);


        addSongPanel.add(comboSongs);
        addSongPanel.add(addSongButton);

        playlistName.setBackground(Color.white);
        playlistName.setText(""+selectedPlaylist.getName());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        playlistName.setFont(new Font("Arial", Font.BOLD,20));
        panel.add(playlistName);
        panel.setBackground(Color.white);
        try{
            sorted = selectedPlaylist.getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
            for(Song s: sorted){
                panel.add(new SongListRender(s,selectedPlaylist, songListController));

            }
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(this,"This Playlist is empty");
        }
        if(isMyPlaylist()){
            panel.add(addSongPanel);
        }

        jScrollPane.setViewportView(panel);
    }

    /**
     * Metodo que te escoge la cancion que quieres anadir
     * @return la cancion que quieres anadir a la playlist
     */
    public Song getSelectedSongToAdd(){
        return (Song)comboSongs.getSelectedItem();
    }

    /**
     * Metodo que te muestra un mensaje de si la Playlist ya existe
     */
    public void showErrorSongAdd() {
        JPanel panel= new JPanel();
        JOptionPane.showMessageDialog(panel,"Song already in list");
    }

    /**
     * Metodo que te dice si la playlist es del usuario logeado
     * @return booleano de si es el usuario logeado
     */
    private boolean isMyPlaylist(){
        return selectedPlaylist.getOwner().equals(Owner.getUser().getName());
    }

    public static Playlist getSelectedPlaylist () {
        return SongListlView.selectedPlaylist;
    }

    public static void setSelectedPlaylist (Playlist playlist) {
        SongListlView.selectedPlaylist = playlist;
    }

    public static void setAllSongs (LinkedList<Song> songs) {
        SongListlView.allSongs = songs;
    }
}
