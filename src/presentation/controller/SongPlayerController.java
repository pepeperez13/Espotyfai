package presentation.controller;

import business.SongManager;
import business.SongPlayer;
import business.entities.Playlist;
import business.entities.Song;
import presentation.view.BottomBarPanel;
import presentation.view.DetailedSongView;
import presentation.view.MainManagerView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SongPlayerController implements ActionListener {
    private static SongPlayer songPlayer;
    private static BottomBarPanel bottomBarPanel;
    private static DetailedSongView detailedSongView;
    private MainManagerView mainManagerView;
    private static boolean repeatPlaylist;


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
                Song song = findSongToReproduce(-1);
                if (song != null) {
                    BottomBarPanel.updateSong(song);
                    songPlayer.managePlayer(song.getPath(), 1,song.getSongDurationSeconds());
                }
                System.out.println("Previous");
            }
            if (e.getActionCommand().equals("PAUSE_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2, BottomBarPanel.getSong().getSongDurationSeconds());
                System.out.println("Parar");
            }
            if (e.getActionCommand().equals("PLAY_SONG")) {
                //BottomBarPanel.updateSong(detailedSongView.getSong());
                MainViewController.setReproducingPlaylist(false);
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1, BottomBarPanel.getSong().getSongDurationSeconds());
                BottomBarPanel.setValueSlider(0);
                System.out.println("Play");
            }
            if (e.getActionCommand().equals("NEXT_SONG")) {
                Song song = findSongToReproduce(+ 1);
                if (song != null) {
                    BottomBarPanel.updateSong(song);
                    songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
                }
                //detailedSongView.updateSong(nextSong); Que se actualice solo cuando se le de a ver, si no se queda buscando lyrics
                System.out.println("Next");
            }
            if (e.getActionCommand().equals("DETAILED_VIEW")) {
                detailedSongView.updateSong(BottomBarPanel.getSong());
                mainManagerView.changeView(5, 1);
                System.out.println("Detalles");
            }
            if (e.getActionCommand().equals("REPEAT_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 5, BottomBarPanel.getSong().getSongDurationSeconds());
                System.out.println("Loop song");
            }
            if (e.getActionCommand().equals("REPEAT_PLAYLIST")) {
                if (MainViewController.isReproducingPlaylist()) {
                    repeatPlaylist = true;
                }

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
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1,BottomBarPanel.getSong().getSongDurationSeconds());
    }

    public static void autoNextSong () {
        Song song = findSongToReproduce(+ 1);
        if (song != null) {
            BottomBarPanel.updateSong(song);
            songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
        }
    }

    public static void playPlaylist () {
        List<Song> songsPlaylist = MainViewController.getReproducingPlaylist().getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
        Song song = songsPlaylist.get(0);
        BottomBarPanel.updateSong(song);
        songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
    }

    public static void pauseSong () {
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2, BottomBarPanel.getSong().getSongDurationSeconds());
    }

    public static Song getPlayingSong () {
        return  BottomBarPanel.getSong();
    }

    public static void setRepeatPlaylist (boolean repeatPlaylist) {
        SongPlayerController.repeatPlaylist = repeatPlaylist;
    }

    private static Song findSongToReproduce (int index) {
        Song song = null;
        List<Song> playlistSongs = null;
        try {
            if (MainViewController.isReproducingPlaylist()) {
                playlistSongs = MainViewController.getReproducingPlaylist().getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
                // Si se está reproduciendo una playlist, se quiere repetir, y la canción actual es la última, asignamos la nueva canción como la primera de la lista
                if (repeatPlaylist && MainViewController.isReproducingPlaylist() && BottomBarPanel.getSong().getPosition() == playlistSongs.size()) {
                    song = playlistSongs.get(0);
                } else {
                    song = playlistSongs.get(BottomBarPanel.getSong().getPosition() - 1 + index);
                }
            } else {
                // Buscamos y guardamos como "song" la siguiente canción a la que se está reproduciendo ahora
                for (int i = 0; i < SongManager.ListSongs().size(); i++) {
                    if (SongManager.ListSongs().get(i).getTitle().equals(BottomBarPanel.getSong().getTitle())) {
                        song = SongManager.ListSongs().get(i + index);
                        break;
                    }
                }
            }
            System.out.println("Next");
        } catch (IndexOutOfBoundsException exception) {
            // Si nos encontramos en la primera canción (ya sea de la playlist o de las canciones en general) no podemos pasar a la siguiente

        }
        return song;
    }

    public static double getCurrentTime () {
        return songPlayer.getCurrentTime();
    }

    public static double getEndTime () {
        return songPlayer.getEndTime();
    }
}

