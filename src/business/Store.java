package business;

import business.entities.User;

public final class Store {
    private static User currentUser;
    public static User getUser () {
        return currentUser;
    }
    public static void setUser (User user) {
        currentUser = user;
    }
}