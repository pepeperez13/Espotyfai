package presentation.controller;

import business.SongManager;
import business.SongPlaylistManager;
import business.entities.Playlist;
import business.entities.Song;
import presentation.render.SongListRender;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SongListController implements ActionListener {

    private SongPlaylistManager songPlaylistManager;

    public SongListController() {
        this.songPlaylistManager = new SongPlaylistManager();

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
            songPlaylistManager.deleteSongPlaylistSong(playlist.getName(), song.getTitle());
        }else if (e.getActionCommand().equals(SongListRender.UP_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_subir");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 1);
        } else if (e.getActionCommand().equals(SongListRender.DOWN_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_bajar");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 2);
        }else if(e.getActionCommand().equals(SongListlView.ADDSONG)){
            //LinkedList<Song> songs= SongManager.ListSongs();
            //String nameCancion=SongListlView.showMessageAddSong(songs);
            //songPlaylistManager.InsertNewSongPlaylist(nameCancion,parameterPlayList.getName());
        }
    }
}
