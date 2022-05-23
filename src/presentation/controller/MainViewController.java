package presentation.controller;

import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.MainManagerView;
import presentation.view.MainMenu;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private MainMenu mainMenu;
    private MainManagerView mainManagerView;

    public MainViewController (MainMenu menuBarView, MainManagerView mainManagerView) {
        this.mainMenu = menuBarView;
        this.mainManagerView = mainManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainMenu.GO_INICIO)) {
            mainManagerView.changeView(1, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_BUSCADOR)) {
            mainManagerView.changeView(2, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_MISLISTAS)) {
            mainManagerView.changeView(3, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_SETTINGS)) {
            mainManagerView.changeView(6, 2);
        }
        else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)){
            Playlist parameterPlayList = (Playlist) ((JButton)e.getSource()).getClientProperty( "PLAYLIST" );
            SongListlView.selectedPlaylist = parameterPlayList;
            mainManagerView.changeView(12, 1);
        }
    }

}
