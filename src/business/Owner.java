package business;

import business.entities.User;

/**
 * Clase que indica el usuario que esta logueado
 */
public final class Owner {
    private static User currentUser;

    /**
     * Retorna el usuario que esta logueado
     * @return User Usuario logueado
     */
    public static User getUser () {
        return currentUser;
    }

    /**
     * Setea el usuario que ha iniciado sesion o se ha registrado
     * @param user Usuario logueado
     */
    public static void setUser (User user) {
        currentUser = user;
    }
}