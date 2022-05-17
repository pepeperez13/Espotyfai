package business;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;

import java.util.LinkedList;

public class PlaylistManager {

    private PlaylistDAO playlistDAO;

    public PlaylistManager() {
        this.playlistDAO = new SQLConnectorPlaylist();
    }

    public LinkedList<Playlist> getDataPlaylists () {
        return playlistDAO.SelectDataPlaylist();
    }

    public LinkedList<Playlist> getPlaylistsOfUser(User user){
        return playlistDAO.SelectPlaylistsOfUser(user);
    }

    public boolean adSongToPlaylist (String songName, String playlistName){
        return true;
    }
}
