package business;
import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase que gestiona la interaccion de las canciones con la base de datos
 */
public class SongManager {

    private static SongDAO songDAO = new SQLConnectorSong();

    private static SongPlaylistDAO songPDAO = new SQLConnectorSongPlaylist();

    /**
     * Método que añade una canción
     * @param title Título de una canción
     * @param Genre Género de una canción
     * @param album Album al que pertenece la canción
     * @param artist Artista de la canción
     * @param path Path donde se encuentra la canción
     * @param owner Usuario que añadió la canción
     */
    public static void addSong (String title, String Genre, String album, String artist, String path,String owner) {
        songDAO.InsertDataSong(title, Genre, album, artist, path, owner);
    }

    /**
     * Mira si la cancion existe en la base de datos
     * @param title Título de la canción
     * @param path Path donde se encuentra la canción
     * @return true si la canción existe
     */
    public static boolean checkSongExistance (String title,String path) {
        LinkedList<Song> songs = ListSongs();
        for (Song s: songs) {
            if (s.getTitle().equals(title) || s.getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que devuelve una LinkedList con las canciones cargadas
     * @return LinkedList canciones
     */
    public static LinkedList<Song> ListSongs() {
        songDAO = new SQLConnectorSong();
        LinkedList<Song> songs;
        songs = songDAO.SelectDataSong();
        System.out.println(songs);
        return songs;
    }

    /**
     * Método que retorna una canción segun el nombre de la canción
     * @param name Nombre de la canción
     * @return Song Cancion encontrada
     */
    public static Song SelectSong(String name) {
        songDAO = new SQLConnectorSong();
        Song songs;
        songs = songDAO.SelectSong(name);

        System.out.println(songs.getGenre());

        return songs;
    }

    /**
     * Método que elimina una canción
     * @param title Titulo de una cancion
     * @return true si la cancion ha sido eliminada
     *         false si la cancion no se pudo eliminar
     */
    public static boolean DeleteSong(String title){
        for (Song song: ListSongs()) {
            if (song.getTitle().equals(title)) {
                songDAO.DeleteDataSong(title);
                songPDAO.DeleteSongPFull(title);
                return true;
            }
        }
        return false;
    }
}



