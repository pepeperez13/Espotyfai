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
    private  PlaylistView playlistView;
    private PlaylistManager playlistManager;


    public PlayListController(PlaylistView playlistView) {
        this.playlistManager = new PlaylistManager();
        this.playlistView = playlistView;
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
            try{
                playlistManager.deletePlaylist(playlist.getName());
                playlistView.bringPlaylists();
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
                    playlistManager.createPlaylist(nombrePlaylist.trim(), Store.getUser().getName());
                    playlistView.bringPlaylists();
                }

            } catch (Exception exception) {
                this.playlistView.showErrorPlaylistCreation();
            }
        }
    }
}
