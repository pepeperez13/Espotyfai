package presentation.controller;

import business.BuscadorManager;
import business.entities.Song;
import presentation.view.BuscadorView;
import presentation.view.MainManagerView;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorViewController implements ActionListener {
    private final BuscadorManager buscadorManager;
    private final BuscadorView buscadorView;
    private MainManagerView mainManagerView;
    private DetailedSongView detailedSongView;
    private Song song;
    private Song cancion_encontrada;

    public BuscadorViewController(BuscadorView buscadorView, BuscadorManager buscadorManager, DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        this.buscadorManager = buscadorManager;
        this.buscadorView = buscadorView;
        this.detailedSongView = detailedSongView;
        this.mainManagerView = mainManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(buscadorView.SEARCH_SONG)) {
            cancion_encontrada = buscadorManager.buscarCancion(buscadorView.getSearchSong());
            buscadorView.setSearch(cancion_encontrada);
        } else {
            if (e.getActionCommand().equals(buscadorView.DETAIL_SONG)) {
                detailedSongView.updateSong(cancion_encontrada);
                mainManagerView.changeView(5);
            }
        }
    }
}