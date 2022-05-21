package business;

import business.entities.User;

public class Store {
    public static User LOGGED_USER;
    private User currentUser;
    public User getUser () {
        return this.currentUser;
    }
    public void setUser (User user) {
        this.currentUser = user;
    }
}