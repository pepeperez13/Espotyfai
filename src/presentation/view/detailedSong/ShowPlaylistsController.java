package presentation.view.detailedSong;

import business.PlaylistManager;
import business.SongPlaylistManager;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlaylistsController implements ActionListener {

    private ShowPlaylistsFrame showPlaylistsFrame;
    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;
    private SongPlaylistManager songPlaylistManager;



    public ShowPlaylistsController (ShowPlaylistsFrame showPlaylistsFrame, DetailedSongView detailedSongView) {
        this.showPlaylistsFrame = showPlaylistsFrame;
        this.playlistManager = new PlaylistManager();
        this.songPlaylistManager = new SongPlaylistManager();
        this.detailedSongView = detailedSongView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (songPlaylistManager.songExistsInPlaylist(detailedSongView.getSongTitle(), e.getActionCommand())) {
            detailedSongView.showErrorMessage();
        }else{
            SongPlaylistManager.InsertNewSongPlaylist(detailedSongView.getSongTitle(), e.getActionCommand());
        }
        showPlaylistsFrame.dispose();
    }
}
