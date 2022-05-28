package persistance.dao.sql;

import business.entities.Playlist;

import business.entities.Song;
import business.entities.SongPlaylist;
import business.entities.User;
import persistance.PlaylistDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class SQLConnectorPlaylist implements PlaylistDAO {

    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;

    public void InsertDataPlaylist(String name, String owner) throws Exception {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO playlist (PLAYLIST_NAME,PLAYLIST_OWNER) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, owner);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new playlist was inserted successfully!");
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void UpdateDataPlaylist(String name1,String owner, String name2){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE playlist SET PLAYLIST_NAME=?,PLAYLIST_OWNER=? WHERE PLAYLIST_NAME= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setString(2, owner);
            statement.setString(3, name2);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing playlist was updated successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteDataPlaylist(String name){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");

            String sqlDeleteRelationships = "DELETE FROM song_playlist WHERE PLAYLIST_NAME= ?";
            PreparedStatement stDelRelationship = conn.prepareStatement(sqlDeleteRelationships);
            stDelRelationship.setString(1,name);
            stDelRelationship.executeUpdate();

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


    public LinkedList<Playlist> SelectPlaylistsOfUser(User user)
    {
        LinkedList<Playlist> playlists = new LinkedList<>();
        String owner = user.getName();
        String playlistname = "";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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

    public LinkedList<Playlist> SelectDataPlaylist(){
        LinkedList<Playlist> playlists = new LinkedList<>();
        String name;
        String owner;

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM PLAYLIST";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
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

    /**
     * Method that closes the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }
}
