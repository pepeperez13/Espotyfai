package presentation.controller;

import business.PlaylistManager;
import business.Owner;
import business.SongManager;
import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.BottomBarPanel;
import presentation.view.MainManagerView;
import presentation.view.PlaylistView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que controla la vista del PlaylistView
 */
public class PlayListController implements ActionListener {
    private  PlaylistView playlistView;
    private static PlaylistManager playlistManager;
    private MainManagerView mainManagerView;
    private static Playlist parameterPlayList = new Playlist();
    private static boolean reproducingPlaylist;

    /**
     * Constructor de la clase
     * @param playlistView vista de las playlist
     */
    public PlayListController(PlaylistView playlistView, MainManagerView mainManagerView) {
        this.playlistManager = new PlaylistManager();
        this.playlistView = playlistView;
        this.mainManagerView = mainManagerView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PlayListRender.DELETE_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_ELIMINAR");
            playlistManager.deletePlaylist(playlist.getName());
            playlistView.bringPlaylists(playlistManager.getDataPlaylists(),playlistManager.getPlaylistsOfUser(Owner.getUser()));

         }else if (e.getActionCommand().equals(PlaylistView.CREAR_PLAYLIST)) {
            String nombrePlaylist = PlaylistView.crearPlaylist();
            // cancel
            if (nombrePlaylist != null && !nombrePlaylist.trim().isEmpty()){
                playlistManager.createPlaylist(nombrePlaylist.trim(), Owner.getUser().getName());
                playlistView.bringPlaylists(playlistManager.getDataPlaylists(),playlistManager.getPlaylistsOfUser(Owner.getUser()));
            }

        }
        else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST");
            SongListlView.setSelectedPlaylist(playlist);
            SongListlView.setAllSongs(SongManager.ListSongs());
            mainManagerView.changeView(12, 1);
        }
        else if (e.getActionCommand().equals(PlayListRender.REPRODUCIR_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_REPRODUCIR");

            if (playlist.getSongs().size() > 0) {
                parameterPlayList = playlist;
                reproducingPlaylist = true;
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
    public static LinkedList<Playlist> getPlaylistsOfUser(){
        return playlistManager.getPlaylistsOfUser(Owner.getUser());
    }

    /**
     * Metodo que te devuelve todas las playlists
     * @return todas las playlists
     */
    public static LinkedList<Playlist> getAllPlaylists(){
        return playlistManager.getDataPlaylists();
    }
    /**
     * Método que retorna si está reproduciendo una playlist
     * @return True si se está reproduciendo una playlist
     */
    public static boolean isReproducingPlaylist() {
        return reproducingPlaylist;
    }

    /**
     *Método que recibe un boolean que indica si se esta reproduciendo una playlist
     */
    public static void setReproducingPlaylist(boolean playing) {
        reproducingPlaylist = playing;
    }

    /**
     * Método que indica que playlist que se está reproduciendo
     * @return Playlist Playlist que se esta reproduciendo
     */
    public static Playlist getReproducingPlaylist() {
        return parameterPlayList;
    }
}
