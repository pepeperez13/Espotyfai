package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.entities.Playlist;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import presentation.view.BottomBarPanel;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que controla las diferentes acciones que se pueden realizar desde la vista detallada
 * @author Jose Perez
 */
public class DetailedSongController implements ActionListener {

    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;

    /**
     * Constructor de la clase, incializa o asigna otras clases necesarias
     * @param detailedSongView vista detallada que estará controlando
     */
    public DetailedSongController(DetailedSongView detailedSongView) {
        this.detailedSongView = detailedSongView;
        this.playlistManager = new PlaylistManager();
    }

    /**
     * Controla los posibles eventos que genera la vista detallada
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_TO_PLAYLIST_COMMAND")) {
            detailedSongView.showPlaylists();
        }
        if (e.getActionCommand().equals("CLOSE_PANEL_COMMAND")) {
            detailedSongView.setVisible(false);
        }
        if (e.getActionCommand().equals("PLAY_SONG_COMMAND")) {
            // Si se le da a play desde la vista detallada, seguro que no se está reproduciendo desde playlist
            PlayListController.setReproducingPlaylist(false);
            BottomBarPanel.updateSong(detailedSongView.getSong());
            SongPlayerController.playSong();
        }
        if (e.getActionCommand().equals("PAUSE_SONG_COMMAND")) {
            SongPlayerController.pauseSong();
        }
    }

    /**
     * Carga las playlist existentes, para poder mostrarlas cuando un usuario quiere añadir una canción a una playlist
     * @return lista de las playlist existentes
     */
    public LinkedList<Playlist> getDataPlaylists () {
        return playlistManager.getDataPlaylists();
    }
}
