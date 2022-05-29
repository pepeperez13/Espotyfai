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
        this.songPlaylistDAO = new SQLConnectorSongPlaylist();
    }

    public LinkedList<Playlist> getDataPlaylists() {
        return playlistDAO.SelectDataPlaylist();
    }


    public LinkedList<Playlist> getPlaylistsOfUser(User user) {
        return playlistDAO.SelectPlaylistsOfUser(user);
    }

    /**
     * Metodo que se utiliza para eliminar una playlist.
     * @param name
     */
    public void deletePlaylist(String name) {
        playlistDAO = new SQLConnectorPlaylist();
        playlistDAO.DeleteDataPlaylist(name);
        //songPlaylistDAO.DeleteDataSongP(name);
    }

    /**
     * Metodo que se utiliza para crear una nueva playlisy.
     * @param name
     * @param owner
     * @throws Exception
     */
    public void createPlaylist(String name, String owner) throws Exception{
        playlistDAO.InsertDataPlaylist(name, owner);
    }

    /**
     * Metodo que comprueba si existe una playlist.
     * @param user
     * @param nombrePlaylist
     * @return
     */
    public boolean existPlaylist(User user, String nombrePlaylist) {
        playlistDAO = new SQLConnectorPlaylist();
        LinkedList<Playlist> playlists = playlistDAO.SelectPlaylistsOfUser(user);
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(nombrePlaylist)) {
                return true;
            }
        }
        return false;
    }

}

