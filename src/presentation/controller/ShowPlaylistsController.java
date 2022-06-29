package presentation.controller;

import business.SongPlaylistManager;
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
     * @param detailedSongView vista detallada que se esta mostrando
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
            if(songPlaylistManager.InsertNewSongPlaylist(detailedSongView.getSongTitle(), e.getActionCommand())) {
                detailedSongView.showOKMessage();
            }else {
                // Si no somos el propietario de una playlist, no se pueden añadir canciones
                detailedSongView.showErrorUserMessage();
            }

        }
    }
}
