package presentation.view;

import business.entities.Song;
import presentation.controller.SongPlayerController;

import javax.swing.*;
import java.awt.*;

public class BottomBarPanel extends JPanel {
    private SongPlayerController controller;
    private static Song song;
    private static JPanel song_player;
    private static GridBagConstraints c;
    private static JLabel songInfo;

    public BottomBarPanel (DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        controller = new SongPlayerController(this, detailedSongView, mainManagerView);

        song_player = new JPanel(new GridBagLayout());
        song_player.setBackground(new Color(191, 105, 240));
        c = new GridBagConstraints();

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

        JButton repeatSong = new JButton("Repeat Song");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        song_player.add(repeatSong, c);
        next_song.setActionCommand("REPEAT_SONG");
        next_song.addActionListener(controller);

        JButton repeatPlaylist = new JButton("Repeat Playlist");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        song_player.add(repeatPlaylist, c);
        next_song.setActionCommand("REPEAT_PLAYLIST");
        next_song.addActionListener(controller);

        this.add(song_player);
        this.setBackground(new Color(191, 105, 240));

    }

    public static void updateSong (Song song) {
        try {
            BottomBarPanel.song = song;

            if (songInfo != null) {
                song_player.remove(songInfo);
            }

            songInfo = setSongInfo(song.getTitle(), song.getArtist());

            song_player.add(songInfo);
        } catch (NullPointerException e) {
            System.out.println("No hay canción para reproducir. No hace falta mostrar nada");
        }
    }

    public static JLabel setSongInfo (String title, String artist) {
        JLabel song_info_label = new JLabel(title + " - " + artist);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;

        return song_info_label;
    }

    public static Song getSong () {
        return song;
    }
}