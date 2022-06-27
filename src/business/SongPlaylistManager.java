package business;


import business.entities.Playlist;
import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.PlaylistDAO;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;


public class SongPlaylistManager {
    private static SongPlaylistDAO songPDAO;
    private static PlaylistDAO playlistDAO;
    private static SongDAO songDAO;
    private static PlaylistManager playlistManager;

    /**
     * Metodo que se emplea para insertar una nueva cancion en una base de datos y se suma una posicion sobre la ultima cancion de la playlist modificada.
     * @param title titulo de la cancion
     * @param name nombre de la playlisy
     * @return boolean
     * @throws Exception
     */
    public boolean InsertNewSongPlaylist(String title, String name){
        boolean exists = false;
        int lastPos = 0;
        playlistManager = new PlaylistManager();
        songPDAO = new SQLConnectorSongPlaylist();
        //Creaccion de una linked list de playlist
        //Llamamos a un metodo de playlist manager que nos da la informacion de una playlist
        LinkedList<Playlist> playlists = playlistManager.getDataPlaylists();

        lastPos = songPDAO.getLastPos(name);

        for(Playlist p: playlists){
            if(p.getName().equals(name)){
                if(p.getOwner().equals(Owner.getUser().getName())){
                    songPDAO.InsertDataSongP(title, name, lastPos +1);
                    return true;
                }
            }
        }
        return false;


    }


    /**
     * Metodo para quitar o eliminar una cancion de una playlist
     * @param name nombre de la playlist
     * @param title titulo de la cancion
     */

    public void deleteSongPlaylistSong(String name,String title){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongP(name,title);
    }
    /**
     * Metodo para quitar o eliminar una cancion de una playlist (TESTS)
     * @param name nombre de la playlisy
     * @param title titulo de la cancion
     */
    public static void deleteSongP(String name, String title){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongPpt3(title,name);
    }


    /**
     * Metodo que te devuelve una linked list de SONG con todas las canciones de una playlist
     * @param name nombre de la playlist
     * @return songs
     */

    public  LinkedList<Song> ListPlaylistSongs(String name){
        songPDAO = new SQLConnectorSongPlaylist();
        songDAO = new SQLConnectorSong();
        playlistDAO = new SQLConnectorPlaylist();
        LinkedList<SongPlaylist> PSongs;

        LinkedList<Song> songs = new LinkedList<Song>();

        PSongs = songPDAO.SelectSongsP(name);
        for(int i = 0; i < PSongs.size(); i++) {
            System.out.println(PSongs.get(i).getTitle());

            songs.add(songDAO.SelectSong(PSongs.get(i).getTitle())) ;

        }

        return songs;
    }

    /**
     * Metodo que actualiza la posicion de una cancion subiendola o bajandola dependiendo del indice indicado.
     * @param title titulo de la cancion
     * @param name nombre de la playlist
     * @param index indice que indica si se sube una posicion (1) o se baja una posicion (2) en una playlisy
     */
    public void updatePosP(String title, String name, int index){
        LinkedList<SongPlaylist> PSongs2 = null;
        songPDAO = new SQLConnectorSongPlaylist();
        int pos = 0;

        switch (index){
            case (1):

                pos = songPDAO.getPosP(title,name);
                System.out.println(pos);


                if (pos != 1) {
                    songPDAO.UpdatePosPExtraU(name, pos);
                    songPDAO.updatePosP(title, name, pos - 1);
                }
                break;
            case (2):
                pos = songPDAO.getPosP(title,name);
                System.out.println(pos);
                System.out.println("HOLASSAA");

                songPDAO.UpdatePosPExtraD(name,pos);
                songPDAO.updatePosP(title,name,pos + 1);
                break;
        }

    }

    /**
     * Metodo que verifica si la cancion existe en una playlist.
     * @param songTitle titulo de la cancion
     * @param playlistName nombre de la playlist
     * @return boolean
     */
    public boolean songExistsInPlaylist (String songTitle, String playlistName) {
        LinkedList<Song> songs = ListPlaylistSongs(playlistName);
        boolean exists = false;

        try {
            for (Song song : songs) {
                if (song.getTitle().equals(songTitle)) {
                    exists = true;
                }
            }
        } catch (NullPointerException e) {
            exists = false;
        }
        return exists;
    }
}

