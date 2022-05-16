package business;

import business.entities.Playlist;
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
}
