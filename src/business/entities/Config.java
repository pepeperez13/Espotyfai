package business.entities;

/**
 *Esta clase representa los datos de la configuracion de la base de datos
 */
public class Config {
    private String dataBaseIP;
    private String userName;
    private String password;

    /**
     * Obtiene la IP de la base de datos
     * @return IP de la base de datos
     */
    public String getDataBaseIP() {
        return dataBaseIP;
    }

    /**
     * Obtiene el usuario de la base de datos
     * @return usuario de la base de datos
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Obtiene la contraseña para acceder a la base de datos
     * @return contraseña de la base de datos
     */
    public String getPassword() {
        return password;
    }
}
