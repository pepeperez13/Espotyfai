package presentation.controller;

import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.MainManagerView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private final MainManagerView menuBarView;

    public MainViewController (MainManagerView menuBarView) {
        this.menuBarView = menuBarView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainManagerView.GO_INICIO)) {
            menuBarView.changeView(1);
            System.out.println(1);
        } else if (e.getActionCommand().equals(MainManagerView.GO_BUSCADOR)) {
            menuBarView.changeView(2);
            System.out.println(2);
        } else if (e.getActionCommand().equals(MainManagerView.GO_MISLISTAS)) {
            menuBarView.changeView(3);
            System.out.println(3);
        } else if (e.getActionCommand().equals(MainManagerView.GO_SETTINGS)) {
            menuBarView.changeView(4);
            System.out.println(4);
        }
        else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)){
            Playlist parameterPlayList = (Playlist) ((JButton)e.getSource()).getClientProperty( "PLAYLIST" );
            SongListlView.selectedPlaylist = parameterPlayList;
            menuBarView.changeView(6);
        }
    }

}
