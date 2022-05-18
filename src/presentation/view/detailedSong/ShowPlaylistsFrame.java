package presentation.view.detailedSong;

import business.entities.Playlist;
import presentation.controller.DetailedSongController;
import presentation.view.detailedSong.DetailedSongView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ShowPlaylistsFrame extends JFrame {

    private ShowPlaylistsController controller;

    public ShowPlaylistsFrame (DetailedSongView detailedSongView) {
        controller = new ShowPlaylistsController(this, detailedSongView);
        setSize(600, 400);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Box box= Box.createVerticalBox();
        //LinkedList<Playlist> playlists = controller.getDataPlaylists();
        Playlist p1 = new Playlist("P1", "popo");
        Playlist p2 = new Playlist("P1htyhrtyhtygfbfgbfgbfgblfidkbjotiblhblihihbhhyyhty", "popo");
        Playlist p3 = new Playlist("Pgrtgrt1", "popo");
        Playlist p4 = new Playlist("P1t", "popo");

        LinkedList<Playlist> playlists = new LinkedList<>();
        playlists.add(p1); playlists.add(p2); playlists.add(p3); playlists.add(p4);


        for (int i= 0; i < playlists.size(); i++) {
            JButton button = new JButton(playlists.get(i).getName());
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);
            button.setActionCommand(playlists.get(i).getName());
            button.addActionListener(controller);
            box.add(button);
        }

        JScrollPane scroll= new JScrollPane(box);
        //scroll.setPreferredSize(new Dimension(150, 100));
        add(scroll);


    }
}
