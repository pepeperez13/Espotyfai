package presentation.view;

import business.PlaylistManager;
import business.Store;
import business.entities.Playlist;
import presentation.render.PlayListRender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ConfigPlaylistView extends JPanel {

    private PlaylistManager manager = new PlaylistManager();

    public ConfigPlaylistView() {
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        JScrollPane jScrollPane= new JScrollPane(bringPlaylists());
        this.add(jScrollPane);

    }

    private JList<Playlist> bringPlaylists() {
        //if(Store.LOGGED_USER == null) return new JList<>() ;

        // create List model
        DefaultListModel<Playlist> model = new DefaultListModel<>();
//        // add item to model
//        model.addElement(new Playlist("Playlist 1","yo@email.com"));
//        model.addElement(new Playlist("Playlist 2","yo@email.com"));
        //Store store= new Store();
        //LinkedList<Playlist> misPlaylists = manager.getPlaylistsOfUser(store.getUser());
//
//
       // for(Playlist p: misPlaylists){
       //    model.addElement(p);
      //  }

        // create JList with model
        JList<Playlist> list = new JList<Playlist>(model);
        list.setCellRenderer(new PlayListRender());
        return list;
    }
}
