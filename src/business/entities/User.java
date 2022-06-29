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

    /**
     * Permite crear un nuevo user sin saber aun sus datos
     */
    public User () {
    }

    /**
     * Obtiene el nombre del usuario
     * @return nombre del usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el email del usuario
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene la contrasena del usuario
     * @return contrasena del usuario
     */
    public String getPassword() {
        return password;
    }
}
