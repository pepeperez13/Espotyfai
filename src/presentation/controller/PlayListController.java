package presentation.controller;

import business.PlaylistManager;
import business.Store;
import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.view.PlaylistView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListController implements ActionListener {
    private PlaylistManager playlistManager;


    public PlayListController() {
        this.playlistManager = new PlaylistManager();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PlayListRender.DELETE_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_ELIMINAR");
             playlistManager.deletePlaylist(playlist.getName());
         }else if (e.getActionCommand().equals(PlaylistView.CREAR_PLAYLIST)) {
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
        }
    }
}
