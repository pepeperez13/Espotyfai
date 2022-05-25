package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.SongPlaylistManager;
import business.Store;
import business.entities.Playlist;
import business.entities.Song;
import persistance.PlaylistDAO;
import presentation.render.PlayListRender;
import presentation.render.SongListRender;
import presentation.view.MainManagerView;
import presentation.view.MainMenu;
import presentation.view.PlaylistView;
import presentation.view.SongListlView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainViewController implements ActionListener {
    private MainMenu mainMenu;
    private MainManagerView mainManagerView;
    private PlaylistManager playlistManager;
    private SongPlaylistManager songManager;
    Playlist parameterPlayList= new Playlist();
    Song song= new Song();


    public MainViewController (MainMenu menuBarView, MainManagerView mainManagerView) {
        this.mainMenu = menuBarView;
        this.mainManagerView = mainManagerView;
        this.playlistManager = new PlaylistManager();
        this.songManager=new SongPlaylistManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainMenu.GO_INICIO)) {
            mainManagerView.changeView(1, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_BUSCADOR)) {
            mainManagerView.changeView(2, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_MISLISTAS)) {
            mainManagerView.changeView(3, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_SETTINGS)) {
            mainManagerView.changeView(6, 2);
        } else if (e.getActionCommand().equals(PlayListRender.EDIT_BUTTON)){
             parameterPlayList = (Playlist) ((JButton)e.getSource()).getClientProperty( "PLAYLIST" );
            SongListlView.selectedPlaylist = parameterPlayList;
            mainManagerView.changeView(12, 1);
        }else if(e.getActionCommand().equals(PlaylistView.CREAR_PLAYLIST)){
            String nombrePlaylist=PlaylistView.crearPlaylist();
            if(playlistManager.existPlaylist(Store.getUser(),nombrePlaylist)){
                PlaylistView.showErrorPlaylistCreation();
            }else{
                playlistManager.createPlaylist(nombrePlaylist, Store.getUser().getName());

            }

        }else if(e.getActionCommand().equals(PlayListRender.DELETE_BUTTON)){
             parameterPlayList = (Playlist) ((JButton)e.getSource()).getClientProperty( "PLAYLIST_ELIMINAR" );
            playlistManager.deletePlaylist(parameterPlayList.getName());
        }else if(e.getActionCommand().equals(SongListRender.DELETE_BUTTON)){
            song=(Song) ((JButton)e.getSource()).getClientProperty( "SONG_ELIMINAR" );
            songManager.deleteNewSongPlaylist(song.getTitle());
        }
    }

}
