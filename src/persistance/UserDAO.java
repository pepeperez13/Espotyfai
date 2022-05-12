package persistance;

import business.entities.User;

import java.util.LinkedList;

public interface UserDAO {
    public void InsertDataUser(String user, String email, String pass);

    public void DeleteDataUser(String name);

    public void UpdateDataUser(String name1, String email,String password,String name2);

    public LinkedList<User> SelectDataUser();
}
