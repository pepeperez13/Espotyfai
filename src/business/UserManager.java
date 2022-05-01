package business;

import business.entities.User;
import persistance.dao.sql.SQLConnector;

import java.util.LinkedList;

public class UserManager {
    private SQLConnector sql;

    public UserManager (SQLConnector sql) {
        this.sql = sql;
    }

    public void insertNewUser (String name, String email, String password) {
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
}
