package business;

import business.entities.Playlist;
import business.entities.User;
import persistance.dao.sql.SQLConnector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class UserManager {
    private SQLConnector sql;

    public UserManager (SQLConnector sql) {
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

    public boolean checkUsernameExistance (String name) {
        boolean exists = false;
        LinkedList<User> users = sql.SelectDataUser();
        for (User user: users) {
            if (user.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
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
        User userToDelete = Store.LOGGED_USER;
        LinkedList<Playlist> playlists= sql.SelectDataPlaylist();
        for (Playlist playlist : playlists) {
            if (playlist.getOwner().equals(userToDelete.getEmail())) {
                sql.DeleteDataPlaylist(playlist);
            }
        }
        sql.DeleteDataUser(userToDelete);


    }
    public void logout(){
        User userToLogout = Store.LOGGED_USER;
        sql.LogoutUser(userToLogout);
        Store.LOGGED_USER = null;
    }
}