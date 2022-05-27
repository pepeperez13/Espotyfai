package presentation.view;

import business.PlaylistManager;
import business.Store;
import business.entities.Playlist;
import business.entities.Song;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SongListlView extends JPanel {
    private PlaylistManager manager = new PlaylistManager();
    private SongListRender songListRender;
    private ActionListener controller;
    private JScrollPane jScrollPane;
    public  static Playlist selectedPlaylist;
    public SongListlView(){
        this.setBackground(Color.red);
        setSize(1500, 900);

        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        jScrollPane = new JScrollPane();
        this.add(jScrollPane);
        //this.controller.setSongListRender(songListRender);
    }

    public void registerController(ActionListener controller){
        this.controller = controller;
    }


    public void loadSongs() {
        List<Song> sorted;
        JLabel playlistName= new JLabel();
        JPanel panel = new JPanel();
        playlistName.setBackground(Color.white);
        playlistName.setText(""+selectedPlaylist.getName());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        playlistName.setFont(new Font("Arial", Font.BOLD,20));
        panel.add(playlistName);
        panel.setBackground(Color.white);
        try{
            sorted = selectedPlaylist.getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
            for(Song s: sorted){
                panel.add(new SongListRender(s, controller));
            }
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(this,"This Playlist is empty");
        }

        jScrollPane.setViewportView(panel);
    }
}
