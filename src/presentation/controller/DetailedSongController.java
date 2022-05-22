package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.entities.Playlist;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class DetailedSongController implements ActionListener {

    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;
    private PlaylistDAO playlistDAO;
    private SongManager songManager;

    public DetailedSongController(DetailedSongView detailedSongView) {
        this.detailedSongView = detailedSongView;
        this.playlistDAO = new SQLConnectorPlaylist();
        this.playlistManager = new PlaylistManager();
        this.songManager = new SongManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_TO_PLAYLIST_COMMAND")) {
            //ShowPlaylistsFrame showPlaylistsFrame = new ShowPlaylistsFrame(detailedSongView);
            //showPlaylistsFrame.setVisible(true);
            detailedSongView.showPlaylists();
        }
        if (e.getActionCommand().equals("CLOSE_PANEL_COMMAND")) {
            //detailedSongView.dispose();
        }
        if (e.getActionCommand().equals("PLAY_SONG_COMMAND")) {
            System.out.println("PLAY");
            //SongManager.PlayMusic(detailedSongView.getPath(), 1);
        }
        if (e.getActionCommand().equals("PAUSE_SONG_COMMAND")) {
            System.out.println("PAUSE");
        }
    }

    public LinkedList<Playlist> getDataPlaylists () {
        return playlistManager.getDataPlaylists();
    }
}
