package presentation.view;

import business.PlaylistManager;
import business.Store;
import business.entities.Playlist;

import presentation.controller.MainViewController;
import presentation.render.PlayListRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlaylistView extends JPanel {
    private PlayListRender playListRender;
    private PlaylistManager manager = new PlaylistManager();
    private ActionListener controller;
    private JScrollPane jScrollPane;

    public PlaylistView() {
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

    public void registerController(ActionListener controller){
        this.controller = controller;
    }

    public void bringPlaylists() {

        LinkedList<Playlist> model = manager.getPlaylistsOfUser(Store.getUser());

        JPanel panel = new JPanel();
        JButton crearPlaylist= new JButton();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        crearPlaylist.setText("Crear Playlist");

        for(Playlist p: model){
            panel.add(new PlayListRender(p,controller));
        }
        panel.add(crearPlaylist);
        this.jScrollPane.setViewportView(panel);
    }

}
