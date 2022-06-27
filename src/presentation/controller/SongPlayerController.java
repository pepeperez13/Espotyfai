package presentation.controller;

import business.SongManager;
import business.SongPlayer;
import business.entities.Song;
import presentation.view.BottomBarPanel;
import presentation.view.DetailedSongView;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que controla todos los eventos del BottombarPanel (controla las canciones), y también las acciones de ejecución
 * que pueden provenir de otros lugares como la vista detallada (play/pause) o la vista de playlist (play playlist)
 * @author José Pérez
 */
public class SongPlayerController implements ActionListener {
    private static SongPlayer songPlayer;
    private static BottomBarPanel bottomBarPanel;
    private static DetailedSongView detailedSongView;
    private MainManagerView mainManagerView;
    private static boolean repeatPlaylist;

    /**
     * Constructor que recibe e incia las diferentes clases necesarias para el funcionamiento de esta
     * @param bottomBarPanel panel inferior que controla la reproducción
     * @param detailedSongView vista detallada desde la que se puede play/pause
     * @param mainManagerView vista que envia las acciones de playPlaylist
     */
    public SongPlayerController(BottomBarPanel bottomBarPanel, DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        songPlayer = new SongPlayer();
        SongPlayerController.bottomBarPanel = bottomBarPanel;
        SongPlayerController.detailedSongView = detailedSongView;
        this.mainManagerView = mainManagerView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("PREVIOUS_SONG")) {
                // Encontramos la canción que hay que reproducir (anterior)
                Song song = findSongToReproduce(-1);
                // Si no era la primera, cambiamos la canción
                if (song != null) {
                    BottomBarPanel.updateSong(song);
                    songPlayer.managePlayer(song.getPath(), 1,song.getSongDurationSeconds());
                }
            }
            if (e.getActionCommand().equals("PAUSE_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2, BottomBarPanel.getSong().getSongDurationSeconds());
            }
            if (e.getActionCommand().equals("PLAY_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1, BottomBarPanel.getSong().getSongDurationSeconds());
            }
            if (e.getActionCommand().equals("NEXT_SONG")) {
                // Encontramos la canción que hay que reproducir (siguiente)
                Song song = findSongToReproduce(+ 1);
                // Si no era la última, cambiamos la canción
                if (song != null) {
                    BottomBarPanel.updateSong(song);
                    songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
                }
            }
            if (e.getActionCommand().equals("DETAILED_VIEW")) {
                detailedSongView.updateSong(BottomBarPanel.getSong());
                mainManagerView.changeView(5, 1);
            }
            if (e.getActionCommand().equals("REPEAT_SONG")) {
                songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 3, BottomBarPanel.getSong().getSongDurationSeconds());
            }
            if (e.getActionCommand().equals("REPEAT_PLAYLIST")) {
                if (SongListController.isReproducingPlaylist()) {
                    repeatPlaylist = true;
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Nada que reproducir/parar. No hace falta mostrar mensaje");
        }
    }


    /**
     * Método que se llama cuando se le da al play desde la vista detallada y actualiza el Bottom Bar si es necesario
     */
    public static void playSong () {
        // Si se le da al play a traves de la vista detallada, hace falta actualizar la barra
        if (!BottomBarPanel.getSong().getTitle().equals(detailedSongView.getSong().getTitle())) {
            BottomBarPanel.updateSong(detailedSongView.getSong());
        }
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 1,BottomBarPanel.getSong().getSongDurationSeconds());
    }

    /**
     * Método que llama el reproductor cuando se acaba una canción y hay que pasar a la siguiente automáticamente
     */
    public static void autoNextSong () {
        Song song = findSongToReproduce(+ 1);
        if (song != null) {
            BottomBarPanel.updateSong(song);
            songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
        }
    }

    /**
     * Método que se llama cuando desde la vista de las playlist, se le da al play a una
     */
    public static void playPlaylist () {
        List<Song> songsPlaylist = SongListController.getReproducingPlaylist().getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
        Song song = songsPlaylist.get(0);
        BottomBarPanel.updateSong(song);
        songPlayer.managePlayer(song.getPath(), 1, song.getSongDurationSeconds());
    }

    /**
     * Método que se llama desde la vista detallada para pausar la canción
     */
    public static void pauseSong () {
        songPlayer.managePlayer(BottomBarPanel.getSong().getPath(), 2, BottomBarPanel.getSong().getSongDurationSeconds());
    }

    /**
     * Actualiza el valor de si se está reproduciendo alguna playlist
     * @param repeatPlaylist recibe si se está reproduciendo una playlist o no
     */
    public static void setRepeatPlaylist (boolean repeatPlaylist) {
        SongPlayerController.repeatPlaylist = repeatPlaylist;
    }

    /**
     * Método que encuentra la siguiente canción que se debe reproducir (ya sea anterior o siguiente), teniendo en cuenta
     * si se está reproduciendo desde una playlist o no y si está activada la repetición de playlist
     * @param index indica si se quiere reproducir la siguiente canción o la anterior
     * @return canción que se deberá reproducir a continuación
     */
    private static Song findSongToReproduce (int index) {
        Song song = null;
        List<Song> playlistSongs = null;
        try {
            if (SongListController.isReproducingPlaylist()) {
                // Obtenemos las canciones de la playlist actual, ordenadas según su posicion
                playlistSongs = SongListController.getReproducingPlaylist().getSongs().stream().sorted(Comparator.comparing(Song::getPosition)) .collect(Collectors.toList());
                // Si se está reproduciendo una playlist, se quiere repetir, y la canción actual es la última, asignamos la nueva canción como la primera de la lista
                if (repeatPlaylist && SongListController.isReproducingPlaylist() && BottomBarPanel.getSong().getPosition() == playlistSongs.size()) {
                    song = playlistSongs.get(0);
                } else {
                    // Asignamos la nueva canción como la que hay en la posición actual, + index (que valdrá +1 o -1) según si
                    // se quiere ir hacia delante o hacia atrás
                    song = playlistSongs.get(BottomBarPanel.getSong().getPosition() - 1 + index);
                }
            } else {
                // Buscamos y guardamos como "song" la siguiente canción a la que se está reproduciendo ahora
                for (int i = 0; i < SongManager.ListSongs().size(); i++) {
                    if (SongManager.ListSongs().get(i).getTitle().equals(BottomBarPanel.getSong().getTitle())) {
                        // Asignamos la nueva canción como la que hay en la posición actual, + index (que valdrá +1 o -1) según si
                        // se quiere ir hacia delante o hacia atrás
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

    /**
     * Permite saber a otras partes del programa el tiempo de reproducción transcurrido de la canción actual
     * @return tiempo transcurrido
     */
    public static double getCurrentTime () {
        return songPlayer.getCurrentTime();
    }

    /**
     * Permite saber a otras partes del programas la duración de la canción actual
     * @return duración de la canción actual
     */
    public static double getEndTime () {
        return songPlayer.getEndTime();
    }
}

