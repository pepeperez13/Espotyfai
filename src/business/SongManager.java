package business;

import business.entities.Song;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SongManager<Public> {

    private static SongDAO songDAO = new SQLConnectorSong();
    private static SongPlaylistDAO songPDAO = new SQLConnectorSongPlaylist();

    /**
     * Este método añade una canción
     * @param title Título de la canción
     * @param Genre Genero de la canción
     * @param album
     * @param artist
     * @param path
     * @param owner
     */
    public static void addSong (String title, String Genre, String album, String artist, String path,String owner) {
        songDAO.InsertDataSong(title, Genre, album, artist, path,owner);
    }
    public static LinkedList<Song> ListSongs() {
        songDAO = new SQLConnectorSong();
        LinkedList<Song> songs;
        songs = songDAO.SelectDataSong();
        System.out.println(songs);
        return songs;
    }
    public static void InsertNewSong (String title, String genre, String album, String artist, String path, String owner){
        songDAO = new SQLConnectorSong();

        songDAO.InsertDataSong(title, genre, album, artist, path, owner);
    }

    public static Song SelectSong(String Song) {
        songDAO = new SQLConnectorSong();
        Song songs;
        songs = songDAO.SelectSong(Song);

        System.out.println(songs.getGenre());

        return songs;
    }

    public static void DeleteSong(String song){
        songDAO.DeleteDataSong(song);
        songPDAO.DeleteDataSongP(song);
    }
}



