package presentation.controller;

import business.PlaylistManager;
import business.entities.Playlist;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import presentation.view.detailedSong.DetailedSongView;
import presentation.view.detailedSong.ShowPlaylistsFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class DetailedSongController implements ActionListener {

    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;
    private PlaylistDAO playlistDAO;

    public DetailedSongController(DetailedSongView detailedSongView) {
        this.detailedSongView = detailedSongView;
        this.playlistDAO = new SQLConnectorPlaylist();
        this.playlistManager = new PlaylistManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_TO_PLAYLIST_COMMAND")); {
            System.out.println("HOLAAAAAAA");
            ShowPlaylistsFrame showPlaylistsFrame = new ShowPlaylistsFrame(detailedSongView);
            showPlaylistsFrame.setVisible(true);
        }
    }

    public LinkedList<Playlist> getDataPlaylists () {
        return playlistManager.getDataPlaylists();
    }
}
