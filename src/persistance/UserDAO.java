package persistance;

import business.entities.User;

import java.util.LinkedList;

public interface UserDAO {
    void InsertDataUser(String user, String email, String pass);

    void DeleteDataUser(String name);

    LinkedList<User> SelectDataUser();
}
