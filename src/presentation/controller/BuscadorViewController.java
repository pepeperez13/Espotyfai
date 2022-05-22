package presentation.controller;

import business.BuscadorManager;
import business.entities.Song;
import presentation.view.BuscadorView;
import presentation.view.MainManagerView;
import presentation.view.detailedSong.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorViewController implements ActionListener {
    private final BuscadorManager buscadorManager;
    private final BuscadorView buscadorView;
    private MainManagerView mainManagerView;
    private DetailedSongView detailedSongView;
    private Song song;
    private Song cancion_encontrada;

    public BuscadorViewController(BuscadorView buscadorView, BuscadorManager buscadorManager) {
        this.buscadorManager = buscadorManager;
        this.buscadorView = buscadorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(buscadorView.SEARCH_SONG)) {
            cancion_encontrada = buscadorManager.buscarCancion(buscadorView.getSearchSong());
            buscadorView.setSearch(cancion_encontrada);
        } else {
            if (e.getActionCommand().equals(buscadorView.DETAIL_SONG)) {
                detailedSongView.recibirCancion(cancion_encontrada);
                mainManagerView.changeView(5);
            }
        }
    }

}