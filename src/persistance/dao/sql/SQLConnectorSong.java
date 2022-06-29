package persistance.dao.sql;

import business.entities.Config;
import business.entities.Song;
import persistance.ConfigJsonDao;
import persistance.SongDAO;

import java.sql.*;
import java.util.LinkedList;


/**
 * Clase que contiene todos los metodos para acceder y modificar la información de la base de datos de Song
 */
public class SQLConnectorSong implements SongDAO {
    private static SongDAO songDAO = new SQLConnectorSong();
    private static String dbURL = songDAO.GetDataBaseData().getDataBaseIP();
    private static String username = songDAO.GetDataBaseData().getUserName();
    private static String password = songDAO.GetDataBaseData().getPassword();

    /**
     * Metodo que se encarga de obtener de el archivo JSON los parametros de necesarios que identifican a la base de datos.
     * @return config
     */
    public Config GetDataBaseData(){
        Config config;
        //Llamamos y creamos en classe a la classe ConfigJsonDao para acceder a su metodo.
        ConfigJsonDao configJsonDao = new ConfigJsonDao();
        config = configJsonDao.readConfig();
        System.out.println(config);
        //Devuelve config
        return config;
    }

    /**
     * Metodo que se encarga de insertar en la base de datos los datos de una cancion.
     * @param title titulo de la cancion
     * @param genre genero de la cancion
     * @param album album de la cancion
     * @param artist artista de la cancion
     * @param path ruta de la cancion
     * @param owner dueño de la cancion
     */
    public void InsertDataSong(String title, String genre, String album, String artist, String path, String owner) {
    //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Accedemos a base de datos mediante insert y le especificamos donde y que guardar en cada columna.
            String sql = "INSERT INTO song (SONG_TITLE,SONG_GENRE,SONG_ALBUM,SONG_ARTIST,SONG_PATH,SONG_OWNER) VALUES (?, ?, ?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, album);
            statement.setString(4, artist);
            statement.setString(5, path);
            statement.setString(6, owner);


            int rowsInserted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Metodo que se encarga de borrar en la base de datos los datos de una cancion.
     * @param title titulo de la cancion
     */
    public void DeleteDataSong(String title){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Utilizamos una sentencia SQL y le pasamos el titulo para que borre toda la informacion relacionada con el.
            String sql = "DELETE FROM song WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A song was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Metodo que selecciona la informacion de la entidad cancion.
     * @return songs linked list llena con la informacion de las canciones del sistema
     */

    public LinkedList<Song> SelectDataSong(){
        //Creamos una linked list de songs.
        LinkedList<Song> songs = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            String sql = "SELECT * FROM song";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            //bucle que va obteniendo toda la informacion fila a fila va rellenando la linked list.
            while (rs.next())
            {
                String title = rs.getString("SONG_TITLE");
                String genre = rs.getString("SONG_GENRE");
                String album = rs.getString("SONG_ALBUM");
                String artist = rs.getString("SONG_ARTIST");
                String path = rs.getString("SONG_PATH");
                String owner = rs.getString("SONG_OWNER");


                Song newSong = new Song(title, genre, album, artist, path, owner);
                songs.add(newSong);


            }

            statement.close();
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }



    }



    /**
     * Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
     * @param name nombre de la cancion
     * @return newSong entidad cancion
     */
    public Song SelectSong(String name){
        Song newSong = null;
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Ejecutamos el statement Select
            String sql = "SELECT * FROM song";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            String title = null;
            //Bucle que recorre fila por fila toda la tabla y selecciona toda la informacion solo relacionada con el nombre introducido.
            while (rs.next()) {

                title = rs.getString("SONG_TITLE");
                String genre = rs.getString("SONG_GENRE");
                String album = rs.getString("SONG_ALBUM");
                String artist = rs.getString("SONG_ARTIST");
                String path = rs.getString("SONG_PATH");
                String owner = rs.getString("SONG_OWNER");

                //Condicional que selecciona solo la informacion relacionada con el paramtro introducido
                if (title.equals(name)) {
                    newSong = new Song(title, genre, album, artist, path, owner);
                    System.out.println(title);

                }

            }
            statement.close();
            return newSong;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

}

