package presentation.controller;

import business.PlaylistManager;
import business.SongPlaylistManager;
import business.Store;
import presentation.view.DetailedSongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlaylistsController implements ActionListener {

    private DetailedSongView detailedSongView;
    private PlaylistManager playlistManager;
    private SongPlaylistManager songPlaylistManager;



    public ShowPlaylistsController ( DetailedSongView detailedSongView) {
        this.playlistManager = new PlaylistManager();
        this.songPlaylistManager = new SongPlaylistManager();
        this.detailedSongView = detailedSongView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (songPlaylistManager.songExistsInPlaylist(detailedSongView.getSongTitle(), e.getActionCommand())) {
            detailedSongView.showErrorMessage();
        }else{
            if(songPlaylistManager.InsertNewSongPlaylist(detailedSongView.getSongTitle(), e.getActionCommand())){
                detailedSongView.showOKMessage();
            }else{
                detailedSongView.showErrorUserMessage();
            }


        }
    }
}
