package presentation.controller;

import business.BuscadorManager;
import business.entities.Song;
import presentation.view.BuscadorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorViewController implements ActionListener {
    private final BuscadorManager buscadorManager;
    private final BuscadorView buscadorView;
    private Song song;

    public BuscadorViewController(BuscadorView buscadorView, BuscadorManager buscadorManager) {
        this.buscadorManager = buscadorManager;
        this.buscadorView = buscadorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(buscadorView.SEARCH_SONG)) {
            Song cancion_encontrada = buscadorManager.buscarCancion(buscadorView.getSearchSong());
            buscadorView.setSearch(cancion_encontrada);

        }
    }

}