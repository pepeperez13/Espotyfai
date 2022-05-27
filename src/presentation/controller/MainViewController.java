package presentation.controller;

import business.*;
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
    private PlaylistManager playlistManager;
    private SongPlaylistManager songManager;
    private static Playlist parameterPlayList = new Playlist();
    Song song = new Song();
    private static boolean reproducingPlaylist;


    public MainViewController(MainMenu menuBarView, MainManagerView mainManagerView) {
        this.mainMenu = menuBarView;
        this.mainManagerView = mainManagerView;
        this.playlistManager = new PlaylistManager();
        this.songManager = new SongPlaylistManager();
        reproducingPlaylist = false;
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
            parameterPlayList = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST");
            SongListlView.selectedPlaylist = parameterPlayList;
            mainManagerView.changeView(12, 1);
        } else if (e.getActionCommand().equals(PlaylistView.CREAR_PLAYLIST)) {
            try {
                String nombrePlaylist = PlaylistView.crearPlaylist();
                if (playlistManager.existPlaylist(Store.getUser(), nombrePlaylist)) {
                    PlaylistView.showErrorPlaylistCreation();
                } else {
                    playlistManager.createPlaylist(nombrePlaylist, Store.getUser().getName());
                }
            } catch (NullPointerException exception) {
                exception.printStackTrace();
            }
        } else if (e.getActionCommand().equals(PlayListRender.DELETE_BUTTON)) {
            parameterPlayList = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_ELIMINAR");
            playlistManager.deletePlaylist(parameterPlayList.getName());
        } else if (e.getActionCommand().equals(SongListRender.DELETE_BUTTON)) {
            song = (Song) ((JButton) e.getSource()).getClientProperty("SONG_ELIMINAR");
            songManager.deleteSongPlaylistSong(parameterPlayList.getName(), song.getTitle());
        } else if (e.getActionCommand().equals(PlayListRender.REPRODUCIR_BUTTON)) {
            parameterPlayList = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_REPRODUCIR");
            MainViewController.reproducingPlaylist = true;
            BottomBarPanel.updateSong(parameterPlayList.getSongs().get(0));
            SongPlayerController.playPlaylist();
        } else if (e.getActionCommand().equals(SongListRender.UP_BUTTON)) {
            song = (Song) ((JButton) e.getSource()).getClientProperty("song_subir");
            songManager.updatePosP(song.getTitle(), parameterPlayList.getName(), 1);
        } else if (e.getActionCommand().equals(SongListRender.DOWN_BUTTON)) {
            song = (Song) ((JButton) e.getSource()).getClientProperty("song_bajar");
            songManager.updatePosP(song.getTitle(), parameterPlayList.getName(), 2);
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


