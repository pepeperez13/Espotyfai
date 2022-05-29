package business.entities;

/**
 * Clase que representa el usuario que esta logeado
 */
public class User {
    private String name;
    private String email;
    private String password;

    /**
     * Constructor de la clase
     * @param name nombre del usuario logeado
     * @param email email del usuario logeado
     * @param password contraseña del usuario logeado
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User () {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
