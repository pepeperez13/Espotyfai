package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.SongPlaylistManager;
import business.Store;
import business.entities.Playlist;
import business.entities.Song;
import persistance.PlaylistDAO;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;
import presentation.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainViewController implements ActionListener {
    private MainMenu mainMenu;
    private MainManagerView mainManagerView;
    private SongManager songManager;

    private static Playlist parameterPlayList = new Playlist();
    private static boolean reproducingPlaylist;
    private SongPlaylistManager songPlaylistManager;


    public MainViewController(MainMenu menuBarView, MainManagerView mainManagerView) {
        this.mainMenu = menuBarView;
        this.mainManagerView = mainManagerView;

        this.songManager= new SongManager();
        this.songPlaylistManager = new SongPlaylistManager();

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
        } else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST");
            SongListlView.selectedPlaylist = playlist;
            SongListlView.allSongs = songManager.ListSongs();
            mainManagerView.changeView(12, 1);
        }
        else if (e.getActionCommand().equals(PlayListRender.REPRODUCIR_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_REPRODUCIR");

            if (playlist.getSongs().size() > 0) {
                parameterPlayList = playlist;
                MainViewController.reproducingPlaylist = true;
                BottomBarPanel.updateSong(playlist.getSongs().get(0));
                SongPlayerController.playPlaylist();
                SongPlayerController.setRepeatPlaylist(false);
            }

        }
    }

    public static boolean isReproducingPlaylist() {
        return MainViewController.reproducingPlaylist;
    }

    public static void setReproducingPlaylist(boolean playing) {
        MainViewController.reproducingPlaylist = playing;
    }

    public static Playlist getReproducingPlaylist() {
        return parameterPlayList;
    }
}


