package persistance.dao.sql;

import business.entities.SongPlaylist;

import persistance.SongPlaylistDAO;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnectorSongPlaylist implements SongPlaylistDAO {

    private static String dbURL = "jdbc:mysql://localhost:3306/espotifai";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;



    public void InsertDataSongP(String title, String name, int pos) throws Exception {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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


    /*public void UpdateDataSongP(String title1,String name,String title2){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "UPDATE song_playlist SET SONG_TITLE=?,PLAYLIST_NAME=? WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title1);
            statement.setString(2, name);
            statement.setString(3, title2);


            int rowsUpdated = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    */
    //FUNCIONA
    public void DeleteDataSongPpt3(String name,String title){
        System.out.println("Successful connection 3...");
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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
    public void DeleteDataSongPpt2(int pos){

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection 2...");
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

    //Metodo que se utiliza para quitar una cancion de una playlist especifica.
    public void DeleteDataSongP(String name,String title){
        //se selecciona la fila en la que operaremos
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            int posInit = getPosP(title,name);
            String sql = "SELECT * FROM song_playlist WHERE PLAYLIST_NAME like '"+name+"'AND POS > '"+posInit+"'";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            int pos = 0;
            //int lastPos = getLastPos(name);
            //pos <= lastPos

            while (rs.next()) {
                System.out.println("Ok while");
                //se va incrementando la fila en la que operamos y updateamos la posicion restandole 1
                pos = rs.getInt("POS");
                System.out.println(pos);
                System.out.println("Successful connection 2...");
                String sql2 = "UPDATE song_playlist SET POS = POS - 1 WHERE POS = '"+pos+"'";

                PreparedStatement statement2 = conn.prepareStatement(sql2);


                int rowsUpdated = statement2.executeUpdate();
                pos++;


            }

            DeleteDataSongPpt3(name,title);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    //metodo que solo se utiliza cuando se quiere eliminar la playlist entera de la talbla song playlist
    public void DeleteDataSongPFull(String name){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM song_playlist WHERE PLAYLIST_NAME=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    //Metodo que se utiliza para eliminar una cancion de la tabla song playlist.
    public void DeleteSongPFull(String title){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "DELETE FROM song_playlist WHERE SONG_TITLE=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public LinkedList<SongPlaylist> SelectDataSongP(){
        LinkedList<SongPlaylist> songsP = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM songs_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
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
    //Metodo que te permite obtener toda la informacion de una cancion dado el nombre de la cancion.
    public LinkedList<SongPlaylist> SelectSongsP(String pName){
        LinkedList<SongPlaylist> songP = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
            String sql = "SELECT * FROM song_playlist";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            String title = null;
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

    public LinkedList<SongPlaylist> updatePosP(String title, String name,int pos) {
        LinkedList<SongPlaylist> songPosP = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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

    public int getPosP (String title, String name){
        int pos = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");



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
    //statement.setString(2, name);
    // statement.setInt(3, pos-1);

    public int UpdatePosPExtraU (String name, int pos){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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
    public int UpdatePosPExtraD (String name, int pos){
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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

    public int getLastPos(String name){
        int pos = 0;
        int maxPos =0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            System.out.println("Successful connection...");
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
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }


}






