package persistance.dao.sql;

import business.entities.Playlist;

import business.entities.User;
import persistance.PlaylistDAO;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorPlaylist implements PlaylistDAO {

    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;

    public void InsertDataPlaylist(String name, String owner) {

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
            ex.printStackTrace();
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





    public LinkedList<Playlist> SelectDataPlaylist(){
        LinkedList<Playlist> playlists = new LinkedList<>();
        String name;
        String owner;

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM playlist";
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
