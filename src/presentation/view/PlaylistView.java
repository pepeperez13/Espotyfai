package presentation.view;

import business.PlaylistManager;
import business.entities.Playlist;
import presentation.controller.ConfigPlaylistController;
import presentation.render.PlayListRender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistView extends JPanel {
    private PlayListRender playListRender;
    private PlaylistManager manager = new PlaylistManager();
    private ConfigPlaylistController controller= new ConfigPlaylistController();

    public PlaylistView() {
        this.setBackground(Color.red);
        setSize(1500, 900);
        //setResizable(true);
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        JScrollPane jScrollPane= new JScrollPane(bringPlaylists());
        this.add(jScrollPane);

        controller.setPlayListRender(playListRender);

    }

    private JPanel bringPlaylists() {

        ArrayList<Playlist> model = new ArrayList<Playlist>();
        model.add(new Playlist("Playlist 1","yo@email.com"));
        model.add(new Playlist("Playlist 2","yo@email.com"));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        for(Playlist p: model){
            panel.add(new PlayListRender(p,controller));
        }

        return panel;
    }

}
