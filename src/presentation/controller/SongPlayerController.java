package presentation.controller;

import business.SongPlayer;
import presentation.view.BottomBarPanel;
import presentation.view.DetailedSongView;
import presentation.view.MainManagerView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        if (e.getActionCommand().equals("PREVIOUS_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
            System.out.println("Anterior");
        }
        if (e.getActionCommand().equals("PAUSE_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 2);
            System.out.println("Parar");
        }
        if (e.getActionCommand().equals("PLAY_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
            System.out.println("Play");
        }
        if (e.getActionCommand().equals("NEXT_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
            System.out.println("Next");
        }
        if (e.getActionCommand().equals("DETAILED_VIEW")) {
            detailedSongView.updateSong(bottomBarPanel.getSong());
            mainManagerView.changeView(5, 1);
            System.out.println("Detalles");
        }
    }

    // Métodos estáticos para que cualquier clase pueda acceder a ellos sin necesidad de instanciar

    public static void playSong () {
        bottomBarPanel.updateSong(detailedSongView.getSong());
        songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
    }

    public static void pauseSong () {
        songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 2);
    }
}

