package presentation.view;

import business.entities.Playlist;
import presentation.render.PlayListRender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfigPlaylistView extends JPanel {
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public ConfigPlaylistView() {
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        this.add(new JScrollPane(bringPlaylists()),
                BorderLayout.CENTER);

    }

    private JList<Playlist> bringPlaylists() {
        // create List model
        DefaultListModel<Playlist> model = new DefaultListModel<>();
        // add item to model
        model.addElement(new Playlist("Playlist 1","yo@email.com"));
        model.addElement(new Playlist("Playlist 2","yo@email.com"));

        // create JList with model
        JList<Playlist> list = new JList<Playlist>(model);
        list.setCellRenderer(new PlayListRender());
        return list;
    }
}
