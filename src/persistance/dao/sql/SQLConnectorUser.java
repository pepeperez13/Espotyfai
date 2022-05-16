package persistance.dao.sql;

import business.entities.User;
import persistance.UserDAO;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorUser implements UserDAO {
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







    public void UpdateDataUser(String name1, String email,String password,String name2){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE user SET USER_NAME=?,USER_EMAIL=?,USER_PASSWORD = ? WHERE USER_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name1);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, name2);



            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }





    public void DeleteDataUser(String name){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM user WHERE USER_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

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
