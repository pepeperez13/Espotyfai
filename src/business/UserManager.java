package business;

import business.entities.Playlist;
import business.entities.Song;
import business.entities.User;

import persistance.PlaylistDAO;
import persistance.SongDAO;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class UserManager {
    private UserDAO sql;
    private PlaylistDAO sqlP;
    private SongDAO sqlS;

    public UserManager (UserDAO sql) {
        this.sql = sql;
    }

    public void insertNewUser (String name, String email, String password) {
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
        System.out.println("La contraseña con el hash es: " + password);
        sql.InsertDataUser(name, email, password);
    }

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

    public User getCurrentUser (String login) {
        User userAux = new User();
        LinkedList<User> users = sql.SelectDataUser();
        for (User user: users) {
            if (user.getName().equals(login)) {
                userAux = user;
            } else {
                //Usuario no existe
                //¿Frame error?
            }
        }
        return userAux;
    }

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

    public boolean checkEmailFormat (String email) {
        return !email.contains("@") || !email.contains(".com");
    }

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

    public void deleteUser(){
        User userToDelete = Store.getUser();
       // LinkedList<Playlist> playlists= sqlP.SelectDataPlaylist();
       // LinkedList<Song> songs= sqlS.SelectDataSong();
       /* for (Playlist playlist : playlists) {
            if (playlist.getOwner().equals(userToDelete.getName())) {
                sqlP.DeleteDataPlaylist(playlist.getName());

            }
        }
        for (Song song : songs) {
            if (song.getOwner().equals(userToDelete.getName())) {
                sqlP.DeleteDataPlaylist(song.getTitle());

            }
        }*/
        sql.DeleteDataUser(userToDelete.getName());


    }
    public void logout(){
        User userToLogout = Store.getUser();
        //sql.LogoutUser(userToLogout);
        Store.setUser(null);
    }
}