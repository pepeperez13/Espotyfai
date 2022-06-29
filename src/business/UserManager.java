package business;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import persistance.PlaylistDAO;
import persistance.SongDAO;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSong;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * Clase que gestiona las interaccion de las usuarios con la base de datos
 */
public class UserManager {
    private UserDAO sql;
    private PlaylistDAO sqlP;
    private SongDAO sqlS;

    /**
     * Constructor de la clase
     * @param sql dao de los usuarios
     */
    public UserManager (UserDAO sql) {
        this.sql = sql;
        this.sqlP= new SQLConnectorPlaylist();
        this.sqlS= new SQLConnectorSong();
    }

    /**
     * Método que introduce un usuario a la base de datos
     * @param name Nombre del usuario
     * @param email E-mail del usuario
     * @param password Contraseña del usuario
     */
    public void insertNewUser (String name, String email, String password) {
        byte[] psw = password.getBytes();
        byte[] hash = null;

        // Convertimos la contrasena mediante el algoritmo MD5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(psw);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder strBuilder = new StringBuilder();

        for(byte b:hash) {
            strBuilder.append(String.format("%02x", b));
        }
        password = strBuilder.toString();
        System.out.println("La contrasena con el hash es: " + password);
        sql.InsertDataUser(name, email, password);
    }

    /**
     * Método que checkea la contraseña de un usuario que quiere iniciar sesion
     * @param password Contraseña introducida
     * @param name Nombre del usuario
     * @return boolean Indica si es correcto o no
     */
    public boolean checkCorrectPassword (String password, String name) {
        boolean correct = false;
        User userAux = new User();
        LinkedList<User> users = sql.SelectDataUser();

        byte[] psw = password.getBytes();
        byte[] hash = null;

        // Convertimos la contraseña mediante el algoritmo MD5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(psw);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder strBuilder = new StringBuilder();

        for(byte b:hash) {
            strBuilder.append(String.format("%02x", b));
        }
        password = strBuilder.toString();

        for (User user : users) {
            if (user.getName().equals(name)) {
                userAux = user;
            }
        }
        System.out.println(userAux.getPassword());
        System.out.println(password);
        correct = userAux.getPassword().equals(password);
        return correct;
    }

    /**
     * Metodo que checkea si el usuario existe
     * @param name Nombre del usuario
     * @return boolean Indica si existe o no
     */
    public boolean checkUsernameExistance (String name) {
        boolean exists = false;
        LinkedList<User> users = sql.SelectDataUser();
        try {
            for (User user : users) {
                if (user.getName().equals(name)) {
                    exists = true;
                    break;
                }
            }
        }catch (NullPointerException e) {
            // Si la base de datos esta vacia (users vale null), no existe ningun usuario
            return false;
        }

        return exists;
    }

    /**
     * Metodo que retorna el usuario actual
     * @param login Nombre del usuario
     * @return User Usuario actual
     */
    public User getCurrentUser (String login) {
        User userAux = new User();
        LinkedList<User> users = sql.SelectDataUser();
        for (User user: users) {
            if (user.getName().equals(login)) {
                userAux = user;
            }
        }
        return userAux;
    }

    /**
     * Metodo indica si el e-mail existe
     * @param email Email del usuario
     * @return boolean Indica si existe el email
     */
    public boolean checkEmailExistance (String email) {
        boolean exists = false;
        LinkedList<User> users = sql.SelectDataUser();
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Metodo que indica si el email tiene una estructura correcta
     * @param email Email del usuario
     * @return boolean Indica si el formato es correcto
     */
    public boolean checkEmailFormat (String email) {
        return !email.contains("@") || !email.contains(".com");
    }

    /**
     * Metodo que comprueba si el formato de la contraseña introducida es correcto
     * @param password Contraseña introducida
     * @return boolean Indica si la contraseña es correcta
     */
    public boolean checkPasswordFormat (String password) {
        boolean upperFlag = false, lowerFlag = false, numberFlag = false, lengthFlag = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                upperFlag = true;
            }
            if (Character.isLowerCase(ch)) {
                lowerFlag = true;
            }
            if (Character.isDigit(ch)) {
                numberFlag = true;
            }
        }

        if (upperFlag && lowerFlag && numberFlag && password.length() >= 8) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Metodo que elimina un usuario junto con toda su respectiva informacion
     */
    public void deleteUser(){
        User userToDelete = Owner.getUser();
        LinkedList<Playlist> userPlaylists= sqlP.SelectPlaylistsOfUser(userToDelete);
        LinkedList<Song> songs= sqlS.SelectDataSong();
        LinkedList<Playlist> generalPlaylists = sqlP.SelectDataPlaylist();


        for (Playlist playlist : userPlaylists) {
                sqlP.DeleteDataPlaylist(playlist.getName());
        }

        for (Song song : songs) {
            // Para cada cancion del usuario que se borra, debemos eliminarla del resto de playlist que la contienen
            if (song.getOwner().equals(userToDelete.getName())) {
                for (Playlist playlist: generalPlaylists) {
                    for (int i = 0; i < playlist.getSongs().size(); i++) {
                        if (playlist.getSongs().get(i).getTitle().equals(song.getTitle())) {
                            SongPlaylistManager.deleteSongP(song.getTitle(), playlist.getName());
                        }
                    }
                }
                sqlS.DeleteDataSong(song.getTitle());
            }
        }
        sql.DeleteDataUser(userToDelete.getName());


    }

    /**
     * Metodo que desloguea un usuario
     */
    public void logout(){
        User userToLogout = Owner.getUser();
        //sql.LogoutUser(userToLogout);
        Owner.setUser(null);
    }
}