package persistance.dao.sql;

import java.sql.Connection;

import persistance.ArtistDAO;
import business.entities.Artist;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorArtist implements ArtistDAO {

    private static String dbURL = "jdbc:mysql://localhost:3306/espotifay";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;

    public void InsertDataArtist(String name) {


        try (Connection conn = DriverManager.getConnection(SQLConfig.dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "INSERT INTO artist (ARTIST_NAME) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Artist was inserted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void DeleteDataArtist() {

    }

    public void UpdateDataArtist(String name1, String name2){
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
    public LinkedList<Artist> SelectDataArtist(){

        String art;
        LinkedList<Artist> artists = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM artist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                art = rs.getString("USER_NAME");
                Artist newArtist = new Artist(art);
                artists.add(newArtist);

                // print the results
                System.out.format("%s\n", art);
            }
            statement.close();
            return artists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
    public static void DeleteDataArtist(String artname){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM artist WHERE ARTIST_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, artname);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The artist was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

