package persistance.dao.sql;

import business.entities.Playlist;

import business.entities.Song;
import business.entities.SongPlaylist;
import business.entities.User;
import persistance.PlaylistDAO;
import persistance.SongDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que contiene todos los metodos para acceder y modificar la información de la base de datos de Playlists
 */
public class SQLConnectorPlaylist implements PlaylistDAO {

    private static SongDAO songDAO = new SQLConnectorSong();
    private static String dbURL = songDAO.GetDataBaseData().getDataBaseIP();
    private static String username = songDAO.GetDataBaseData().getUserName();
    private static String password = songDAO.GetDataBaseData().getPassword();

    /**
     * Metodo que se encarga de insertar en la base de datos los datos de una playlist.
     * @param name nombre de la playlist
     * @param owner dueño de la cancion
     */
    public void InsertDataPlaylist(String name, String owner) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql para inertar datos en la tabla playlist
            String sql = "INSERT INTO playlist (PLAYLIST_NAME,PLAYLIST_OWNER) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, owner);

            int rowsInserted = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Metodo que se encarga de insertar en la base de datos los datos de una cancion.
     * @param name nombre de la playlist
     */
    public void DeleteDataPlaylist(String name){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement SQL que elimina datos de la tabla song_playlisy dependiendo del nombre de la playlist.
            String sqlDeleteRelationships = "DELETE FROM song_playlist WHERE PLAYLIST_NAME= ?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1,name);
            stDelRelationship.executeUpdate();
            //Generacion de un statement sql para eliminar datos de la tabla playlisy dependeindeo del nombre de la playlist.
            String sql = "DELETE FROM playlist WHERE PLAYLIST_NAME=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A playlist was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que se encarga de seleccionar en la base de datos los datos de una playlist de un usuario.
     * @param user usuario
     * @return playlists linked list llenada con las playlist de un usuario
     */
    public LinkedList<Playlist> SelectPlaylistsOfUser(User user)
    {
        //Creacion de una linked list de playlists
        LinkedList<Playlist> playlists = new LinkedList<>();
        String owner = user.getName();
        String playlistname = "";
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql que selecciona de la tabla playlist dependiendo del owner
            String sql = "SELECT * FROM PLAYLIST WHERE PLAYLIST_OWNER = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,owner);
            ResultSet rs = statement.executeQuery();
            // obtengo todas las playlists pero sin canciones
            while (rs.next())
            {
                playlistname = rs.getString("PLAYLIST_NAME");
                owner = rs.getString("PLAYLIST_OWNER");
                Playlist newPlaylist = new Playlist(playlistname, owner);
                playlists.add(newPlaylist);
                // print the results
                System.out.format("%s, %s\n", playlistname,owner);
            }
            statement.close();

            // para cada playlist obtenida, recupero sus canciones
            SQLConnectorSong sqlSong = new SQLConnectorSong();
            for(Playlist p: playlists){
                ArrayList<Song> songs= new ArrayList<>();
                p.setSongs(songs);
                sql = "SELECT * FROM song_playlist WHERE PLAYLIST_NAME = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1,p.getName());
                rs = statement.executeQuery();
                while (rs.next())
                {
                    String song_title = rs.getString("SONG_TITLE");
                    int song_pos = rs.getInt("POS");

                    Song song = sqlSong.SelectSong(song_title);
                    song.setPosition(song_pos);
                    p.getSongs().add(song);
                }
                statement.close();

            }

            return playlists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que se encarga de seleccionar en la base de datos los datos de una playlist.
     * @return playlists linked list con todos los datos de una playlist
     */
    public LinkedList<Playlist> SelectDataPlaylist(){
        //Creacion de una linked list de playlist
        LinkedList<Playlist> playlists = new LinkedList<>();
        String name;
        String owner;
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generacion de un statement sql que selecciona de la tabla playlist.
            String sql = "SELECT * FROM PLAYLIST";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            //bucle que rellena la linked list
            while (rs.next())
            {
                name = rs.getString("PLAYLIST_NAME");
                owner = rs.getString("PLAYLIST_OWNER");
                Playlist newPlaylist = new Playlist(name, owner);
                playlists.add(newPlaylist);
                // print the results
                System.out.format("%s, %s\n", name,owner);
            }
            statement.close();
            // para cada playlist obtenida, recupero sus canciones
            SQLConnectorSong sqlSong = new SQLConnectorSong();

            for(Playlist p: playlists){
                ArrayList<Song> songs= new ArrayList<>();
                p.setSongs(songs);

                sql = "SELECT * FROM song_playlist WHERE PLAYLIST_NAME = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1,p.getName());
                rs = statement.executeQuery();
                while (rs.next())
                {
                    String song_title = rs.getString("SONG_TITLE");
                    int song_pos = rs.getInt("POS");

                    Song song = sqlSong.SelectSong(song_title);
                    song.setPosition(song_pos);
                    p.getSongs().add(song);
                }

                statement.close();
            }
            return playlists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
