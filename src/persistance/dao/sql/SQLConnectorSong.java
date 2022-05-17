package persistance.dao.sql;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;
import persistance.SongDAO;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorSong implements SongDAO {
    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;




    public void InsertDataSong(String title, String genre, String album, String artist, String path, String owner) {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO song (SONG_TITLE,SONG_GENRE,SONG_ALBUM,SONG_ARTIST,SONG_PATH,SONG_OWNER) VALUES (?, ?, ?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, album);
            statement.setString(4, artist);
            statement.setString(5, path);
            statement.setString(6, owner);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void UpdateDataSong(String title1,String genre,String album, String artist,String path,String owner,String title2){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE song SET SONG_TITLE=?,SONG_GENRE=?,SONG_ALBUM = ?,SONG_ARTIST = ?,SONG_PATH = ?,SONG_OWNER = ? WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title1);
            statement.setString(2, genre);
            statement.setString(3, album);
            statement.setString(4, artist);
            statement.setString(5, path);
            statement.setString(6, owner);
            statement.setString(7, title2);


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing song was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteDataSong(String title){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM song WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A song was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public LinkedList<Song> SelectDataSong(){
        LinkedList<Song> songs = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM song";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String title = rs.getString("SONG_TITLE");
                String genre = rs.getString("SONG_GENRE");
                String album = rs.getString("SONG_ALBUM");
                String artist = rs.getString("SONG_ARTIST");
                String path = rs.getString("SONG_PATH");
                String owner = rs.getString("SONG_OWNER");

                Song newSong = new Song(title, genre, album,artist,path,owner);
                songs.add(newSong);

                // print the results
                System.out.format("%s, %s, %s, %s, %s, %s\n", title,genre,album,artist,path,owner);
            }

            statement.close();
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }



    }
    //Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
    public LinkedList<Song> SelectSong(String name){
        LinkedList<Song> song = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM song";
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


