package persistance.dao.sql;

import business.entities.SongPlaylist;

import persistance.SongDAO;
import persistance.SongPlaylistDAO;

import java.sql.*;
import java.util.LinkedList;

/**
 * The type Sql connector song playlist.
 */
public class SQLConnectorSongPlaylist implements SongPlaylistDAO {

    private static SongDAO songDAO = new SQLConnectorSong();
    private static String dbURL = songDAO.GetDataBaseData().getDataBaseIP();
    private static String username = songDAO.GetDataBaseData().getUserName();
    private static String password = songDAO.GetDataBaseData().getPassword();
    private static Connection conn;

    /**
     * Metodo que inserta los datos de una cancion en la base de datos.
     * @param title
     * @param name
     * @param pos
     * @throws Exception
     */
    public void InsertDataSongP(String title, String name, int pos) throws Exception {
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia SQL que especifica las columnas a introducir.
            String sql = "INSERT INTO song_playlist (SONG_TITLE,PLAYLIST_NAME,POS) VALUES (?, ?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, name);
            statement.setInt(3, pos);




            int rowsInserted = statement.executeUpdate();


        } catch (SQLException ex) {
           throw ex;
        }
    }

    /**
     * Metodo que complementado con DeleteDataSongPpt2 y DeleteDataSong corren la posicion de las canciones posteriores a la eliminada.
     * @param name
     * @param title
     */
    public void DeleteDataSongPpt3(String name,String title){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia SQL para eliminar canciones dependiendo del titulo de la cancion.
            String sql = "DELETE FROM song_playlist WHERE SONG_TITLE=?";

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
     * Metodo que complementado con DeleteDataSongPpt3 y DeleteDataSong corren la posicion de las canciones posteriores a la eliminada.
     * @param pos
     */
    public void DeleteDataSongPpt2(int pos){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia SQL para actualizar la posicion dependiendo de la posicion dada
            String sql2 = "UPDATE song_playlist SET POS =? WHERE POS = '"+pos+"'";

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            pos = pos-1;
            statement2.setInt(1,pos);
            System.out.println(pos);


            int rowsUpdated = statement2.executeUpdate();

            statement2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que se utiliza para quitar una cancion de una playlist especifica.
     * @param name
     * @param title
     */

    public void DeleteDataSongP(String name,String title){
        //Connectamos a la base de datos y controlamos excepciones.
        //se selecciona la fila en la que operaremos
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Conexion ok");
            //Llamamos a un metodo que nos da la posicion de una cancion dentro de una playlist.
            int posInit = getPosP(title,name);
            //Generamos un statement sql donde seleccionamos dependiendo de el nombre d ela playlist y siendo la posicion mayor que la posicion inicial.
            String sql = "SELECT * FROM song_playlist WHERE PLAYLIST_NAME like '"+name+"'AND POS > '"+posInit+"'";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            int pos = 0;

            while (rs.next()) {
                System.out.println("Ok while");
                //se va incrementando la fila en la que operamos y updateamos la posicion restandole 1
                pos = rs.getInt("POS");
                System.out.println(pos);
                System.out.println("Conexion ok");
                String sql2 = "UPDATE song_playlist SET POS = POS - 1 WHERE POS = '"+pos+"'";

                PreparedStatement statement2 = conn.prepareStatement(sql2);


                int rowsUpdated = statement2.executeUpdate();
                pos++;


            }
            //Llamamos a este metodo para acabar el proeceso.
            DeleteDataSongPpt3(name,title);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * metodo que solo se utiliza cuando se quiere eliminar la playlist entera de la talbla song playlist
     * @param name
     */

    public void DeleteDataSongPFull(String name){
        //Connectamos a la base de datos y controlamos excepciones.
        //se selecciona la fila en la que operaremos
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia sql para eliminar dependiendo del nombre de la playlisy
            String sql = "DELETE FROM song_playlist WHERE PLAYLIST_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Metodo que se utiliza para eliminar una cancion de la tabla song playlist.
     * @param title
     */
    public void DeleteSongPFull(String title){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia SQL para eliminar dependiendo del titulo
            String sql = "DELETE FROM song_playlist WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Metodo que se emplea para seleccionar la informacion de una cancion de una playlist.
     * @return
     */
    public LinkedList<SongPlaylist> SelectDataSongP(){
        //Creamos una linked list de SongPlaylist
        LinkedList<SongPlaylist> songsP = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia sql que selecciona de la tabla song playlist
            String sql = "SELECT * FROM songs_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            //Bucle que va llenando la linked list.
            while (rs.next())
            {
                String title = rs.getString("SONG_TITLE");
                String name = rs.getString("PLAYLIST_NAME");
                int pos = rs.getInt("POS");


                SongPlaylist newSong = new SongPlaylist(title, name, pos);
                songsP.add(newSong);



            }

            statement.close();
            return songsP;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }




    }

    /**
     * Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
     * @param pName
     * @return
     */

    public LinkedList<SongPlaylist> SelectSongsP(String pName){
        //Creamos una linked list de SongPlaylist
        LinkedList<SongPlaylist> songP = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Creamos una sentencia SQL para seleccionar sobre la tabla song_playlist.
            String sql = "SELECT * FROM song_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            String title = null;
            //Bucle que llena la linked list con el titulo, el nombre de la playlist y la posicion.
            while (rs.next()) {

                title = rs.getString("SONG_TITLE");
                String pNameSQL = rs.getString("PLAYLIST_NAME");
                int pos = rs.getInt("POS");


                if (pNameSQL.equals(pName)) {
                    SongPlaylist newSongP = new SongPlaylist(title, pName, pos);
                    songP.add(newSongP);
                    /*System.out.println("bien");
                    System.out.println(songP);*/
                }else{
                    //System.out.println("Mal");
                }

            }
            statement.close();
            return songP;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }



    }

    /**
     * Metodo que actualiza la posicion de una cancion en una playlist.
     * @param title
     * @param name
     * @param pos
     * @return
     */
    public LinkedList<SongPlaylist> updatePosP(String title, String name,int pos) {
        //Creamos una linked list de Song Playlist
        LinkedList<SongPlaylist> songPosP = new LinkedList<>();
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            String sql = "UPDATE song_playlist SET POS =? WHERE SONG_TITLE LIKE ? AND PLAYLIST_NAME LIKE ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pos );
            statement.setString(2, title);
            statement.setString(3, name);



            int rowsUpdated = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return songPosP;
    }

    /**
     * Metodo que obtiene la posicion de una cancion en una playlist.
     * @param title
     * @param name
     * @return
     */
    public int getPosP (String title, String name){
        int pos = 0;
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");


            //Generamos un statement SQL que selecciona en la tabla song_playlist dependiendo del titulo de la cancion y el nombre de la playlist.
            String sql = "SELECT * FROM song_playlist WHERE SONG_TITLE = '"+title+"' AND PLAYLIST_NAME like '"+name+"' ";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                pos = rs.getInt("POS");
                System.out.println("-----"+pos+"-----");
            }

            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pos;
    }

    /**
     * Metodo que se utiliza para incrementar en una posicion una cancion en una playlist especifica.
     * @param name
     * @param pos
     * @return
     */
    public int UpdatePosPExtraU (String name, int pos){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos sentencia sql que actualiza la tabla song_playlist dependeidno de la posicion el nombre de la playlist
            String sql = "UPDATE song_playlist SET POS =? WHERE POS =? AND PLAYLIST_NAME LIKE ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pos );
            statement.setInt(2, pos - 1);
            statement.setString(3, name);
            System.out.println("Up");


            int rowsUpdated = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pos;
    }

    /**
     * Metodo que se utiliza para decrementar en una posicion la posicion de una cancion en una playlist especifica.
     * @param name
     * @param pos
     * @return
     */
    public int UpdatePosPExtraD (String name, int pos){
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //generamos una sentencia sql para actualizar la tabla song_playlist dependiendo de la posicion y el nombre de la playlist.
            String sql = "UPDATE song_playlist SET POS =? WHERE POS =? AND PLAYLIST_NAME LIKE ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pos );
            statement.setInt(2, pos + 1);
            statement.setString(3, name);
            System.out.println("Down");


            int rowsUpdated = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pos;
    }

    /**
     * Metodo que se emplea para saber cual es la ultima posicion de una playlist.
     * @param name
     * @return
     */
    public int getLastPos(String name){
        int pos = 0;
        int maxPos =0;
        //Connectamos a la base de datos y controlamos excepciones.
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Conexion ok");
            //Generamos una sentencia sql que selecciona en la tabla song_playlisy dependiendo del nombre de la playlisy y retorna la utlima posicion.
            String sql = "SELECT * FROM song_playlist WHERE PLAYLIST_NAME like '"+name+"' ";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                pos = rs.getInt("POS");
                System.out.println("-----"+pos+"-----");

                if (pos>maxPos){
                    maxPos = pos;
                }
            }
            System.out.println(maxPos);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return maxPos;
    }


    /**
     * Method that closes the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    /**
     * Metodo que cierra la conexion con la base de datos
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: "+e.getSQLState()+"("+e.getMessage() + ")");
        }
    }


}






