package persistance.dao.sql;

import business.entities.User;
import persistance.SongDAO;
import persistance.UserDAO;

import java.sql.*;
import java.util.LinkedList;

/**
 * Clase que contiene todos los metodos para acceder y modificar la información de la base de datos de User
 */
public class SQLConnectorUser implements UserDAO {
    private static SongDAO songDAO = new SQLConnectorSong();
    private static String dbURL = songDAO.GetDataBaseData().getDataBaseIP();
    private static String username = songDAO.GetDataBaseData().getUserName();
    private static String password = songDAO.GetDataBaseData().getPassword();

    /**
     * Metodo que se emplea para insertar en la base de datos los datos de un usuario.
     * @param user username
     * @param email email del usuario
     * @param pass password del usuario
     */
    public void InsertDataUser(String user, String email, String pass) {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos una sentencia sql para insertar en la tabla user sus parametros
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


    /**
     * Metodo que se emplea para eliminar los datos de un usuario.
     * @param name nombre del usuario
     */
    public void DeleteDataUser(String name){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            //Generamos un statement sql para eliminar dependiendo del username
            String sql = "DELETE FROM user WHERE USER_NAME = ?";

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

    /**
     * Metodo que se utiliza para seleccionar obtener los datos de todos los usuarios.
     * @return users Linked list con los datos del usuario
     */
    public LinkedList<User> SelectDataUser(){
        String user, emails, pass;
        //Creamos linkedlists de user
        LinkedList<User> users = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos statement sql para seleccionar de la tabla user.
            String sql = "SELECT * FROM user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            //blucle para ir llenando la linked list con la informacion extraida de la base de datos
            while (rs.next())
            {
                user = rs.getString("USER_NAME");
                emails = rs.getString("USER_EMAIL");
                pass = rs.getString("USER_PASSWORD");
                User newUser = new User(user, emails, pass);
                users.add(newUser);
            }
            statement.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
