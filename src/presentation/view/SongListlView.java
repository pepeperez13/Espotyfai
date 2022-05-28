package presentation.view;

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
 *Vista de listar las Canciones
 */
public class SongListlView extends JPanel {
    private PlaylistManager manager = new PlaylistManager();
    private SongListRender songListRender;
    private ActionListener mainController;
    private ActionListener songListController;
    public static final String ADDSONG = "ADDSONG";
    public static final String ADDSONGPANE = "ADDSONGPANE";
    private JScrollPane jScrollPane;
    public  static Playlist selectedPlaylist;

    public SongListlView(){
        this.songListController = new SongListController();
        this.setBackground(Color.red);
        setSize(1500, 900);

        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        jScrollPane = new JScrollPane();
        this.add(jScrollPane);
        //this.controller.setSongListRender(songListRender);
    }

    /**
     *
     * @param controller
     */
    public void registerController(ActionListener controller){
        this.mainController = controller;
    }

    /**
     * Metodo que configura la vista para mostrar las canciones de una playlist
     */
    public void loadSongs() {
        List<Song> sorted;
        JLabel playlistName= new JLabel();
        JPanel panel = new JPanel();
        JPanel addSong= new JPanel();
        addSong.setBackground(Color.white);
        JButton addSongButton= new JButton();
        addSongButton.setText("Add song");
        addSongButton.setBackground(Color.red);
        addSongButton.setActionCommand(ADDSONG);
        addSongButton.addActionListener(songListController);
        addSong.add(addSongButton);
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
        panel.add(addSong);
        jScrollPane.setViewportView(panel);
    }
    public  static String showMessageAddSong(LinkedList<Song> songs) {
        JPanel songPanel = new JPanel();
        //showPlaylistsController = new ShowPlaylistsController(this);

        Box box= Box.createVerticalBox();


       /* for (Song song : songs) {
            JButton button = new JButton(song.getTitle());
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);
            button.setActionCommand(ADDSONGPANE);
            button.addActionListener(controller);
            box.add(button);
        }*/

       /* JScrollPane scroll= new JScrollPane(box);
        songPanel.add(scroll);
        JOptionPane.showMessageDialog(this, songPanel, "Select a song you want to add to the playlist", JOptionPane.PLAIN_MESSAGE);*/
        return null;
    }
}
