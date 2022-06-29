package presentation.controller;

import business.BuscadorManager;
import business.entities.Song;
import presentation.view.BuscadorView;
import presentation.view.MainManagerView;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla los eventos de la vista del buscador de canciones
 */
public class BuscadorViewController implements ActionListener {
    private final BuscadorManager buscadorManager;
    private final BuscadorView buscadorView;
    private MainManagerView mainManagerView;
    private DetailedSongView detailedSongView;
    private Song cancion_encontrada;

    /**
     * Constrcutor de la clase
     * @param buscadorView vista que se controla
     * @param buscadorManager manager dedicado
     * @param detailedSongView vista detallada, a la que se accede tras buscar una cancion
     * @param mainManagerView mainmanager para poder acceder al cardpanel
     */
    public BuscadorViewController(BuscadorView buscadorView, BuscadorManager buscadorManager, DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        this.buscadorManager = buscadorManager;
        this.buscadorView = buscadorView;
        this.detailedSongView = detailedSongView;
        this.mainManagerView = mainManagerView;
    }

    /**
     * Gestiona los eventos que ocurren en la vista
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(buscadorView.SEARCH_SONG)) {
            cancion_encontrada = buscadorManager.buscarCancion(buscadorView.getSearchSong());
            buscadorView.setSearch(cancion_encontrada);
        } else {
            if (e.getActionCommand().equals(buscadorView.DETAIL_SONG)) {
                if (cancion_encontrada != null) {
                    detailedSongView.updateSong(cancion_encontrada);
                    mainManagerView.changeView(5, 1);
                } else {
                    buscadorView.cannotShowDetails();
                }
            }
        }
    }
}