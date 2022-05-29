package presentation.controller;

import business.SongManager;
import presentation.view.DetailedSongView;
import presentation.view.MainManagerView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Clase que controla la cancion seleccionada en la JTable
 */
public class SongsTableController implements ListSelectionListener {

    private DetailedSongView detailedSongView;
    private MainManagerView mainManagerView;
    private JTable songsTableView;

    /**
     * Constructor
     * @param songsTableView Vista que muestra la JTable
     * @param mainManagerView Vista principal
     * @param detailedSongView Vista detallada de las canciones
     */
    public SongsTableController(JTable songsTableView, MainManagerView mainManagerView, DetailedSongView detailedSongView) {
        this.songsTableView = songsTableView;
        this.mainManagerView = mainManagerView;
        this.detailedSongView = detailedSongView;
    }

    /**
     * Método que obtiene el nombre de la canción de la JTable y se la pasa a la vista detallada
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        String aux;
        aux = (String) songsTableView.getValueAt(songsTableView.getSelectedRow(), 0);
        detailedSongView.updateSong(SongManager.SelectSong(aux));
        mainManagerView.changeView(5, 1);
    }
}
