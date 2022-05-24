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

public class BottomBarPanelController implements ActionListener {
    private SongPlayer songPlayer;
    private BottomBarPanel bottomBarPanel;
    private DetailedSongView detailedSongView;
    private MainManagerView mainManagerView;

    public BottomBarPanelController(BottomBarPanel bottomBarPanel, DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        this.songPlayer = new SongPlayer();
        this.bottomBarPanel = bottomBarPanel;
        this.detailedSongView = detailedSongView;
        this.mainManagerView = mainManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("PREVIOUS_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
        }
        if (e.getActionCommand().equals("PAUSE_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 2);
        }
        if (e.getActionCommand().equals("PLAY_SONG")) {
            songPlayer.managePlayer("path", 1);
        }
        if (e.getActionCommand().equals("NEXT_SONG")) {
            songPlayer.managePlayer(bottomBarPanel.getSong().getPath(), 1);
        }
        if (e.getActionCommand().equals("DETAILED_VIEW")) {
            detailedSongView.updateSong(bottomBarPanel.getSong());
            mainManagerView.changeView(5, 1);
        }
    }
}

