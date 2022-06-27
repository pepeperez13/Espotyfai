package presentation.controller;

import business.SongPlaylistManager;
import business.entities.Playlist;
import business.entities.Song;
import presentation.render.SongListRender;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla la vista del SongListView
 */
public class SongListController implements ActionListener {

    private SongPlaylistManager songPlaylistManager;
    private SongListlView songListlView;

    public SongListController(SongListlView songListlView) {
        this.songPlaylistManager = new SongPlaylistManager();
        this.songListlView = songListlView;
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
            playlist.getSongs().remove(song);
            this.songListlView.loadSongs();

        }else if (e.getActionCommand().equals(SongListRender.UP_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_subir");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            int pos = song.getPosition();
            if(pos>1){
                songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 1);

                for(Song s: playlist.getSongs()){
                    if(s.getPosition() == pos-1){
                        s.setPosition(s.getPosition()+1);
                    }
                }
                song.setPosition(pos-1);
            }
                this.songListlView.loadSongs();
        } else if (e.getActionCommand().equals(SongListRender.DOWN_BUTTON)) {
            Song song = (Song) ((JButton) e.getSource()).getClientProperty("song_bajar");
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");

            int pos = song.getPosition();
            if(pos<playlist.getSongs().size()){
                songPlaylistManager.updatePosP(song.getTitle(), playlist.getName(), 2);
                for(Song s: playlist.getSongs()){
                    if(s.getPosition() == pos+1){
                        s.setPosition(s.getPosition()-1);
                    }
                }
                song.setPosition(pos+1);
            }
            this.songListlView.loadSongs();

        }else if(e.getActionCommand().equals(SongListlView.ADDSONG)){
            Song song = this.songListlView.getSelectedSongToAdd();
            Playlist playlist = (Playlist) ((JButton) e.getSource()).getClientProperty("playlist");
            if(song!= null && playlist != null && !songPlaylistManager.songExistsInPlaylist(song.getTitle(), playlist.getName())){
                songPlaylistManager.InsertNewSongPlaylist(song.getTitle(),playlist.getName());
                SongListlView.getSelectedPlaylist().getSongs().add(song);
                this.songListlView.loadSongs();
            } else {
                this.songListlView.showErrorSongAdd();
            }
        }
    }
}
