package presentation.view;

import business.entities.Song;
import presentation.controller.BottomBarPanelController;

import javax.swing.*;
import java.awt.*;

public class BottomBarPanel extends JPanel {
    private BottomBarPanelController controller;
    private Song song;

    public BottomBarPanel (DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        controller = new BottomBarPanelController(this, detailedSongView, mainManagerView);

        JPanel song_player = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JLabel song_info_label = new JLabel("nombre de la cancion - artista");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        song_player.add(song_info_label, c);

        JButton back_song = new JButton("<<");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        song_player.add(back_song, c);
        back_song.setActionCommand("PREVIOUS_SONG");
        back_song.addActionListener(controller);

        JButton pause = new JButton("||");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        song_player.add(pause, c);
        pause.setActionCommand("PAUSE_SONG");
        pause.addActionListener(controller);

        JButton play = new JButton(">");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        song_player.add(play, c);
        play.setActionCommand("PLAY_SONG");
        play.addActionListener(controller);

        JButton next_song = new JButton(">>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        song_player.add(next_song, c);
        next_song.setActionCommand("NEXT_SONG");
        next_song.addActionListener(controller);

        JButton full_screen = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;       //aligned with button 2
        c.gridy = 2;       //third row
        song_player.add(full_screen, c);
        full_screen.setActionCommand("DETAILED_VIEW");
        full_screen.addActionListener(controller);

        this.add(song_player);

        //this.setVisible(true);
    }

    public void updateSong (Song song) {
        this.song = song;
    }

    public Song getSong () {
        return this.song;
    }
}
