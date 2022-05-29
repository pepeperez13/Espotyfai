package presentation.controller;

import business.PlaylistManager;
import business.Owner;
import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.PlaylistView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla la vista del PlaylistView
 */
public class PlayListController implements ActionListener {
    private  PlaylistView playlistView;
    private PlaylistManager playlistManager;

    /**
     * Constructor de la clase
     * @param playlistView
     */
    public PlayListController(PlaylistView playlistView) {
        this.playlistManager = new PlaylistManager();
        this.playlistView = playlistView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PlayListRender.DELETE_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_ELIMINAR");
            try{
                playlistManager.deletePlaylist(playlist.getName());
                playlistView.bringPlaylists(playlistManager.getDataPlaylists(),playlistManager.getPlaylistsOfUser(Owner.getUser()));
            }catch(Exception exception){
                exception.printStackTrace();
            }


         }else if (e.getActionCommand().equals(PlaylistView.CREAR_PLAYLIST)) {
            try {
                String nombrePlaylist = PlaylistView.crearPlaylist();
                // cancel
                if(nombrePlaylist == null || nombrePlaylist.trim().isEmpty()){

                }
                else{
                    playlistManager.createPlaylist(nombrePlaylist.trim(), Owner.getUser().getName());
                    playlistView.bringPlaylists(playlistManager.getDataPlaylists(),playlistManager.getPlaylistsOfUser(Owner.getUser()));
                }

            } catch (Exception exception) {
                this.playlistView.showErrorPlaylistCreation();
            }
        }
    }
}
