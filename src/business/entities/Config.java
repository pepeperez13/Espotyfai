package business.entities;

/**
 *Esta clase representa los datos de la configuracion de la base de datos
 */
public class Config {

    private int dataBasePort;
    private String dataBaseIP;
    private String dataBaseName;
    private String userName;
    private String password;

    public int getDataBasePort() {
        return dataBasePort;
    }

    public String getDataBaseIP() {
        return dataBaseIP;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
