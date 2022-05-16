package presentation.view.detailedSong;

import business.entities.Playlist;
import presentation.controller.DetailedSongController;
import presentation.view.detailedSong.DetailedSongView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ShowPlaylistsFrame extends JFrame {

    public ShowPlaylistsFrame (DetailedSongView detailedSongView, DetailedSongController controller) {

        setSize(400, 300);
        setLocation(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box box= Box.createVerticalBox();
        LinkedList<Playlist> playlists = controller.getDataPlaylists();
        for (int i=1; i<=10; i++) {
            JButton btn= new JButton(playlists.get(i).getName());
            btn.setMaximumSize(new Dimension(150, 30));
            box.add(btn);
        }
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scroll= new JScrollPane(box);
        scroll.setPreferredSize(new Dimension(150, 100));
        add(scroll);

    }
}
