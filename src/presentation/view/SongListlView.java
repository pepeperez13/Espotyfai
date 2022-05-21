package presentation.view;

import business.PlaylistManager;
import business.Store;
import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.ConfigPlaylistController;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SongListlView extends JFrame {
    private PlaylistManager manager = new PlaylistManager();
    private SongListRender songListRender;
    private ConfigPlaylistController controller= new ConfigPlaylistController();
    private JScrollPane jScrollPane;
    public SongListlView(){
        this.setBackground(Color.red);
        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        jScrollPane = new JScrollPane(bringPlaylists());
        this.add(jScrollPane);
        this.controller.setSongListRender(songListRender);
    }
    private JPanel bringPlaylists() {
        //if(Store.LOGGED_USER == null) return new JList<>() ;

        // create List model
        ArrayList<Song> model = new ArrayList<>();
        // add item to model
        model.add(new Song("Song 1","gen1","","Artist1","","test@test.com"));
        model.add(new Song("Song 2","gen1","","Artist2","","test@test.com"));


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(Song s: model){
            panel.add(new SongListRender(s, controller));
        }

        return panel;
    }

    public void loadSongs(List<Song> songs) {
        List<Song> sorted = songs.stream().sorted(Comparator.comparing(Song::getPosicion)) .collect(Collectors.toList());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(Song s: sorted){
            panel.add(new SongListRender(s, controller));
        }

        jScrollPane.setViewportView(panel);
    }
}
