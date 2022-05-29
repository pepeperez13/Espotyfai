package business;

import business.entities.Song;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase que se encarga de buscar entre las canciones existentes
 */
public class BuscadorManager {
    //private Conexion conexion;

    /**
     * Método que se encarga de buscar entre las canciones existentes
     * @param song_name Nombre de la cancion
     * @return Song Cancion requerida
     */
    public Song buscarCancion (String song_name) {
        LinkedList<Song> list_songs = SongManager.ListSongs();

        Song cancion_encontrada = null;
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Que cancion quieres buscar? ");
        String buscador = song_name;
        System.out.println(song_name);

        int posicion_song = 0;
        boolean found = false;
        for (int i = 0; i < list_songs.size() || !found; i++) {
            if (buscador.equals(list_songs.get(i).getTitle())) {
                posicion_song = i;
                found = true;
            } else {
                if (buscador.equals((list_songs.get(i).getGenre()))) {
                    posicion_song = i;
                    found = true;
                } else {
                    if (buscador.equals((list_songs.get(i).getArtist()))) {
                        posicion_song = i;
                        found = true;
                    } else {
                        if (buscador.equals((list_songs.get(i).getAlbum()))) {
                            posicion_song = i;
                            found = true;
                        } else {
                            if (buscador.equals((list_songs.get(i).getOwner()))) {
                                posicion_song = i;
                                found = true;
                            }
                        }
                    }
                }
            }
        }
        if (!found) {
            System.out.println("La cancion que buscas no se encuentra actualmente en el sistema. \n");
            cancion_encontrada = null;
        } else {
            if (found) {
                System.out.println("Cancion encontrada! :) \n");
                System.out.println(list_songs.get(posicion_song).getTitle());
                System.out.println(list_songs.get(posicion_song).getArtist());
                System.out.println(list_songs.get(posicion_song).getGenre());
                System.out.println(list_songs.get(posicion_song).getOwner());
                System.out.println(list_songs.get(posicion_song).getAlbum());

                cancion_encontrada = new Song(list_songs.get(posicion_song).getTitle(), list_songs.get(posicion_song).getGenre(), list_songs.get(posicion_song).getAlbum(), list_songs.get(posicion_song).getArtist(), list_songs.get(posicion_song).getPath(), list_songs.get(posicion_song).getOwner());
            }
        }
        return cancion_encontrada;
    }
}
