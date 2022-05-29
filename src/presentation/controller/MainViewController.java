package presentation.controller;

import business.Owner;
import business.PlaylistManager;
import business.SongManager;
import business.SongPlaylistManager;
import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.BottomBarPanel;
import presentation.view.MainManagerView;
import presentation.view.MainMenu;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que controla que vistas mostrar según la opción del menu general clickada
 */
public class MainViewController implements ActionListener {

    private MainMenu mainMenu;
    private MainManagerView mainManagerView;
    private SongManager songManager;
    private PlaylistManager playlistManager = new PlaylistManager();
    private static Playlist parameterPlayList = new Playlist();
    private static boolean reproducingPlaylist;
    private SongPlaylistManager songPlaylistManager;

    /**
     * Constructor
     * @param menuBarView vista del MENU GENERAL
     * @param mainManagerView vista principal
     */
    public MainViewController(MainMenu menuBarView, MainManagerView mainManagerView) {
        this.mainMenu = menuBarView;
        this.mainManagerView = mainManagerView;

        this.songManager= new SongManager();
        this.songPlaylistManager = new SongPlaylistManager();

    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
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

    /**
     * Metodo que te devuelve todas las playlists del usuario
     * @return playlists del usuario
     */
    public LinkedList<Playlist> getPlaylistsOfUser(){
       return playlistManager.getPlaylistsOfUser(Owner.getUser());
    }

    /**
     * Metodo que te devuelve todas las playlists
     * @return todas las playlists
     */
    public LinkedList<Playlist> getAllPlaylists(){
        return playlistManager.getDataPlaylists();
    }
    /**
     * Método que retorna si está reproduciendo una playlist
     * @return True si se está reproduciendo una playlist
     */
    public static boolean isReproducingPlaylist() {
        return MainViewController.reproducingPlaylist;
    }

    /**
     *Método que recibe un boolean que indica si se esta reproduciendo una playlist
     * @return void
     */
    public static void setReproducingPlaylist(boolean playing) {
        MainViewController.reproducingPlaylist = playing;
    }

    /**
     * Método que indica que playlist que se está reproduciendo
     * @return Playlist Playlist que se esta reproduciendo
     */
    public static Playlist getReproducingPlaylist() {
        return parameterPlayList;
    }
}


