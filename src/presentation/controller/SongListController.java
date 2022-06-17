package presentation.controller;

import business.Owner;
import business.PlaylistManager;
import business.SongManager;
import business.SongPlaylistManager;
import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import com.sun.tools.javac.Main;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;
import presentation.view.BottomBarPanel;
import presentation.view.MainManagerView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Clase que controla la vista del SongListView
 */
public class SongListController implements ActionListener {

    private SongPlaylistManager songPlaylistManager = new SongPlaylistManager();
    private SongListlView songListlView;
    private MainManagerView mainManagerView;
    private static PlaylistManager playlistManager = new PlaylistManager();
    private static Playlist parameterPlayList = new Playlist();
    private static boolean reproducingPlaylist;
    public SongListController(SongListlView songListlView, MainManagerView mainManagerView) {
        this.songListlView = songListlView;
        this.mainManagerView = mainManagerView;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(SongListRender.DELETE_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("SONG_ELIMINAR");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            try {
                songPlaylistManager.deleteSongPlaylistSong(playlist.getName(), song.getTitle());
                playlist.getSongs().remove(song);
                this.songListlView.loadSongs();
            } catch (Exception ex) {

            }

        } else if (e.getActionCommand().equals(SongListRender.UP_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_subir");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            try {
                int pos = song.getPosition();
                if (pos > 1) {
                    songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 1);

                    for (Song s : playlist.getSongs()) {
                        if (s.getPosition() == pos - 1) {
                            s.setPosition(s.getPosition() + 1);
                        }
                    }
                    song.setPosition(pos - 1);
                }
                this.songListlView.loadSongs();
            } catch (Exception ex) {

            }
        } else if (e.getActionCommand().equals(SongListRender.DOWN_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_bajar");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");

            try {

                int pos = song.getPosition();
                if (pos < playlist.getSongs().size()) {
                    songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 2);
                    for (Song s : playlist.getSongs()) {
                        if (s.getPosition() == pos + 1) {
                            s.setPosition(s.getPosition() - 1);
                        }
                    }
                    song.setPosition(pos + 1);
                }
                this.songListlView.loadSongs();
            } catch (Exception ex) {

            }

        } else if (e.getActionCommand().equals(SongListlView.ADDSONG)) {
            Song song = this.songListlView.getSelectedSongToAdd();
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            if (song != null && playlist != null && !songPlaylistManager.songExistsInPlaylist(song.getTitle(), playlist.getName())) {
                try {
                    songPlaylistManager.InsertNewSongPlaylist(song.getTitle(), playlist.getName());
                    //SongListlView.selectedPlaylist.getSongs().add(song);
                    songListlView.getSelectedPlaylist().getSongs().add(song);
                    this.songListlView.loadSongs();
                } catch (Exception ex) {
                    this.songListlView.showErrorSongAdd();
                }
            } else {
                this.songListlView.showErrorSongAdd();
            }
        } else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST");
            //SongListlView.selectedPlaylist = playlist;
            songListlView.setPlayList(playlist);
            //SongListlView.allSongs = songManager.ListSongs();
            songListlView.setAllSongs(SongManager.ListSongs());
            mainManagerView.changeView(12, 1);
        } else if (e.getActionCommand().equals(PlayListRender.REPRODUCIR_BUTTON)) {
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("PLAYLIST_REPRODUCIR");

            if (playlist.getSongs().size() > 0) {
                parameterPlayList = playlist;
                SongListController.reproducingPlaylist = true;
                BottomBarPanel.updateSong(playlist.getSongs().get(0));
                SongPlayerController.playPlaylist();
                SongPlayerController.setRepeatPlaylist(false);
            }

        }
    }
    /**
     * Metodo que te devuelve todas las playlists del usuario
     * @return playlists del usuario
     */
    public static LinkedList<Playlist> getPlaylistsOfUser () {
        return playlistManager.getPlaylistsOfUser(Owner.getUser());
    }

    /**
     * Metodo que te devuelve todas las playlists
     * @return todas las playlists
     */
    public static LinkedList<Playlist> getAllPlaylists () {
        return playlistManager.getDataPlaylists();
    }
    /**
     * Método que retorna si está reproduciendo una playlist
     * @return True si se está reproduciendo una playlist
     */
    public static boolean isReproducingPlaylist() {
        return MainViewController.reproducingPlaylist;
    }

    /**
     *Método que recibe un boolean que indica si se esta reproduciendo una playlist
     * @return void
     */
    public static void setReproducingPlaylist (boolean playing) {
        MainViewController.reproducingPlaylist = playing;
    }

    /**
     * Método que indica que playlist que se está reproduciendo
     * @return Playlist Playlist que se esta reproduciendo
     */
    public static Playlist getReproducingPlaylist() {
        return parameterPlayList;
    }
}
