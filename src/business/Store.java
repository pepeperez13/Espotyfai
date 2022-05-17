package business;

import business.entities.User;

public class Store {
    public static User LOGGED_USER;
    private User currentUser;

    public void setUser (User loginUser) {
        //Cuando se inicia sesion, mandar esta info aquí.Será la informacion gestionaremos a tiempo real.
        currentUser = loginUser;
    }

    public User getUser () {
        return currentUser;
    }

}