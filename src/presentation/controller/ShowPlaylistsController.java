package presentation.controller;

import business.PlaylistManager;
import business.SongPlaylistManager;
import business.Store;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla los posibles eventos generados cuando se quiere añadir una canción a una playlist desde la vista detallada
 * @author Jose Perez
 */
public class ShowPlaylistsController implements ActionListener {

    private DetailedSongView detailedSongView;
    private SongPlaylistManager songPlaylistManager;

    /**
     * Método constructor que inicaliza o enlaza las clases necesarias para este controlador
     * @param detailedSongView
     */
    public ShowPlaylistsController(DetailedSongView detailedSongView) {
        this.songPlaylistManager = new SongPlaylistManager();
        this.detailedSongView = detailedSongView;
    }

    /**
     * Método que gestiona los distintos eventos generados al intentar añadir una canción a una playlist desde la vista detallada
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (songPlaylistManager.songExistsInPlaylist(detailedSongView.getSongTitle(), e.getActionCommand())) {
            detailedSongView.showErrorMessage();
        } else {
            try {
                songPlaylistManager.InsertNewSongPlaylist(detailedSongView.getSongTitle(), e.getActionCommand());
                detailedSongView.showOKMessage();
            } catch (Exception ex) {
                // Si no somos el propietario de una playlist, no se pueden añadir canciones
                detailedSongView.showErrorUserMessage();
            }
        }
    }
}
