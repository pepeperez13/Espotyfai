package persistance.dao.sql;

import business.entities.Song;
import business.entities.SongPlaylist;
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



            }

            statement.close();
            return songsP;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }



    }
    //Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
    public LinkedList<SongPlaylist> SelectSongsP(String pName){
        LinkedList<SongPlaylist> songP = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM song_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            String title = null;
            while (rs.next()) {

                title = rs.getString("SONG_TITLE");
                String pNameSQL = rs.getString("PLAYLIST_NAME");


                if (pNameSQL.equals(pName)) {
                    SongPlaylist newSongP = new SongPlaylist(title, pName);
                    songP.add(newSongP);
                    /*System.out.println("bien");
                    System.out.println(songP);*/
                }else{
                    //System.out.println("Mal");
                }

            }
            statement.close();
            return songP;
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



