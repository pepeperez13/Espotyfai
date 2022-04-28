package persistance.dao.sql;

import business.entities.User;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnector {


    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;

    public void InsertDataUser(String user, String email, String pass) {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO user (USER_NAME,USER_EMAIL,USER_PASSWORD) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, email);
            statement.setString(3, pass);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void InserDataArtist() {


        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO artist (ARTIST_NAME) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "billY JEAN");


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Artist was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void InsertDataPlaylist() {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO playlist (PLAYLIST_NAME,PLAYLIST_OWNER) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "ChillHouse");
            statement.setString(2, "billy");



            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new playlist was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void InsertDataSong() {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO song (SONG_TITLE,SONG_GENRE,SONG_ALBUM,SONG_ARTIST,SONG_PATH,SONG_OWNER) VALUES (?, ?, ?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Euphoria");
            statement.setString(2, "Techno");
            statement.setString(3, "Euphoria Attract");
            statement.setString(4, "David el jeta");
            statement.setString(5, "c/users/borja/desktop/music");
            statement.setString(6, "Billy");


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void UpdateDataArtist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE artist SET ARTIST_NAME=? WHERE ARTIST_NAME= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Sarah");
            statement.setString(2, "bill");


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Artist was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void UpdateDataPlaylist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE playlist SET PLAYLIST_NAME=?,PLAYLIST_OWNER=? WHERE PLAYLIST_NAME= ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "TechHouse");
            statement.setString(2, "Bill");
            statement.setString(3, "ChillHouse");



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing playlist was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void UpdateDataSong(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE song SET SONG_TITLE=?,SONG_GENRE=?,SONG_ALBUM = ?,SONG_ARTIST = ?,SONG_PATH = ?,SONG_OWNER = ? WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Boom");
            statement.setString(2, "Dance");
            statement.setString(3, "BOOM BOOM");
            statement.setString(4, "Don Omar");
            statement.setString(5, "/b/fg/dfgg");
            statement.setString(6, "me");
            statement.setString(7, "Euphoria");


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing song was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void UpdateDataUser(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE user SET USER_NAME=?,USER_EMAIL=?,USER_PASSWORD = ? WHERE USER_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Elpepe");
            statement.setString(2, "elpepe@gmail.com");
            statement.setString(3, "BOOni");
            statement.setString(4, "bill");



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void DeleteDataArtist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM artist WHERE ARTIST_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "billY JEAN");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A artist was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void DeleteDataPlaylist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM playlist WHERE PLAYLIST_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "billY JEAN");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A playlist was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void DeleteDataSong(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM song WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "billY JEAN");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A song was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void DeleteDataUser(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM user WHERE USER_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Elpepe");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public LinkedList<User> SelectDataUser(){
        String user, emails, pass;
        LinkedList<User> users = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                user = rs.getString("USER_NAME");
                emails = rs.getString("USER_EMAIL");
                pass = rs.getString("USER_PASSWORD");
                User newUser = new User(user, emails, pass);
                users.add(newUser);

                // print the results
                System.out.format("%s, %s, %s\n", user,emails,pass);
            }
            statement.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void SelectDataSong(){
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


                // print the results
                System.out.format("%s, %s, %s, %s, %s, %s\n", title,genre,album,artist,path,owner);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public static void SelectDataPlaylist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String pname = rs.getString("PLAYLIST_NAME");
                String powner = rs.getString("PLAYLIST_OWNER");



                // print the results
                System.out.format("%s, %s\n", pname,powner);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public static void SelectDataArtist(){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM artist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                String partist = rs.getString("ARTIST_NAME");



                // print the results
                System.out.format("%s\n", partist);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
