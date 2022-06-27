package business;

import business.entities.Playlist;
import business.entities.User;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;

/**
 * Clase que gestiona las playlist
 */
public class PlaylistManager {

    private static SQLConnectorPlaylist playlistDAO;
    private static SQLConnectorSongPlaylist songPlaylistDAO;

    /**
     * Constructor. Inicia los DAOs
     */
    public PlaylistManager() {
        this.playlistDAO = new SQLConnectorPlaylist();
        this.songPlaylistDAO = new SQLConnectorSongPlaylist();
    }

    /**
     * Método que retorna una lista de playlist
     * @return LinkedList Lista de playlist
     */
    public LinkedList<Playlist> getDataPlaylists() {
        return playlistDAO.SelectDataPlaylist();
    }

    /**
     * Método que retorna una lista de playlist de un usuario en concreto
     * @param user Nombre del usuario
     * @return LinkedList Lista de playlist
     */
    public LinkedList<Playlist> getPlaylistsOfUser(User user) {
        return playlistDAO.SelectPlaylistsOfUser(user);
    }

    /**
     * Metodo que se utiliza para eliminar una playlist.
     * @param name Nombre de la playlist
     */
    public void deletePlaylist(String name) {
        playlistDAO = new SQLConnectorPlaylist();
        playlistDAO.DeleteDataPlaylist(name);
    }

    /**
     * Metodo que se utiliza para crear una nueva playlist
     * @param name  Nombre de la playlist
     * @param owner Nombre del usuario que ha creado la playlist
     */
    public void createPlaylist(String name, String owner) {
        playlistDAO.InsertDataPlaylist(name, owner);
    }
}

