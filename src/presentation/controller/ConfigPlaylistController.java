package presentation.controller;

import business.entities.Playlist;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;
import presentation.view.ManageAccountView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPlaylistController implements ActionListener {
    private PlayListRender playListRender;
    private SongListRender songListRender;
    private SongListlView songListlView;

    public ConfigPlaylistController() {

    }

    public void setSongListlView(SongListlView songListlView) {
        this.songListlView = songListlView;
    }

    public void setPlayListRender(PlayListRender playListRender) {
        this.playListRender = playListRender;
    }

    public void setSongListRender(SongListRender songListRender) {
        this.songListRender = songListRender;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case PlayListRender.EDIT_BUTTON:
                Playlist parameterPlayList = (Playlist) ((JButton)e.getSource()).getClientProperty( "PLAYLIST" );
                songListlView.loadSongs(parameterPlayList.getSongs());
                break;

            case PlayListRender.DELETE_BUTTON:

                break;

            case SongListRender.DELETE_BUTTON:

                break;

            case SongListRender.UP_BUTTON:
                break;

            case SongListRender.DOWN_BUTTON:
                break;

        }
    }
}
