package presentation.controller;

import business.SongPlayer;
import presentation.view.BottomBarPanel;
import presentation.view.DetailedSongView;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongPlayerController implements ActionListener {
    private static SongPlayer songPlayer;
    private static BottomBarPanel bottomBarPanel;
    private static DetailedSongView detailedSongView;
    private MainManagerView mainManagerView;

    public SongPlayerController(BottomBarPanel bottomBarPanel, DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        this.songPlayer = new SongPlayer();
        this.bottomBarPanel = bottomBarPanel;
        this.detailedSongView = detailedSongView;
        this.mainManagerView = mainManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("PREVIOUS_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1);
                System.out.println("Anterior");
            }
            if (e.getActionCommand().equals("PAUSE_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2);
                System.out.println("Parar");
            }
            if (e.getActionCommand().equals("PLAY_SONG")) {
                BottomBarPanel.updateSong(detailedSongView.getSong());
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1);
                System.out.println("Play");
            }
            if (e.getActionCommand().equals("NEXT_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1);
                System.out.println("Next");
            }
            if (e.getActionCommand().equals("DETAILED_VIEW")) {
                detailedSongView.updateSong(BottomBarPanel.getSong());
                mainManagerView.changeView(5, 1);
                System.out.println("Detalles");
            }
        } catch (NullPointerException exception) {
            System.out.println("Nada que reproducir/parar. No hace falta mostrar mensaje");
        }
    }

    // Métodos estáticos para que cualquier clase pueda acceder a ellos sin necesidad de instanciar

    public static void playSong () {
        // Si se le da al play a traves de la vista detallada, hace falta actualizar la barra
        if (!BottomBarPanel.getSong().getTitle().equals(detailedSongView.getSong().getTitle())) {
            BottomBarPanel.updateSong(detailedSongView.getSong());
        }
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1);
    }

    public static void pauseSong () {
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2);
    }
}
