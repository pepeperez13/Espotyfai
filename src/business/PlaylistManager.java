package business;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import persistance.PlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;

public class PlaylistManager {

    private static SQLConnectorPlaylist playlistDAO;
    private static SQLConnectorSongPlaylist songPlaylistDAO;


    public PlaylistManager() {
        this.playlistDAO = new SQLConnectorPlaylist();
    }

    public LinkedList<Playlist> getDataPlaylists () {
        return playlistDAO.SelectDataPlaylist();
    }

    public LinkedList<Playlist> getPlaylistsOfUser(User user){
        return playlistDAO.SelectPlaylistsOfUser(user);
    }


    public static void DeletePlaylist(String name){
        playlistDAO = new SQLConnectorPlaylist();
        playlistDAO.DeleteDataPlaylist(name);
        songPlaylistDAO.DeleteDataSongP(name);
    }
    public static void CreatePlaylist(String name,String owner){
        playlistDAO.InsertDataPlaylist(name,owner);
    }


}
