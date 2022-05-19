package persistance.dao.sql;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.SongPlaylist;
import business.entities.User;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorSongPlaylist implements SongPlaylistDAO {
    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;




    public void InsertDataSongP(String title, String name) {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO song_playlist (SONG_TITLE,PLAYLIST_NAME) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, name);



            int rowsInserted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void UpdateDataSongP(String title1,String name,String title2){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE song_playlist SET SONG_TITLE=?,PLAYLIST_NAME=? WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title1);
            statement.setString(2, name);
            statement.setString(3, title2);


            int rowsUpdated = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteDataSongP(String title){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM song_playlist WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public LinkedList<SongPlaylist> SelectDataSongP(){
        LinkedList<SongPlaylist> songsP = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM songs_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String title = rs.getString("SONG_TITLE");
                String name = rs.getString("PLAYLIST_NAME");


                SongPlaylist newSong = new SongPlaylist(title, name);
                songsP.add(newSong);

                // print the results
                System.out.format("%s, %s\n", title,name);
            }

            statement.close();
            return songsP;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }



    }
    //Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
    public LinkedList<Song> SelectSongP(String name){
        LinkedList<Song> song = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM song_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            String title = null;
            while (rs.next()) {

                title = rs.getString("SONG_TITLE");
                String genre = rs.getString("SONG_GENRE");
                String album = rs.getString("SONG_ALBUM");
                String artist = rs.getString("SONG_ARTIST");
                String path = rs.getString("SONG_PATH");
                String owner = rs.getString("SONG_OWNER");
                System.out.println(name);
                System.out.println(title);
                if (title.equals(name)) {
                    Song newSong = new Song(title, genre, album, artist, path, owner);
                    song.add(newSong);


                    System.out.format("%s, %s, %s, %s, %s, %s\n", title, genre, album, artist, path, owner);
                }else{
                    System.out.println("Mal");
                }

            }
            statement.close();
            return song;
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



