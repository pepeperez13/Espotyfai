package business;

import business.entities.User;

public class Store {
    public static User LOGGED_USER;
    private User currentUser = new User();

    public Store (User user) {
        this.currentUser = user;
    }
    public Store () {

    }
    public User getUser () {
        return this.currentUser;
    }

}